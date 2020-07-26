package com.team.project.dto;

import com.team.project.model.ProjectPreference;
import com.team.project.model.User;

import java.util.List;

/**
 * @author ZQJ
 * @date 5/13/2020
 */
public class ProjectPreferenceDTO extends ProjectPreference {

    private String projectName;
    private String groupName;
    private List<User> members;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
