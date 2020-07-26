package com.team.project.model;

import java.io.Serializable;
import java.util.Date;

public class GroupPreference implements Serializable {

    private Integer id;
    private Integer studentId;
    private Integer likePersonId;
    private Integer dislikePersonId;
    private Integer dislikePersonTwoId;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getLikePersonId() {
        return likePersonId;
    }

    public void setLikePersonId(Integer likePersonId) {
        this.likePersonId = likePersonId;
    }

    public Integer getDislikePersonId() {
        return dislikePersonId;
    }

    public void setDislikePersonId(Integer dislikePersonId) {
        this.dislikePersonId = dislikePersonId;
    }

    public Integer getDislikePersonTwoId() {
        return dislikePersonTwoId;
    }

    public void setDislikePersonTwoId(Integer dislikePersonTwoId) {
        this.dislikePersonTwoId = dislikePersonTwoId;
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
                ", id=" + id +
                ", studentId=" + studentId +
                ", likePersonId=" + likePersonId +
                ", dislikePersonId=" + dislikePersonId +
                ", dislikePersonTwoId=" + dislikePersonTwoId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
