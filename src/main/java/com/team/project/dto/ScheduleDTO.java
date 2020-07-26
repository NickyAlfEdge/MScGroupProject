package com.team.project.dto;

import com.team.project.model.Group;
import com.team.project.model.MeetingSchedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTO {
    private Group group;
    private String client;
    private String facilitator;
    private int day;
    private List<MeetingSchedule> groupSchedules;


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<MeetingSchedule> getGroupSchedules() {
        return groupSchedules;
    }

    public void setGroupSchedules(List<MeetingSchedule> groupSchedules) {
        this.groupSchedules = groupSchedules;
    }
}
