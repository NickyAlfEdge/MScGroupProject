package com.team.project.service.impl;

import com.team.project.mapper.StudentMarkMapper;
import com.team.project.model.StudentMark;
import com.team.project.model.StudentMarkExample;
import com.team.project.service.UserMarkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Nicky Edge
 * @date 4/23/2020
 */
@Service
public class UserMarkServiceImpl implements UserMarkService {

    @Autowired
    private StudentMarkMapper studentMarkMapper;

    @Override
    public StudentMark queryById(int userId) {
        StudentMarkExample example = new StudentMarkExample();
        example.createCriteria().andStudentIdEqualTo(userId);
        List<StudentMark> studentMark = studentMarkMapper.selectByExample(example);
        if (studentMark.isEmpty()) {
            return null;
        } else {
            return studentMark.get(0);
        }
    }

    @Override
    public List<StudentMark> queryList() {
        StudentMarkExample example = new StudentMarkExample();
        return studentMarkMapper.selectByExample(example);
    }

    @Override
    public void saveNewMark(StudentMark newMark) {
        Date now = new Date();
        newMark.setCreateTime(now);
        studentMarkMapper.insert(newMark);
    }

    @Override
    public void  editMark(StudentMark mark) {
        Date now = new Date();
        StudentMark oldMark = studentMarkMapper.selectByPrimaryKey(mark.getId());
        BeanUtils.copyProperties(mark, oldMark);
        oldMark.setUpdateTime(now);
        studentMarkMapper.updateByPrimaryKey(oldMark);
    }

    @Override
    public void deleteAllStudentMark() {
        StudentMarkExample example = new StudentMarkExample();
        studentMarkMapper.deleteByExample(example);
    }
}