package com.team.project.dto;

import com.team.project.model.Group;
import com.team.project.model.User;

import java.util.List;

public class GroupDetailDTO extends Group {
    private List<User> groupMembers;

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<User> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
