package com.team.project.service.impl;

import com.team.project.constant.ResultEnum;
import com.team.project.dto.Result;
import com.team.project.mapper.UserMapper;
import com.team.project.model.*;
import com.team.project.service.UserService;
import com.team.project.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ZQJ
 * @date 4/23/2020
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginVerify(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(user.getEmail())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<User> queryList(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        return userMapper.selectByExample(userExample);
    }

    @Override
    public User queryById(Integer userId) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<User> user = userMapper.selectByExample(example);
        if (!(user.isEmpty())) {
            return user.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> queryList() {
        UserExample example = new UserExample();
        example.createCriteria().andUserTypeEqualTo(Byte.valueOf("1"));

        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> queryAllUsers() {
        UserExample example = new UserExample();
        //example.createCriteria().andUserTypeEqualTo(Byte.valueOf("1"));
        example.createCriteria().andUserIdIsNotNull();
        return userMapper.selectByExample(example);
    }
    @Override
    public User saveOrUpdate(User user){

        Date now = new Date();
        if (queryById(user.getUserId())!=null) {
            User oldUser = userMapper.selectByPrimaryKey(user.getUserId());
            BeanUtils.copyProperties(user, oldUser);
            oldUser.setUpdateTime(now);
            userMapper.updateByPrimaryKey(oldUser);
        } else {
            user.setCreateTime(now);
            user.setUpdateTime(now);
            int insert = userMapper.insert(user);
        }
        return user;
    }

    @Override
    public Result deleteUserById(Integer id) {
        int i = userMapper.deleteByPrimaryKey(id);
        return ResultUtil.success();
    }
}
