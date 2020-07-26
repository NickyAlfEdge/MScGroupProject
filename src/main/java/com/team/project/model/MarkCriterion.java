package com.team.project.model;

import java.io.Serializable;
import java.util.Date;

public class MarkCriterion implements Serializable {

    private Integer criterionId;
    private String criterionName;
    private Integer total;
    private Date updateTime;
    private Date createTime;
    private static final long serialVersionUID = 1L;

    public Integer getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(Integer criterionId) {
        this.criterionId = criterionId;
    }

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
                ", criterionId=" + criterionId +
                ", criterionName=" + criterionName +
                ", total=" + total +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
