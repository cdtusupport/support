package com.cdtu.support.service;

import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.Role;

import java.util.List;

public interface PermissionService {

	List<Role> queryAllRole();

	List<Permission> queryAllPermission();

}
