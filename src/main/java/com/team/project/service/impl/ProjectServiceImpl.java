package com.team.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.team.project.constant.ResultEnum;
import com.team.project.dto.ProjectDetailDTO;
import com.team.project.dto.Result;
import com.team.project.mapper.ProjectMapper;
import com.team.project.mapper.ProjectPreferenceMapper;
import com.team.project.model.Project;
import com.team.project.model.ProjectExample;
import com.team.project.model.ProjectPreference;
import com.team.project.model.ProjectPreferenceExample;
import com.team.project.service.ProjectService;
import com.team.project.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ZQJ
 * @date 5/8/2020
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectPreferenceMapper projectPreferenceMapper;

    @Override
    public List<ProjectDetailDTO> selectProjectList() {
        ProjectExample example = new ProjectExample();
        example.setOrderByClause("name asc");
        return projectMapper.selectProjectListByExample(example);
    }

    /**
     * search projects which has been passed verify
     *
     * @return
     */
    @Override
    public List<ProjectDetailDTO> selectSuitableProjectList() {
        ProjectExample example = new ProjectExample();
        example.setOrderByClause("name asc");
        // only select suitable projects
        example.createCriteria().andStatusEqualTo(new Byte("1"));
        return projectMapper.selectProjectListByExample(example);
    }

    @Override
    public List<ProjectDetailDTO> selectProjectList(String projectName, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        ProjectExample projectExample = new ProjectExample();
        projectExample.setOrderByClause("update_time desc");
        if (projectName != null && projectName.length() > 0) {
            projectExample.createCriteria().andNameLike("%" + projectName + "%");
        }
        return projectMapper.selectProjectListByExample(projectExample);
    }

    @Override
    public ProjectDetailDTO selectProjectById(Integer id) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andProjectIdEqualTo(id);
        List<ProjectDetailDTO> projectDetailDTOS = projectMapper.selectProjectListByExample(example);
        if (projectDetailDTOS.size() > 0) {
            return projectDetailDTOS.get(0);
        }
        return null;
    }

    @Override
    public Project saveOrUpdate(Project project) {
        Date now = new Date();
        if (project.getProjectId() != null) {
            Project oldProject = projectMapper.selectByPrimaryKey(project.getProjectId());
            BeanUtils.copyProperties(project, oldProject);
            oldProject.setBrief(getBrief(oldProject));
            oldProject.setUpdateTime(now);
            int i = projectMapper.updateByPrimaryKey(oldProject);
        } else {
            project.setBrief(getBrief(project));
            project.setUpdateTime(now);
            int insert = projectMapper.insert(project);
        }
        return project;
    }

    private String getBrief(Project project) {
        String brief;
        int length = project.getDescription().length();
        if (length > 200) {
            brief = project.getDescription().substring(0, 200);
        } else {
            brief = project.getDescription();
        }
        return brief;
    }

    @Override
    public Result deleteProjectById(Integer id) {
        //check whether there is a project preference
        ProjectPreferenceExample preferenceExample = new ProjectPreferenceExample();
        preferenceExample.createCriteria().andProjectIdEqualTo(id);
        List<ProjectPreference> projectPreferences = projectPreferenceMapper.selectByExample(preferenceExample);
        if (projectPreferences.size() > 0) {
            return ResultUtil.error(ResultEnum.PROJECT_OCCUPIED);
        }
        int i = projectMapper.deleteByPrimaryKey(id);
        return ResultUtil.success();
    }

    @Override
    public Integer selectClientIDByProjectName(String projectName) {

        Integer clientId = 0;

        if (!(projectName.isEmpty())) {
            ProjectExample example = new ProjectExample();
            example.createCriteria().andNameEqualTo(projectName);
            List<Project> client = projectMapper.selectByExample(example);
            if (!(client.isEmpty())) {
                clientId = client.get(0).getClientId();
            }
        }
        return clientId;
    }
}
