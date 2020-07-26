package com.team.project.service;

import com.team.project.dto.ProjectPreferenceDTO;
import com.team.project.dto.Result;
import com.team.project.model.Group;
import com.team.project.model.ProjectPreference;
import com.team.project.model.User;

import java.util.List;

/**
 * @author ZQJ
 * @date 5/9/2020
 */
public interface ProjectPreferenceService {

    /**
     * search groups which have choose this project
     *
     * @param projectId     - project Id
     * @return              - groups
     */
    List<Group> selectGroupByProjectId(Integer projectId);

    /**
     * search all groups info and the projects which they have chosen
     *
     * @param groupName     -
     * @param projectName   -
     * @param pageSize      -
     * @param pageNum       -
     * @return              -
     */
    List<ProjectPreferenceDTO> selectGroupProjectList(String groupName, String projectName, Integer pageSize, Integer pageNum);

    /**
     * save or update project preference
     *
     * @param preference    -
     */
    void saveOrUpdatePreference(ProjectPreference preference);

    /**
     * group chooses project (select user's group id again, cuz the user in session may not be updated)
     *
     * @param projectId     - project id
     * @param userId        - student user id
     * @return          -
     */
    Result choosePreference(Integer projectId, Integer userId);

    /**
     * select group project by student id (select user's group id again, cuz the user in session may not be updated)
     *
     * @param userId    student id (user id)
     * @return          -
     */
    List<ProjectPreferenceDTO> selectGroupProjectByStudentId(Integer userId);

    /**
     * retrieve an individual students project
     *
     * @param activeUser        - the active user of the session
     */
    String selectActiveStudentProjectByGroupID(User activeUser);
}
