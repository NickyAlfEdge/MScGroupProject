package com.team.project.mapper;

import com.team.project.dto.ProjectPreferenceDTO;
import com.team.project.model.Group;
import com.team.project.model.ProjectPreference;
import com.team.project.model.ProjectPreferenceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPreferenceMapper {
    long countByExample(ProjectPreferenceExample example);

    int deleteByExample(ProjectPreferenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPreference record);

    int insertSelective(ProjectPreference record);

    List<ProjectPreference> selectByExample(ProjectPreferenceExample example);

    ProjectPreference selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPreference record, @Param("example") ProjectPreferenceExample example);

    int updateByExample(@Param("record") ProjectPreference record, @Param("example") ProjectPreferenceExample example);

    int updateByPrimaryKeySelective(ProjectPreference record);

    int updateByPrimaryKey(ProjectPreference record);

    List<Group> selectGroupByExample(ProjectPreferenceExample example);

    List<ProjectPreferenceDTO> selectGroupsWithProjectByExample(ProjectPreferenceExample preferenceExample);
}
