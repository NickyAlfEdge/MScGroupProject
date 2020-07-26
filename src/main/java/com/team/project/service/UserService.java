package com.team.project.service;

import com.team.project.dto.Result;
import com.team.project.model.Project;
import com.team.project.model.User;

import java.util.List;

/**
 * @author ZQJ
 * @date 4/23/2020
 */
public interface UserService {

    User queryById(Integer userId);

    /**
     * verify user's login info
     *
     * @param user login info
     * @return user
     */
    User loginVerify(User user);

    /**
     * query users by criterion
     *
     * @param user  - users login details
     * @return      - list of queried user results
     */
    List<User> queryList(User user);

    /**
     * query users by criterion
     *
     */
    List<User> queryList();

    /**
     * create User
     * @param user      - user
     * @return          - result
     */
    User saveOrUpdate(User user);

    /**
     * query all users
     * @param       - user
     * @return      - result
     */
    List<User> queryAllUsers();

    /**
     * delete
     * @param id user id
     * @return result
     */
    Result deleteUserById(Integer id);
}
