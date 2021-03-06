package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.mapper.UserMapper;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.pojo.User;
import com.cdtu.support.pojo.UserExample;
import com.cdtu.support.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        List<User> policyList = userMapper.selectByExample(null);
        return policyList;
    }

    @Override
    public List<User> queryByName(String name) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        List<User> userList=userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        User user=userMapper.selectByPrimaryKey(id);
        if (user==null)
            return;
        else {
            userMapper.deleteByPrimaryKey(id);
            return;
        }
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
        return;
    }

    @Override
    public User queryById(String id) {
        User user=userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        User user1=userMapper.selectByPrimaryKey(user.getId());
        if (user1==null){
            return;
        }
        userMapper.updateByPrimaryKeySelective(user);
        return;
    }

}
