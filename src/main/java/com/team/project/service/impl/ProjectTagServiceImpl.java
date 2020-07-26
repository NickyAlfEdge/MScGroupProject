package com.team.project.service.impl;

import com.team.project.mapper.ProjectTagMapper;
import com.team.project.model.ProjectTag;
import com.team.project.model.ProjectTagExample;
import com.team.project.service.ProjectTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZQJ
 * @date 5/10/2020
 */
@Service
public class ProjectTagServiceImpl implements ProjectTagService {

    @Autowired
    private ProjectTagMapper tagMapper;

    @Override
    public List<ProjectTag> queryAllTags() {
        List<ProjectTag> projectTags = tagMapper.selectByExample(new ProjectTagExample());
        return projectTags;
    }
}
