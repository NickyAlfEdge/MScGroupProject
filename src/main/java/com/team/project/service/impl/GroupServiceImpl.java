package com.team.project.service.impl;

import com.team.project.dto.GroupDetailDTO;
import com.team.project.mapper.GroupMapper;
import com.team.project.model.Group;
import com.team.project.model.GroupExample;
import com.team.project.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ZQJ Nicky Edge
 * @date 5/9/2020
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<GroupDetailDTO> selectGroupList() {
        GroupExample example = new GroupExample();
        return groupMapper.selectGroupListByExample(example);
    }

    @Override
    public GroupDetailDTO selectGroupById(Integer id) {
        GroupExample example = new GroupExample();
        example.createCriteria().andGroupIdEqualTo(id);
        List<GroupDetailDTO> groupDetailDTOS = groupMapper.selectGroupListByExample(example);
        if (groupDetailDTOS.size() > 0) {
            return groupDetailDTOS.get(0);
        }
        return null;
    }

    @Override
    public void saveOrUpdate(Group group) {
        Date now = new Date();
        if (group.getGroupId() != null) {
            Group existedGroup = groupMapper.selectByPrimaryKey(group.getGroupId());
            BeanUtils.copyProperties(group, existedGroup);
            existedGroup.setFacilitatorId(existedGroup.getFacilitatorId());
            existedGroup.setUpdateTime(now);
            groupMapper.updateByPrimaryKey(existedGroup);
        } else {
            group.setFacilitatorId(group.getFacilitatorId());
            group.setUpdateTime(now);
            groupMapper.insert(group);
        }
    }

    @Override
    public void deleteGroupById(Integer groupId) {
        groupMapper.deleteByPrimaryKey(groupId);
    }
}
