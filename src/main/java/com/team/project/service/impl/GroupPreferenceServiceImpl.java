package com.team.project.service.impl;


import com.team.project.mapper.GroupMapper;
import com.team.project.mapper.GroupPreferenceMapper;
import com.team.project.mapper.UserMapper;
import com.team.project.model.*;
import com.team.project.service.GroupPreferenceService;
import com.team.project.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Kai
 * @date 5/9/2020
 */
@Service
public class GroupPreferenceServiceImpl implements GroupPreferenceService {

    @Autowired
    private GroupPreferenceMapper groupPreferenceMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<GroupPreference> selectGroupPreferenceList() {
        GroupPreferenceExample example = new GroupPreferenceExample();
        return groupPreferenceMapper.selectByExample(example);
    }

    @Override
    public GroupPreference saveOrUpdate(GroupPreference groupPreference) {
        Date now = new Date();
        if (groupPreference.getId() != null) {
            GroupPreference oldGroupPreference = groupPreferenceMapper.selectByPrimaryKey(groupPreference.getId());
            BeanUtils.copyProperties(groupPreference, oldGroupPreference);
            oldGroupPreference.setUpdateTime(now);
            groupPreferenceMapper.updateByPrimaryKey(oldGroupPreference);
        } else {
            groupPreference.setId(groupPreference.getId());
            groupPreference.setUpdateTime(now);
            groupPreferenceMapper.insert(groupPreference);
        }

        return groupPreference;
    }

    @Override
    public void autoAllocation() {
        // save student into this group list
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
                if (groupPreference.getLikePersonId().equals(groupPreference1.getStudentId()) &&
                        groupPreference.getStudentId().equals(groupPreference1.getLikePersonId())) {
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
        Date now = new Date();
        groupListMap.forEach((key, value) -> {
            Group group = new Group();
            group.setName(UUIDUtil.getShortUUID());
            group.setCreateTime(now);
            group.setUpdateTime(now);
            groupMapper.insert(group);
            User user;
            for (Integer integer : value) {
                user = studentMap.get(integer);
                user.setStudentGroupId(group.getGroupId());
                userMapper.updateByPrimaryKey(user);
            }
        });
    }
}
