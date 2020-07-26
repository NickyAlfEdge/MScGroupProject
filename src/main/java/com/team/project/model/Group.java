package com.team.project.model;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {

    private Integer groupId;
    private String name;
    private Integer facilitatorId;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(Integer facilitatorId) {
        this.facilitatorId = facilitatorId;
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
                ", groupId=" + groupId +
                ", name=" + name +
                ", facilitatorId=" + facilitatorId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
