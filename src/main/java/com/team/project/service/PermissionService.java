package com.team.project.service;

import com.team.project.dto.Menu;
import com.team.project.model.Permission;

import java.util.List;

/**
 * @author ZQJ
 * @date 4/28/2020
 */
public interface PermissionService {

    /**
     * get all Menu list
     *
     * @return      -
     */
    List<Menu> getMenuList();
}
