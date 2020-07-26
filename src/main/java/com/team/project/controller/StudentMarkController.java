package com.team.project.controller;

import com.team.project.dto.Result;
import com.team.project.dto.StudentRecord;
import com.team.project.model.MarkCriterion;
import com.team.project.model.StudentMark;
import com.team.project.model.User;
import com.team.project.service.*;
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
 * @author Nicky Edge
 * @date 12/05/2020
 */
@Controller
public class StudentMarkController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectPreferenceService projectPreferenceService;
    @Autowired
    private UserMarkService userMarkService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MarkCriterionService markCriterionService;
    private User activeUser;

    /**
     * Assessment main page routing and MVC data
     *
     * @param model             - assessment mvc
     * @param request           - Http request to retrieve the sessions User credentials
     * @return  /assessment page
     */
    @GetMapping("/assessment")
    public String studentMarkManagementRoute(Model model, HttpServletRequest request) {

        activeUser = getActiveUser(request);
        if (activeUser.getUserType() == 1) {
            String project = getActiveUserProject();
            String clientName = getActiveUserClientName(project);
            Integer mark = getActiveUserMark();
            List<MarkCriterion> criterion = markCriterionService.queryList();

            model.addAttribute("criterion", criterion);
            model.addAttribute("userProject", project);
            model.addAttribute("clientName", clientName);
            model.addAttribute("userMark", mark);
            model.addAttribute("activeUser", activeUser);
        } else if (activeUser.getUserType() == 4) {
            List<User> students = userService.queryList();
            List<StudentMark> marks = userMarkService.queryList();
            List<MarkCriterion> criterion = markCriterionService.queryList();
            ArrayList<StudentRecord> currentStudents = populateStdRecWithMark(students, marks);

            model.addAttribute("criterion", criterion);
            model.addAttribute("students", currentStudents);
            model.addAttribute("activeUser", activeUser);
        } else {
            List<MarkCriterion> criterion = markCriterionService.queryList();

            model.addAttribute("criterion", criterion);
            model.addAttribute("activeUser", activeUser);
        }

        return "assessment";
    }

    /**
     * create datatype objects for subsequent display within datatable
     *
     * @param currentStudents       - List of current users of type student
     * @param currentMarks          - List of current student marks
     * @return ArrayList<StudentRecord>
     */
    private ArrayList<StudentRecord> populateStdRecWithMark(List<User> currentStudents, List<StudentMark> currentMarks) {

        ArrayList<StudentRecord> students = new ArrayList<>();
        int mark = 0;

        for (User student : currentStudents) {
            StudentRecord stu = new StudentRecord(student.getUserId(), student.getForename(), student.getSurname(),
                    student.getEmail(), student.getStudentGroupId(), mark, mark);

            StudentRecord validatedStudent = validateRecord(stu);
            for (StudentMark marks : currentMarks) {
                if (student.getUserId().equals(marks.getStudentId())) {
                    validatedStudent.setMarkId(marks.getId());
                    validatedStudent.setGrade(marks.getMark());
                }
            }
            students.add(validatedStudent);
        }
        return students;
    }

    /**
     * check for null fields within the StudentRecord, assigning a value where necessary
     *
     * @param student       - the individual student record being validated
     * @return validated StudentRecord
     */
    private StudentRecord validateRecord(StudentRecord student) {

        if (student.getUserId() == null) {
            student.setUserId(0);
        }
        if (student.getForename() == null) {
            student.setForename("No Name");
        }
        if (student.getSurname() == null) {
            student.setForename("No Surname");
        }
        if (student.getEmail() == null) {
            student.setEmail("No Email");
        }
        if (student.getStudentGroupId() == null) {
            student.setStudentGroupId(0);
        }
        return student;
    }

    /**
     * save new Student Mark
     *
     * @param newMark              - student mark
     * @return success or not
     */
    @PostMapping(path = "/assessment/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Result saveNewMark(@RequestBody @Validated StudentMark newMark) {

        newMark.setRaterId(activeUser.getUserId());
        userMarkService.saveNewMark(newMark);
        return ResultUtil.success();
    }

    /**
     * save new Student Mark
     * Edit Student Mark
     *
     * @param newMark               - student mark
     * @return success or not
     */
    @PostMapping(path = "/assessment/edit", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Result editMark(@RequestBody @Validated StudentMark newMark) {

        newMark.setRaterId(activeUser.getUserId());
        userMarkService.editMark(newMark);
        return ResultUtil.success();
    }

    /**
     * clear all student marks
     *
     * @return success or not
     */
    @PostMapping(path = "/assessment/clearMarks", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Result clearMarks() {

        userMarkService.deleteAllStudentMark();
        return ResultUtil.success();
    }

    /**
     * clear all data within the grading rubric
     *
     * @return success or not
     */
    @PostMapping(path = "/assessment/clearRubric", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Result clearRubric() {

        markCriterionService.deleteAllCriterion();
        return ResultUtil.success();
    }

    /**
     * save new Student Mark
     *
     * @param newMark              - new rubric criterion
     * @return success or not
     */
    @PostMapping(path = "/assessment/updateRubric", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Result updateRubric(@RequestBody @Validated MarkCriterion newMark) {

        markCriterionService.saveOrEditCriterion(newMark);
        return ResultUtil.success();
    }

    /**
     * project page router, if assign pageNum, return specific page's content.
     *
     * @param criterionId       - the queried criterionID
     */
    @GetMapping("/oldCriterion")
    @ResponseBody
    public String criterionRoute(Integer criterionId) {

        String criterionText;
        if (criterionId != null) {
            MarkCriterion criterion = markCriterionService.queryById(criterionId);
            if (criterion == null) {
                criterionText = "";
            } else {
                criterionText = criterion.getCriterionName();
            }
            return criterionText;
        } else {
            return "";
        }
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
     *
     *
     * @param projectName       - name of the queried project
     * @return the client responsible for the project
     */
    private String getActiveUserClientName(String projectName) {

        int clientId = projectService.selectClientIDByProjectName(projectName);
        if (clientId == 0) {
            return "No Client";
        } else {
            User client = userService.queryById(clientId);
            return client.getForename() + " " + client.getSurname();
        }
    }

    /**
     * get the active users project name
     *
     * @return the project name for the corresponding user
     */
    private String getActiveUserProject() {

        String userProject = projectPreferenceService.selectActiveStudentProjectByGroupID(activeUser);
        String noProj = "No Project Selected";
        if (userProject == null) {
            return noProj;
        } else {
            return userProject;
        }
    }

    /**
     * retrieve the active users current mark
     *
     * @return the active users mark.
     */
    private Integer getActiveUserMark() {

        StudentMark studentMark = userMarkService.queryById(activeUser.getUserId());
        Integer noMark = 0;
        if (studentMark == null) {
            return noMark;
        } else {
            return studentMark.getMark();
        }
    }
}
