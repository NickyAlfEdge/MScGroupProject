package com.team.project.controller;

import com.team.project.constant.ConstParams;
import com.team.project.constant.ResultEnum;
import com.team.project.dto.Result;
import com.team.project.model.User;
import com.team.project.service.UserService;
import com.team.project.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ZQJ
 * @date 4/25/2020
 */
@Controller
@Validated
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    public UserService userService;

    /**
     * login verify method
     *
     * @return result
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody @Validated User user, HttpServletRequest request, HttpServletResponse response) {
        logger.info("request: " + user);
        user = userService.loginVerify(user);
        if (user == null) {
            return ResultUtil.error(ResultEnum.ILLEGAL_USER);
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(ConstParams.SESSION_EXPIRE);
        session.setAttribute("user", user);

        return ResultUtil.success(user);
    }

    /**
     * logout and invalidate session
     *
     * @param request   - logout request
     * @return          - logout result
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return ResultUtil.success();
    }
}
