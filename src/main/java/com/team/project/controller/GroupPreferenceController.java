package com.team.project.controller;

import com.team.project.dto.Result;
import com.team.project.model.GroupPreference;
import com.team.project.model.StudentMark;
import com.team.project.model.User;
import com.team.project.service.GroupPreferenceService;
import com.team.project.util.ResultUtil;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GroupPreferenceController {

    @Autowired
    private GroupPreferenceService groupPreferenceService;


    @GetMapping("/chooseGroupPreference")
    public String chooseGroupPreferenceRoute(HttpServletRequest request, Model model) {

        User activeUser = getActiveUser(request);
        List<GroupPreference> groupPreferenceList = groupPreferenceService.selectGroupPreferenceList();

        model.addAttribute("activeUser", activeUser);
        model.addAttribute("preferences", groupPreferenceList);

        return "groupPreference";
    }

    /**
     * get the active user from the session
     *
     * @param request - http session request
     * @return the active user of the session
     */
    private User getActiveUser(HttpServletRequest request) {
        return (User) SessionUtils.getAttribute(request, "user");
    }

    @PostMapping("/groupPreference/save")
    @ResponseBody
    public Result saveGroupPreference(@RequestBody GroupPreference groupPreference) {
        groupPreferenceService.saveOrUpdate(groupPreference);
        return ResultUtil.success();
    }

    @GetMapping("/groupPreference/autoAllocation")
    @ResponseBody
    public Result autoAllocation() {
        try {
            groupPreferenceService.autoAllocation();
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }
}
