package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.RoleMapper;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<Role> queryAllRole() {
		List<Role> roleList = roleMapper.selectByExample(null);
		return roleList;
	}
}
