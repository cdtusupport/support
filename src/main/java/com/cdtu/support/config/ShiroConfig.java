package com.cdtu.support.config;

import com.cdtu.support.shiro.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		shiroFilterFactoryBean.setSuccessUrl("/index");

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/utf8-jsp/**", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/*", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(ShiroRealm shiroRealm){
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(shiroRealm);
		return defaultWebSecurityManager;
	}


	@Bean
	public ShiroRealm shiroRealm(){
		return new ShiroRealm();
	}
}
