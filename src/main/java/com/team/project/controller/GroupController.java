package com.team.project.controller;

import com.team.project.dto.CommonPage;
import com.team.project.dto.GroupDetailDTO;
import com.team.project.dto.Result;
import com.team.project.model.Group;
import com.team.project.model.User;
import com.team.project.service.GroupService;
import com.team.project.util.ResultUtil;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *@author LZQ
 *@date 5/20/2020
 */
@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * group page router, if assign pageNum, return specific page's content
     *
     * @param model         - mvc of groupList Page
     * @return page info
     */
    @GetMapping("/groupList")
    public String groupManagementRoute(Model model, HttpServletRequest request) {
        User user = (User) SessionUtils.getAttribute(request, "user");
        if (user.getUserType() == 1) {
            GroupDetailDTO groupDetailDTO = groupService.selectGroupById(user.getStudentGroupId());
            model.addAttribute("group", groupDetailDTO);
        } else {
            List<GroupDetailDTO> groups = groupService.selectGroupList();
            model.addAttribute("groups", groups);
            model.addAttribute("pageInfo", CommonPage.restPage(groups));
        }
        return "groupList";
    }

    /**
     * query group details(member info)
     *
     * @return  group detail list
     */
    @GetMapping("/groupDetailList")
    @ResponseBody
    public Result groupDetailList() {
        List<GroupDetailDTO> detailDTOS = groupService.selectGroupList();
        return ResultUtil.success(CommonPage.restPage(detailDTOS));
    }

    /**
     * query Group detail
     *
     * @param groupId   - group Id
     * @return
     */
    @GetMapping("/groupMembers")
    @ResponseBody
    public ArrayList<String[]> queryGroupDetail(Integer groupId) {
        GroupDetailDTO groupDetailDTO = groupService.selectGroupById(groupId);
        return createUserGroupList(groupDetailDTO.getGroupMembers());
    }

    private ArrayList<String[]> createUserGroupList(List<User> group) {

        ArrayList<String[]> thisGroup = new ArrayList<>();

        if (group != null) {
            for (User groupMember : group) {
                String[] member = new String[4];
                member[0] = groupMember.getUserId().toString();
                member[1] = groupMember.getForename();
                member[2] = groupMember.getSurname();
                member[3] = groupMember.getEmail();
                thisGroup.add(member);
            }
        }
        return thisGroup;
    }


    /**
     * save new Student Mark
     *
     * @param group              - group Id
     * @return success or not
     */
    @PostMapping(path = "/groupList/deleteGroup", produces = "application/json")
    @ResponseBody
    public Result deleteGroup(@RequestBody Group group) {
        if (group != null) {
            groupService.deleteGroupById(group.getGroupId());
            return ResultUtil.success();
        } else {
            return ResultUtil.error("Error while deleting group");
        }
    }
}
