package com.team.project.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private Integer userId;
    private String surname;
    private String forename;
    private String email;
    private String password;
    @ApiModelProperty(value = "1: student, 2: client, 3: facilitator, 4: administrator")
    private Byte userType;
    private Integer studentGroupId;
    private Date createTime;
    private Date updateTime;
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Integer userId, String surname, String forename, String email, Integer studentGroupId) {
        this.userId = userId;
        this.surname = surname;
        this.forename = forename;
        this.email = email;
        this.studentGroupId = studentGroupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Integer getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(Integer studentGroupId) {
        this.studentGroupId = studentGroupId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", userId=" + userId +
                ", surname=" + surname +
                ", forename=" + forename +
                ", email=" + email +
                ", password=" + password +
                ", userType=" + userType +
                ", studentGroupId=" + studentGroupId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
