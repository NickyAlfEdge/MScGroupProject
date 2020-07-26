package com.team.project;

import com.team.project.mapper.GroupPreferenceMapper;
import com.team.project.mapper.UserMapper;
import com.team.project.model.GroupPreference;
import com.team.project.model.GroupPreferenceExample;
import com.team.project.model.User;
import com.team.project.model.UserExample;
import com.team.project.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class TeamProjectApplicationTests {

    @Autowired
    private GroupPreferenceMapper groupPreferenceMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        Date now = new Date();
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("user_id desc");
        userExample.createCriteria().andUserIdGreaterThan(1111).andUserTypeEqualTo(new Byte("1")).andStudentGroupIdIsNull();
        List<User> users = userMapper.selectByExample(userExample);
        int size = users.size();
        User currentUser;
        User like;
        User dislike1;
        User dislike2;
        for (int i = 0; i < size; i++) {
            currentUser = users.get(i);
            int next = (i + 1 < size) ? i + 1 : (i + 1 - size);
            int next2 = (i + 2 < size) ? i + 2 : (i + 2 - size);
            int next3 = (i + 3 < size) ? i + 3 : (i + 3 - size);
            like = users.get(next);
            dislike1 = users.get(next2);
            dislike2 = users.get(next3);
            GroupPreference groupPreference = new GroupPreference();
            groupPreference.setStudentId(currentUser.getUserId());
            groupPreference.setLikePersonId(like.getUserId());
            groupPreference.setDislikePersonId(dislike1.getUserId());
            groupPreference.setDislikePersonTwoId(dislike2.getUserId());
            groupPreference.setCreateTime(now);
            groupPreference.setUpdateTime(now);
            try {
                groupPreferenceMapper.insert(groupPreference);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void autoGroup() {
        // save student into this group list
        Map<Integer, Set<Integer>> readyMap;
        Map<Integer, Set<Integer>> groupListMap;
        Integer groupTempId = 0;
        Map<Integer, User> studentMap;
        List<User> userList;
        List<Integer> userIdList;
        List<GroupPreference> preferences;
        Map<Integer, GroupPreference> preferenceMap;
        // get all students without group
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStudentGroupIdIsNull().andUserTypeEqualTo(new Byte("1"));
        userList = userMapper.selectByExample(userExample);
        studentMap = new HashMap<>(userList.size());
        userList.forEach((user) -> studentMap.put(user.getUserId(), user));
        Integer groupNum = userList.size() / 4;
        groupListMap = new HashMap<>(groupNum);
        readyMap = new HashMap<>(groupNum);
        GroupPreferenceExample preferenceExample = new GroupPreferenceExample();
        userIdList = new ArrayList<>(userList.size());
        userList.forEach(user -> userIdList.add(user.getUserId()));
        preferenceExample.createCriteria().andStudentIdIn(userIdList);
        preferences = groupPreferenceMapper.selectByExample(preferenceExample);
        preferenceMap = new HashMap<>(preferences.size());
        // 1. find like each other
        GroupPreference groupPreference;
        GroupPreference groupPreference1;
        next:
        for (int i = 0; i < preferences.size(); i++) {
            preferenceMap.put(preferences.get(i).getStudentId(), preferences.get(i));
            for (int j = i + 1; j < preferences.size(); j++) {
                groupPreference = preferences.get(i);
                groupPreference1 = preferences.get(j);
                // love each other, in one group
                if (groupPreference.getLikePersonId() == groupPreference1.getStudentId() &&
                        groupPreference.getStudentId() == groupPreference1.getLikePersonId()) {
                    Integer oldGroupId = studentMap.get(groupPreference.getStudentId()).getStudentGroupId();
                    Integer oldGroupId2 = studentMap.get(groupPreference.getLikePersonId()).getStudentGroupId();
                    if (oldGroupId == null && oldGroupId2 == null) {
                        // create new group
                        groupTempId += 1;
                        HashSet<Integer> integers = new HashSet<>();
                        integers.add(groupPreference.getStudentId());
                        integers.add(groupPreference.getLikePersonId());
                        groupListMap.put(groupTempId, integers);
                        // set user group id to tempId
                        studentMap.get(groupPreference.getStudentId()).setStudentGroupId(groupTempId);
                        studentMap.get(groupPreference.getLikePersonId()).setStudentGroupId(groupTempId);
                        // every one only has one like ,so stop inside loop
                        continue next;
                    }
                    if (oldGroupId != null) {
                        // find old group
                        Set<Integer> integers = groupListMap.get(oldGroupId);
                        // add member
                        integers.add(groupPreference.getLikePersonId());
                        // set group id
                        studentMap.get(groupPreference.getLikePersonId()).setStudentGroupId(oldGroupId);
                        continue next;
                    }
                    if (oldGroupId2 != null) {
                        // find old group
                        Set<Integer> integers = groupListMap.get(oldGroupId2);
                        // add member
                        integers.add(groupPreference.getStudentId());
                        // set group id
                        studentMap.get(groupPreference.getStudentId()).setStudentGroupId(oldGroupId2);
                        continue next;
                    }
                }
            }
        }
        // love each other allocation over
        User likedStu;
        Integer oldGroupId;
        Set<Integer> integers;
        // try to set their to their likes' group when (group <6)
        for (User user : studentMap.values()) {
            // if have a group, skip this stu
            if (user.getStudentGroupId() != null) {
                continue;
            }
            groupPreference = preferenceMap.get(user.getUserId());
            if (groupPreference == null) {
                continue;
            }
            // find his like
            likedStu = studentMap.get(groupPreference.getLikePersonId());
            if (likedStu != null) {
                oldGroupId = likedStu.getStudentGroupId();
                if (oldGroupId != null) {
                    // find old group
                    integers = groupListMap.get(oldGroupId);
                    // every group should be less than 6
                    if (integers.size() >= 5) {
                        continue;
                    }
                    // add member
                    integers.add(user.getUserId());
                    // set group id in to stu map
                    user.setStudentGroupId(oldGroupId);
                    continue;
                } else {
                    // create new group
                    groupTempId += 1;
                    integers = new HashSet<>();
                    integers.add(groupPreference.getStudentId());
                    integers.add(groupPreference.getLikePersonId());
                    groupListMap.put(groupTempId, integers);
                    // set user group id to tempId
                    user.setStudentGroupId(groupTempId);
                    likedStu.setStudentGroupId(groupTempId);
                    continue;
                }
            }
        }
        // allocation all users into group
        for (User user : studentMap.values()) {
            if (user.getStudentGroupId() != null) {
                continue;
            }
            // find old group and add new member
            for (Map.Entry<Integer, Set<Integer>> entry : groupListMap.entrySet()) {
                integers = entry.getValue();
                oldGroupId = entry.getKey();
                // full group move to ready map
                if (integers.size() >= 5) {
//                        groupListMap.remove(oldGroupId);
//                        readyMap.put(oldGroupId, integers);
                    continue;
                }
                // add member
                integers.add(user.getUserId());
                // set group id in to stu map
                studentMap.get(user.getUserId()).setStudentGroupId(oldGroupId);
                break;
            }
            if (user.getStudentGroupId() == null) {
                // create new group and add member
                groupTempId += 1;
                integers = new HashSet<>();
                integers.add(user.getUserId());
                groupListMap.put(groupTempId, integers);
                // set user group id to tempId
                studentMap.get(user.getUserId()).setStudentGroupId(groupTempId);
            }
        }

    }
}
