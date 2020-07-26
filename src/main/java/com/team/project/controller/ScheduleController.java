package com.team.project.controller;

import com.team.project.dto.Result;
import com.team.project.dto.ScheduleDTO;
import com.team.project.model.Group;
import com.team.project.model.MeetingSchedule;
import com.team.project.model.Project;
import com.team.project.model.User;
import com.team.project.service.ScheduleService;
import com.team.project.util.ResultUtil;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;

/**
 * by HuBo on 5/12/2020
 */
@Controller
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/schedule")
    public String scheduleRoute(Model model,HttpServletRequest request) {

        List<ScheduleDTO> scheduleDTOList;

        User user = (User) SessionUtils.getAttribute(request, "user");

        if (user.getUserType() == 4)//admin
            scheduleDTOList = scheduleService.selectScheduleList();

        else if (user.getUserType() == 3)//facilitator
        {
            scheduleDTOList = scheduleService.selectScheduleListByFacilitator(user.getUserId());

        }
        else if (user.getUserType() == 2)//client
        {
            scheduleDTOList = scheduleService.selectScheduleListByClient(user.getUserId());
        }
        else{
            int groupId = -1;
            try {
                groupId = user.getStudentGroupId();
            }
            catch (NullPointerException e){

            }
            scheduleDTOList = scheduleService.selectScheduleByGroup(groupId);
        }

        model.addAttribute("schedules",scheduleDTOList);


        return "schedule";
    }

    /**
     * generate schedules
     *
     * @return success or not
     */
    @GetMapping("/schedule/generate")
    @ResponseBody
    public Result generateSchedules() {
        return scheduleService.generateSchedules();
    }

    /**
     * update the selected schedules
     *
     * @param meetingScheduleList project object received from client
     * @return success or not
     */
    @PostMapping("/schedule/update")
    @ResponseBody
    public Result updateSchedules(@RequestBody @Validated List<MeetingSchedule> meetingScheduleList) {

        scheduleService.Update(meetingScheduleList);
        return ResultUtil.success();
    }
}
