package com.team.project.controller;

import com.team.project.dto.CommonPage;
import com.team.project.dto.ProjectDetailDTO;
import com.team.project.dto.Result;
import com.team.project.model.Group;
import com.team.project.model.Project;
import com.team.project.model.User;
import com.team.project.service.ProjectService;
import com.team.project.service.UserService;
import com.team.project.util.ResultUtil;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YCY
 * @date 5/19/2020
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    //private ProjectService projectService;

    @GetMapping("/userManagement")

    public String userManagementRoute(Model model,
                               //@RequestParam(required = false) String userId,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        //List<ProjectDetailDTO> projects = projectService.selectProjectList(projectName, pageSize, pageNum);
        List<User> users = userService.queryAllUsers();
        //List<ProjectDetailDTO> projects = projectService.selectProjectList(userId,pageSize,pageNum);
        model.addAttribute("users", users);
        //model.addAttribute("pageInfo", CommonPage.restPage(projects));
        model.addAttribute("pageInfo",CommonPage.restPage(users));
        return "userManagement";
    }
    /**
     * create user
     *
     * @param user user object received from client
     * @param request http request
     * @return success or not
     */
    @PostMapping(path = "/usermanagement/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Result saveUser(@RequestBody @Validated User user, HttpServletRequest request){

        //TODO: client should get from session
        User currentUser = (User) SessionUtils.getAttribute(request, "user");
        Integer userId = user.getUserId();
        String surname = user.getSurname();
        String forename = user.getForename();
        String password = user.getPassword();
        String email = user.getEmail();
        Byte userType = user.getUserType();
        if (userId == null){
            System.out.println("1");
            return ResultUtil.error();
        }
        if (surname == null){
            System.out.println("1");
            return ResultUtil.error();
        }
        if (forename == null){
            System.out.println("1");
            return ResultUtil.error();
        }
        if (userType == null) {
            System.out.println("1");
            return ResultUtil.error();
        }
        if (email == null){
            System.out.println("1");
            return ResultUtil.error();
        }
        if (password == null){
            System.out.println("1");
            return ResultUtil.error();
        }
        user.setUserType(Byte.valueOf(user.getUserType()));
        System.out.println(surname+forename);
        //create time part
        user = userService.saveOrUpdate(user);
        return ResultUtil.success();

    }
    /**
     * query user detail
     *
     * @param id user id
     * @return user detail
     */
    @GetMapping("/usermanagement/edit/{id}")
    @ResponseBody
    public Result queryUserDetail(@PathVariable String id,Model model) {
        //delete all ','
        id = id.replaceAll(",","");
        //System.out.println(id);
        int intId = Integer.parseInt(id);
        User user = userService.queryById(intId);
        //System.out.println(user.getUserId());
        model.addAttribute("user1", user);
        //System.out.println(user.getUserId());
        return ResultUtil.success(user);
    }
    /**
     * delete user by id
     *
     * @param id user id
     * @return success or not
     */
    @GetMapping("/usermanagement/delete/{id}")
    @ResponseBody
    public Result deleteProject(@PathVariable String id) {
        //delete all ','
        id = id.replaceAll(",","");
        //System.out.println(id);
        int intId = Integer.parseInt(id);
        return userService.deleteUserById(intId);
    }



}
