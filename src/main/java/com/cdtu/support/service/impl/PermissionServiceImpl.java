package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.PermissionMapper;
import com.cdtu.support.mapper.RoleMapper;
import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public List<Role> queryAllRole() {
		List<Role> roleList = roleMapper.selectByExample(null);
		return roleList;
	}

	@Override
	public List<Permission> queryAllPermission() {
		List<Permission> permissionList = permissionMapper.selectByExample(null);
		return permissionList;
	}
}
