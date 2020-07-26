package com.team.project.service.impl;

import com.team.project.mapper.MarkCriterionMapper;
import com.team.project.model.*;
import com.team.project.service.MarkCriterionService;
import com.team.project.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Nicky Edge
 * @date 19/05/2020
 */
@Service
public class MarkCriterionServiceImpl implements MarkCriterionService {

    @Autowired
    private MarkCriterionMapper markCriterionMapper;


    @Override
    public MarkCriterion queryById(Integer criterionId) {
        MarkCriterionExample example = new MarkCriterionExample();
        example.createCriteria().andCriterionIdEqualTo(criterionId);
        List<MarkCriterion> criterionList = markCriterionMapper.selectByExample(example);
        if (!(criterionList.isEmpty())) {
            return criterionList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<MarkCriterion> queryList() {
        MarkCriterionExample example = new MarkCriterionExample();
        return markCriterionMapper.selectByExample(example);
    }

    @Override
    public void saveOrEditCriterion(MarkCriterion newMark) {
        Date now = new Date();
        MarkCriterion oldCriterion = markCriterionMapper.selectByPrimaryKey(newMark.getCriterionId());
        if (oldCriterion == null) {
            newMark.setCreateTime(now);
            markCriterionMapper.insert(newMark);
        } else {
            BeanUtils.copyProperties(newMark, oldCriterion);
            oldCriterion.setUpdateTime(now);
            markCriterionMapper.updateByPrimaryKey(oldCriterion);
        }
    }

    @Override
    public void deleteAllCriterion() {
        MarkCriterionExample example = new MarkCriterionExample();
        markCriterionMapper.deleteByExample(example);
    }
}