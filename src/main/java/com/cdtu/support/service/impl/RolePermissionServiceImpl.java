package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.RolePermissionMapper;
import com.cdtu.support.pojo.RolePermission;
import com.cdtu.support.pojo.RolePermissionExample;
import com.cdtu.support.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Override
	public List<Integer> queryPermissionByRoleId(Integer id) {
		RolePermissionExample rolePermissionExample = new RolePermissionExample();
		RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
		criteria.andRoleIdEqualTo(id);

		List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);

		return rolePermissionList.stream()
				.map(RolePermission::getPermissionId)
				.collect(Collectors.toList());
	}
}
