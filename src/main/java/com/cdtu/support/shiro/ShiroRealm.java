package com.cdtu.support.shiro;

import com.cdtu.support.pojo.User;
import com.cdtu.support.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	LoginService loginService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
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
