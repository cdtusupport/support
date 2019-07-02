package com.cdtu.support.service;

import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.Role;

import java.util.List;

public interface PermissionService {

	List<Role> queryAllRole();

	List<Permission> queryAllPermission();

	List<String> queryAllPermissionNameBypId(List<Integer> ids);

	void updatePermission(Integer id, List<Integer> permissionIds);

}
