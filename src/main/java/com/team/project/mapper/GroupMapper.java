package com.team.project.mapper;

import com.team.project.dto.GroupDetailDTO;
import com.team.project.model.Group;
import com.team.project.model.GroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    long countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    int insertSelective(Group record);

    List<Group> selectByExample(GroupExample example);

    Group selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List<GroupDetailDTO> selectGroupListByExample(GroupExample example);
}
