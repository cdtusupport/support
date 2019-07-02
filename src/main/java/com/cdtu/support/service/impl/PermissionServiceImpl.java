package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.PermissionMapper;
import com.cdtu.support.mapper.RoleMapper;
import com.cdtu.support.mapper.RolePermissionMapper;
import com.cdtu.support.pojo.*;
import com.cdtu.support.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	PermissionMapper permissionMapper;

	@Autowired
	RolePermissionMapper rolePermissionMapper;

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

	@Override
	public void updatePermission(Integer id, List<Integer> newPermissionIdList) {

		//1、查询原有权限id
		RolePermissionExample rolePermissionExample = new RolePermissionExample();
		RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
		criteria.andRoleIdEqualTo(id);

		List<Integer> oldPermissionIdList = rolePermissionMapper.
				selectByExample(rolePermissionExample).
				stream().map(RolePermission::getPermissionId).
				collect(Collectors.toList());

		//2、将更改后的权限id与原来的权限id进行比对，找出新增与删除的权限
		//定义需要新增的权限id集合
		List<Integer> insertPermissionIdList = new ArrayList<>();
		//定义需要删除的权限id集合
		List<Integer> deletePermissionIdList = new ArrayList<>();

		int newPermissionListSize = newPermissionIdList.size();
		int oldPermissionListSize = oldPermissionIdList.size();
		//找出需要新增的权限id
		for (int i = 0; i < newPermissionListSize; i++) {
			if (!oldPermissionIdList.contains(newPermissionIdList.get(i))) {
				insertPermissionIdList.add(newPermissionIdList.get(i));
			}
		}
		//找出需要删除的权限id
		for (int i = 0; i < oldPermissionListSize; i++) {
			if (!newPermissionIdList.contains(oldPermissionIdList.get(i))) {
				deletePermissionIdList.add(oldPermissionIdList.get(i));
			}
		}

		//3、判断是否需要修改权限，如果需求，修改权限
		if (insertPermissionIdList.size() != 0) {
			for (Integer insertPermissionId : insertPermissionIdList) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(id);
				rolePermission.setPermissionId(insertPermissionId);
				rolePermissionMapper.insert(rolePermission);
			}
		}

		if (deletePermissionIdList.size() != 0) {
			RolePermissionExample deleteRolePermissionExample = new RolePermissionExample();
			RolePermissionExample.Criteria deleteCriteria = deleteRolePermissionExample.createCriteria();
			deleteCriteria.andRoleIdEqualTo(id);
			deleteCriteria.andPermissionIdIn(deletePermissionIdList);
			rolePermissionMapper.deleteByExample(deleteRolePermissionExample);
		}

	}

}
