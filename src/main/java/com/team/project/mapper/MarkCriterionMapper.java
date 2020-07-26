package com.team.project.mapper;

import com.team.project.model.MarkCriterion;
import com.team.project.model.MarkCriterionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarkCriterionMapper {
    long countByExample(MarkCriterionExample example);

    int deleteByExample(MarkCriterionExample example);

    int deleteByPrimaryKey(Integer criterionId);

    int insert(MarkCriterion record);

    int insertSelective(MarkCriterion record);

    List<MarkCriterion> selectByExample(MarkCriterionExample example);

    MarkCriterion selectByPrimaryKey(Integer criterionId);

    int updateByExampleSelective(@Param("record") MarkCriterion record, @Param("example") MarkCriterionExample example);

    int updateByExample(@Param("record") MarkCriterion record, @Param("example") MarkCriterionExample example);

    int updateByPrimaryKeySelective(MarkCriterion record);

    int updateByPrimaryKey(MarkCriterion record);
}
