package com.team.project.mapper;

import com.team.project.model.ProjectTag;
import com.team.project.model.ProjectTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectTagMapper {
    long countByExample(ProjectTagExample example);

    int deleteByExample(ProjectTagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(ProjectTag record);

    int insertSelective(ProjectTag record);

    List<ProjectTag> selectByExample(ProjectTagExample example);

    ProjectTag selectByPrimaryKey(Integer tagId);

    int updateByExampleSelective(@Param("record") ProjectTag record, @Param("example") ProjectTagExample example);

    int updateByExample(@Param("record") ProjectTag record, @Param("example") ProjectTagExample example);

    int updateByPrimaryKeySelective(ProjectTag record);

    int updateByPrimaryKey(ProjectTag record);
}
