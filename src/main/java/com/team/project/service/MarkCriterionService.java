package com.team.project.service;

import com.team.project.model.MarkCriterion;

import java.util.List;

/**
 * @author      Nicky Edge
 * @date        19/05/2020
 */
public interface MarkCriterionService {


    /**
     * query students by userID
     *
     * @param userId    - queried user ID
     */
    MarkCriterion queryById(Integer userId);

    /**
     * query all users
     *
     */
    List<MarkCriterion> queryList();

    /**
     * save or update Criterion
     *
     * @param newMark   - student mark
     */
    void saveOrEditCriterion(MarkCriterion newMark);


    /**
     * delete all Criterion
     */
    void deleteAllCriterion();
}
