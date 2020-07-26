package com.team.project.controller;

import com.team.project.model.User;
import com.team.project.service.UserService;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZQJ/Nicky Edge
 * @date 4/28/2020
 */
@Controller
public class RouterController {

    @Autowired
    UserService userService;

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpServletRequest request) {
        User activeUser = getActiveUser(request);
        String userName = getUserName(activeUser);
        model.addAttribute("username", userName);

        return "index";
    }

    /**
     * get the active user from the session
     *
     * @param request       - http session request
     * @return the active user of the session
     */
    private User getActiveUser(HttpServletRequest request) {
        return (User) SessionUtils.getAttribute(request, "user");
    }

    /**
     * Get the active users name for index display
     *
     * @param activeUser    - the current sessions user
     * @return the users name/account name
     */
    private String getUserName(User activeUser) {
        if (activeUser.getForename() == null || activeUser.getSurname() == null) {
            return "Invalid User";
        } else {
            return activeUser.getForename() + " " + activeUser.getSurname();
        }
    }

    @GetMapping("/login")
    public String loginRoute() {
        return "login";
    }
}
