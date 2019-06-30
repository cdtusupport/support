package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.RoleMapper;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role queryRoleByLevel(Integer id) {

		Role role = roleMapper.selectByPrimaryKey(id);

		return role;
	}
}
