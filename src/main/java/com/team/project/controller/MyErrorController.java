package com.team.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ZQJ
 * @date 5/8/2020
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletResponse res) {
        int status = res.getStatus();
        if (HttpServletResponse.SC_NOT_FOUND == status) {
            return "404";
        } else {
            return "500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
