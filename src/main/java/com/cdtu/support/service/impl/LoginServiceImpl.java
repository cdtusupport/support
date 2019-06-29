package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.UserMapper;
import com.cdtu.support.pojo.User;
import com.cdtu.support.pojo.UserExample;
import com.cdtu.support.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User queryByName(String name) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(name);

		List<User> userList = userMapper.selectByExample(userExample);
		if (userList.size() == 0){
			return null;
		}

		return userList.get(0);
	}

	@Override
	public User queryById(String id) {

		User user = userMapper.selectByPrimaryKey(id);
		if (user == null){
			return null;
		}

		return user;
	}
}
