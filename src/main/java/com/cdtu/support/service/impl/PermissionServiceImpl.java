package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.PermissionMapper;
import com.cdtu.support.mapper.RoleMapper;
import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.PermissionExample;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<String> queryAllPermissionNameBypId(List<Integer> ids) {

		PermissionExample permissionExample = new PermissionExample();
		PermissionExample.Criteria criteria = permissionExample.createCriteria();
		criteria.andIdIn(ids);

		return permissionMapper.selectByExample(permissionExample).stream().map(Permission::getName).collect(Collectors.toList());
	}
}
