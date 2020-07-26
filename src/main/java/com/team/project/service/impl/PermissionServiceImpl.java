package com.team.project.service.impl;

import com.team.project.dto.Menu;
import com.team.project.mapper.PermissionMapper;
import com.team.project.model.Permission;
import com.team.project.model.PermissionExample;
import com.team.project.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZQJ
 * @date 4/28/2020
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Menu> getMenuList() {
        PermissionExample example = new PermissionExample();
        List<Permission> permissions = permissionMapper.selectByExample(example);
        List<Menu> menuArrayList = new ArrayList<>();
        if (permissions.size() > 0) {
            for (Permission permission : permissions) {
                if (permission.getPid().equals(0)) {
                    Menu menu = findChildPermission(permission, permissions);
                    menuArrayList.add(menu);
                }
            }
        }
        return menuArrayList;
    }

    private Menu findChildPermission(Permission parent, List<Permission> permissions) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(parent, menu);
        ArrayList<Menu> menus = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission.getPid().equals(parent.getPermitId())) {
                Menu childPermission = findChildPermission(permission, permissions);
                menus.add(childPermission);
            }
        }
        menu.setChildList(menus);
        return menu;
    }


}
