package com.team.project.mapper;

import com.team.project.model.GroupPreference;
import com.team.project.model.GroupPreferenceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupPreferenceMapper {
    long countByExample(GroupPreferenceExample example);

    int deleteByExample(GroupPreferenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupPreference record);

    int insertSelective(GroupPreference record);

    List<GroupPreference> selectByExample(GroupPreferenceExample example);

    GroupPreference selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupPreference record, @Param("example") GroupPreferenceExample example);

    int updateByExample(@Param("record") GroupPreference record, @Param("example") GroupPreferenceExample example);

    int updateByPrimaryKeySelective(GroupPreference record);

    int updateByPrimaryKey(GroupPreference record);
}
