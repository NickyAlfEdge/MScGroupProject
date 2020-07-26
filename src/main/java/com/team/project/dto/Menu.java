package com.team.project.dto;

import com.team.project.model.Permission;

import java.util.List;

/**
 * @author ZQJ
 * @date 4/28/2020
 */
public class Menu extends Permission {

    List<Menu> childList;

    public List<Menu> getChildList() {
        return childList;
    }

    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }
}
