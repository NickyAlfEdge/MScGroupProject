package com.team.project.mapper;

import com.team.project.model.StudentMark;
import com.team.project.model.StudentMarkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMarkMapper {

    long countByExample(StudentMarkExample example);

    int deleteByExample(StudentMarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentMark record);

    int insertSelective(StudentMark record);

    List<StudentMark> selectByExample(StudentMarkExample example);

    StudentMark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentMark record, @Param("example") StudentMarkExample example);

    int updateByExample(@Param("record") StudentMark record, @Param("example") StudentMarkExample example);

    int updateByPrimaryKeySelective(StudentMark record);

    int updateByPrimaryKey(StudentMark record);
}
