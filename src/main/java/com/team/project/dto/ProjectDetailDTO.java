package com.team.project.dto;

import com.team.project.model.Group;
import com.team.project.model.Project;
import com.team.project.model.ProjectTag;
import com.team.project.model.User;

import java.util.List;

/**
 * @author ZQJ
 * @date 5/8/2020
 */
public class ProjectDetailDTO extends Project {

    private User user;

    private List<ProjectTag> projectTags;

    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProjectTag> getProjectTags() {
        return projectTags;
    }

    public void setProjectTags(List<ProjectTag> projectTags) {
        this.projectTags = projectTags;
    }
}
