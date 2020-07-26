package com.team.project.service;

import com.team.project.model.StudentMark;

import java.util.List;

/**
 * @author      Nicky Edge
 * @date        17/05/2020
 */
public interface UserMarkService {

    /**
     * query students by userID
     *
     * @param userId    - queried user ID
     */
    StudentMark queryById(int userId);

    /**
     * query all users
     *
     */
    List<StudentMark> queryList();

    /**
     * save Mark
     *
     * @param newMark   - student mark
     */
    void saveNewMark(StudentMark newMark);

    /**
     * edit Mark
     *
     * @param newMark   - new Mark
     */
    void editMark(StudentMark newMark);

    /**
     * delete all Student Marks
     */
    void deleteAllStudentMark();
}