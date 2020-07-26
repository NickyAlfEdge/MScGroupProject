package com.team.project.model;

import java.io.Serializable;
import java.util.Date;

public class StudentMark implements Serializable {

    private Integer id;
    private Integer studentId;
    private Integer raterId;
    private Integer criterionId;
    private Integer mark;
    private Date updateTime;
    private Date createTime;
    private static final long serialVersionUID = 1L;

    public StudentMark() {
    }

    public StudentMark(Integer studentId) {
        this.studentId = studentId;
    }

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

    public Integer getRaterId() {
        return raterId;
    }

    public void setRaterId(Integer raterId) {
        this.raterId = raterId;
    }

    public Integer getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(Integer criterionId) {
        this.criterionId = criterionId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", studentId=" + studentId +
                ", raterId=" + raterId +
                ", criterionId=" + criterionId +
                ", mark=" + mark +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
