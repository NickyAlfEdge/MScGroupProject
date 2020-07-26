package com.team.project.controller;

import com.team.project.dto.Menu;
import com.team.project.dto.Result;
import com.team.project.model.Permission;
import com.team.project.model.User;
import com.team.project.service.PermissionService;
import com.team.project.util.ResultUtil;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * control all resources which present user has access to
 *
 * @author ZQJ
 * @date 4/28/2020
 */
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * admin type is 4
     */
    private static Byte adminType = new Byte("4");
    private static Byte clientType = new Byte("2");
    private static Byte facilitatorType = new Byte("3");

    /**
     * get all menu list
     *
     * @return
     */
    @PostMapping("/menu")
    @ResponseBody
    public Result getMenuList(HttpServletRequest request) {
        User user = (User) SessionUtils.getAttribute(request, "user");
        List<Menu> menuList = permissionService.getMenuList();
        if (!adminType.equals(user.getUserType())) {
            menuList.removeIf(menu -> menu.getFunctionName().contains("User"));
        }
        if (clientType.equals(user.getUserType()) || facilitatorType.equals(user.getUserType())) {
            for (Menu menu : menuList) {
                if (menu.getFunctionName().contains("Group")) {
                    menu.getChildList().removeIf(child -> child.getFunctionName().contains("Choose Preference"));
                }
            }
        }
        return ResultUtil.success(menuList);
    }
}
