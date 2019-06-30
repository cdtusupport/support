package com.cdtu.support.shiro;

import com.cdtu.support.mapper.RolePermissionMapper;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.pojo.User;
import com.cdtu.support.service.LoginService;
import com.cdtu.support.service.PermissionService;
import com.cdtu.support.service.RolePermissionService;
import com.cdtu.support.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	LoginService loginService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		User user = (User) SecurityUtils.getSubject().getPrincipal();

		Role role = roleService.queryRoleByLevel(user.getLevel());
		simpleAuthorizationInfo.addRole(role.getName());

		List<Integer> permissionIdList = rolePermissionService.queryPermissionByRoleId(role.getId());

		List<String> permissionList = permissionService.queryAllPermissionNameBypId(permissionIdList);

		simpleAuthorizationInfo.addStringPermissions(permissionList);

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		User user = loginService.queryByName(usernamePasswordToken.getUsername().trim());

		if (user == null){
			return null;
		}

		return new SimpleAuthenticationInfo(user, user.getPassword(), "");
	}
}
