package com.team.project.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {

    private Integer projectId;
    @NotNull(message = "project name cannot be null")
    private String name;
    private Byte status;
    private String brief;
    @NotNull(message = "description cannot be null")
    private String description;
    private Integer tagId;
    private Integer clientId;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", projectId=" + projectId +
                ", name=" + name +
                ", status=" + status +
                ", brief=" + brief +
                ", description=" + description +
                ", tagId=" + tagId +
                ", clientId=" + clientId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
