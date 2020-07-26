package com.team.project.dto;

import java.util.Objects;

public class StudentRecord {

    private Integer userId;
    private String surname;
    private String forename;
    private String email;
    private Integer studentGroupId;
    private Integer markId;
    private Integer grade;

    public StudentRecord(Integer userId, String surname, String forename, String email, Integer studentGroupId, Integer markId, Integer grade) {
        this.userId = userId;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.studentGroupId = studentGroupId;
        this.markId = markId;
        this.grade = grade;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSurname() {
        return surname;
    }

    public String getForename() {
        return forename;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStudentGroupId() {
        return studentGroupId;
    }

    public Integer getMarkId() {
        return markId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudentGroupId(Integer studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}