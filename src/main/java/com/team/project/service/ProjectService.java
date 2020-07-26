package com.team.project.service;

import com.team.project.dto.ProjectDetailDTO;
import com.team.project.dto.Result;
import com.team.project.model.Project;

import java.util.List;

/**
 * @author ZQJ
 * @date 5/8/2020
 */
public interface ProjectService {

    /**
     * select all project
     * @return projects
     */
    List<ProjectDetailDTO> selectProjectList();

    /**
     * select project list by given criterion
     * @param projectName  project name optional
     * @param pageSize page size
     * @param pageNum page number
     * @return projects
     */
    List<ProjectDetailDTO> selectProjectList(String projectName, Integer pageSize, Integer pageNum);

    /**
     * select project detail by project ID
     *
     * @param id    - project ID
     * @return      - project result
     */
    ProjectDetailDTO selectProjectById(Integer id);

    /**
     * save project
     * if there is old project, update old project
     * @param project   - project
     * @return          - result
     */
    Project saveOrUpdate(Project project);

    /**
     * delete
     * @param id project id
     * @return result
     */
    Result deleteProjectById(Integer id);

    Integer selectClientIDByProjectName(String projectName);

    /**
     * search projects which has been passed verify
     * @return
     */
    List<ProjectDetailDTO> selectSuitableProjectList();
}
