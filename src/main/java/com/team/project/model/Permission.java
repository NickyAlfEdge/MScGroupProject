package com.team.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Permission implements Serializable {

    private Integer permitId;
    private Integer pid;
    private Byte functionType;
    private String functionUrl;
    private String functionName;
    private Integer functionSeq;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;

    public Integer getPermitId() {
        return permitId;
    }

    public void setPermitId(Integer permitId) {
        this.permitId = permitId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Byte functionType) {
        this.functionType = functionType;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Integer getFunctionSeq() {
        return functionSeq;
    }

    public void setFunctionSeq(Integer functionSeq) {
        this.functionSeq = functionSeq;
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
                ", permitId=" + permitId +
                ", pid=" + pid +
                ", functionType=" + functionType +
                ", functionUrl=" + functionUrl +
                ", functionSeq=" + functionSeq +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
