package com.team.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.team.project.constant.ResultEnum;
import com.team.project.dto.ProjectPreferenceDTO;
import com.team.project.dto.Result;
import com.team.project.mapper.ProjectPreferenceMapper;
import com.team.project.mapper.UserMapper;
import com.team.project.model.Group;
import com.team.project.model.ProjectPreference;
import com.team.project.model.ProjectPreferenceExample;
import com.team.project.model.User;
import com.team.project.service.ProjectPreferenceService;
import com.team.project.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ZQJ
 * @date 5/9/2020
 */
@Service
public class ProjectPreferenceServiceImpl implements ProjectPreferenceService {

    @Autowired
    private ProjectPreferenceMapper preferenceMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Group> selectGroupByProjectId(Integer projectId) {
        ProjectPreferenceExample example = new ProjectPreferenceExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return preferenceMapper.selectGroupByExample(example);
    }

    @Override
    public List<ProjectPreferenceDTO> selectGroupProjectList(String groupName, String projectName, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        ProjectPreferenceExample preferenceExample = new ProjectPreferenceExample();
        preferenceExample.setOrderByClause("id asc");
        if (groupName != null && groupName.length() > 0) {
            preferenceExample.createCriteria().andGroupNameLike("%" + groupName + "%");
        }
        return preferenceMapper.selectGroupsWithProjectByExample(preferenceExample);
    }

    @Override
    public void saveOrUpdatePreference(ProjectPreference preference) {
        Date now = new Date();
        ProjectPreferenceExample preferenceExample = new ProjectPreferenceExample();
        preferenceExample.createCriteria().andGroupIdEqualTo(preference.getGroupId());
        List<ProjectPreference> oldList = preferenceMapper.selectByExample(preferenceExample);
        if (oldList.size() > 0) {
            ProjectPreference oldOne = oldList.get(0);
            oldOne.setProjectId(preference.getProjectId());
            oldOne.setUpdateTime(now);
            preferenceMapper.updateByPrimaryKey(oldOne);
        } else {
            preference.setCreateTime(now);
            preference.setUpdateTime(now);
            preferenceMapper.insert(preference);
        }
    }

    @Override
    public Result choosePreference(Integer projectId, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getStudentGroupId() == null) {
            return ResultUtil.error(ResultEnum.NO_GROUP);
        }
        ProjectPreferenceExample example = new ProjectPreferenceExample();
        example.createCriteria().andGroupIdEqualTo(user.getStudentGroupId());
        List<ProjectPreference> preferences = preferenceMapper.selectByExample(example);
        if (preferences.size() > 0) {
            return ResultUtil.error(ResultEnum.HAS_CHOSEN_PROJECT);
        }
        ProjectPreference projectPreference = new ProjectPreference();
        projectPreference.setGroupId(user.getStudentGroupId());
        projectPreference.setProjectId(projectId);
        Date now = new Date();
        projectPreference.setCreateTime(now);
        projectPreference.setUpdateTime(now);
        preferenceMapper.insert(projectPreference);
        return ResultUtil.success();
    }

    @Override
    public List<ProjectPreferenceDTO> selectGroupProjectByStudentId(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Integer studentGroupId = user.getStudentGroupId();
        // student has not chosen group
        if (studentGroupId == null) {
            return null;
        } else {
            ProjectPreferenceExample preferenceExample = new ProjectPreferenceExample();
            // use groupId of GROUP table to search
            preferenceExample.createCriteria().andGroupIdOfGroupEqualTo(studentGroupId);
            return preferenceMapper.selectGroupsWithProjectByExample(preferenceExample);
        }
    }

    @Override
    public String selectActiveStudentProjectByGroupID(User activeUser) {
        if (activeUser.getStudentGroupId() == null) {
            return "";
        } else {
            ProjectPreferenceExample preferenceExample = new ProjectPreferenceExample();
            preferenceExample.createCriteria().andGroupIdOfGroupEqualTo(activeUser.getStudentGroupId());
            List<ProjectPreferenceDTO> studentGroupMem = preferenceMapper.selectGroupsWithProjectByExample(preferenceExample);
            return studentGroupMem.get(0).getProjectName();
        }
    }
}
