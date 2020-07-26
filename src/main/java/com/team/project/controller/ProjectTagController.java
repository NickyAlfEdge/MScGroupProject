package com.team.project.controller;

import com.team.project.dto.Result;
import com.team.project.model.ProjectTag;
import com.team.project.service.ProjectTagService;
import com.team.project.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZQJ
 * @date 5/10/2020
 */
@Controller
public class ProjectTagController {

    @Autowired
    private ProjectTagService projectTagService;

    /**
     * search all tags
     * @return      - search result
     */
    @GetMapping("/projectTag/all")
    @ResponseBody
    public Result queryAllProjectTags() {
        List<ProjectTag> list = projectTagService.queryAllTags();
        return ResultUtil.success(list);
    }

}
