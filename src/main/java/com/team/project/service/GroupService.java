package com.team.project.service;

import com.team.project.dto.GroupDetailDTO;
import com.team.project.dto.Result;
import com.team.project.model.Group;
import com.team.project.model.User;

import java.util.List;

/**
 * @author ZQJ LZQ Nicky Edge
 * @date 5/19/2020
 */
public interface GroupService {
    /**
     * select all group
     * @return groups
     */
    List<GroupDetailDTO> selectGroupList();

    /**
     * select group detail by group ID
     *
     * @param id  - group ID
     * @return    - group result
     */
    GroupDetailDTO selectGroupById(Integer id);

    /**
     * save group
     * @param group  - group
     */
     void saveOrUpdate(Group group);

    /**
     * delete
     * @param id  group ID
     */
    void deleteGroupById(Integer id);

}
