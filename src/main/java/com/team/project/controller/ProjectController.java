package com.team.project.controller;

import com.team.project.constant.ResultEnum;
import com.team.project.dto.CommonPage;
import com.team.project.dto.ProjectDetailDTO;
import com.team.project.dto.ProjectPreferenceDTO;
import com.team.project.dto.Result;
import com.team.project.model.*;
import com.team.project.service.ProjectPreferenceService;
import com.team.project.service.ProjectService;
import com.team.project.util.ResultUtil;
import com.team.project.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ZQJ
 * @date 5/8/2020
 */
@Controller
@Validated
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectPreferenceService projectPreferenceService;

    /**
     * project page router, if assign pageNum, return specific page's content.
     *
     * @param model       model
     * @param projectName projectName used to search project list
     * @param pageSize    page size
     * @param pageNum     page number
     * @return page info
     */
    @GetMapping("/project")
    public String projectRoute(Model model,
                               @RequestParam(required = false) String projectName,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        List<ProjectDetailDTO> projects = projectService.selectProjectList(projectName, pageSize, pageNum);
        model.addAttribute("projects", projects);
        model.addAttribute("pageInfo", CommonPage.restPage(projects));
        return "project";
    }

    /**
     * query project detail (client info, tags)
     *
     * @param projectName project name used to search
     * @param pageSize    page size
     * @param pageNum     page num
     * @return project detail list
     */
    @GetMapping("/projectDetailList")
    @ResponseBody
    public Result projectDetailList(@RequestParam(required = false) String projectName,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                    @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        List<ProjectDetailDTO> detailDTOs = projectService.selectProjectList(projectName, pageSize, pageNum);
        return ResultUtil.success(CommonPage.restPage(detailDTOs));
    }

    /**
     * project detail page router
     *
     * @param id    projectId
     * @param model Model
     * @return project detail template
     */
    @GetMapping("/project/detail/{id}")
    public String projectDetailRouter(@PathVariable Integer id, Model model) {
        ProjectDetailDTO projectDetail = projectService.selectProjectById(id);
        List<Group> groups = projectPreferenceService.selectGroupByProjectId(id);
        model.addAttribute("project", projectDetail);
        model.addAttribute("groups", groups);
        return "project/detail";
    }

    /**
     * query project detail
     *
     * @param id project id
     * @return project detail
     */
    @GetMapping("/project/detail/query/{id}")
    @ResponseBody
    public Result queryProjectDetail(@PathVariable Integer id) {
        ProjectDetailDTO projectDetail = projectService.selectProjectById(id);
        List<Group> groups = projectPreferenceService.selectGroupByProjectId(id);
        projectDetail.setGroups(groups);
        return ResultUtil.success(projectDetail);
    }

    /**
     * save project
     *
     * @param project project object received from client
     * @param request http request
     * @return success or not
     */
    @PostMapping("/project/save")
    @ResponseBody
    public Result saveProject(@RequestBody @Valid Project project, HttpServletRequest request) {
        if (project.getName() == null || project.getName().isEmpty()) {
            return ResultUtil.error(ResultEnum.PARAMS_ERROR);
        }
        if (project.getDescription() == null || project.getDescription().isEmpty()
                || project.getTagId() == null) {
            return ResultUtil.error(ResultEnum.PARAMS_ERROR);
        }
        Byte status = project.getStatus();
        if (status == null) {
            project.setStatus(Byte.valueOf("2"));
        }
        //TODO: client should get from session
        User user = (User) SessionUtils.getAttribute(request, "user");
        project.setClientId(user.getUserId());
        projectService.saveOrUpdate(project);
        return ResultUtil.success();
    }

    /**
     * delete project by id
     *
     * @param id project id
     * @return success or not
     */
    @GetMapping("/project/delete/{id}")
    @ResponseBody
    public Result deleteProject(@PathVariable Integer id) {
        return projectService.deleteProjectById(id);
    }


    /**
     * select all Groups and the projects they have chosen
     *
     * @param groupName   group name
     * @param projectName project name
     * @param pageSize    page size
     * @param pageNum     page num
     * @return groups and corresponding projects template
     */
    @GetMapping("/projectPreference/list")
    public String groupProjectList(@RequestParam(required = false) String groupName,
                                   @RequestParam(required = false) String projectName,
                                   @RequestParam(required = false, defaultValue = "20") Integer pageSize,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   HttpServletRequest request,
                                   Model model) {
        User user = (User) SessionUtils.getAttribute(request, "user");
        // if this user is a student, return his own project
        if (user.getUserType() == 1) {
            List<ProjectPreferenceDTO> list = projectPreferenceService.selectGroupProjectByStudentId(user.getUserId());
            model.addAttribute("myProject", list);
            if (list != null && list.size() > 0 && list.get(0).getProjectId() != null) {
                model.addAttribute("project", projectService.selectProjectById(list.get(0).getProjectId()));
            }
        } else {
            List<ProjectPreferenceDTO> list = projectPreferenceService.selectGroupProjectList(groupName, projectName, pageSize, pageNum);
            model.addAttribute("pageInfo", CommonPage.restPage(list));
            List<ProjectDetailDTO> projectDetailDTOS = projectService.selectSuitableProjectList();
            model.addAttribute("projects", projectDetailDTOS);
        }
        return "project/preference";
    }

    @PostMapping("/projectPreference/save")
    @ResponseBody
    public Result saveGroupProjectPreference(@RequestBody ProjectPreference preference) {
        projectPreferenceService.saveOrUpdatePreference(preference);
        return ResultUtil.success();
    }

    @GetMapping("/project/choose/{id}")
    @ResponseBody
    public Result chooseProjectPreference(@PathVariable(name = "id") Integer projectId, HttpServletRequest request) {
        User user = (User) SessionUtils.getAttribute(request, "user");
        return projectPreferenceService.choosePreference(projectId, user.getUserId());
    }
}
