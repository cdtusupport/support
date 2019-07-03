package com.cdtu.support.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.cdtu.support.shiro.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Properties;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/utf8-jsp/**", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/index/**", "anon");
		filterChainDefinitionMap.put("/jquery/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");
		filterChainDefinitionMap.put("/login/**", "anon");
		filterChainDefinitionMap.put("/ueditor/**", "anon");

		filterChainDefinitionMap.put("/indexShowPage", "anon");
		filterChainDefinitionMap.put("/showPolicyPage", "anon");
		filterChainDefinitionMap.put("/downloadPolicyPage", "anon");
		filterChainDefinitionMap.put("/showNeedPage", "anon");
		filterChainDefinitionMap.put("/showRoadPage", "anon");
		filterChainDefinitionMap.put("/showSchoolPage", "anon");
		filterChainDefinitionMap.put("/showWorkStatePage", "anon");
		filterChainDefinitionMap.put("/showWorkStatePageDetaile", "anon");
		filterChainDefinitionMap.put("/showRecruitPage", "anon");
		filterChainDefinitionMap.put("/needAdd", "anon");
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		shiroFilterFactoryBean.setSuccessUrl("/index");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(ShiroRealm shiroRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(shiroRealm);
		return defaultWebSecurityManager;
	}


	@Bean
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties properties = new Properties();
		properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/index");
		properties.setProperty("org.apache.shiro.authz.UnauthenticatedException", "/toLogin");
		resolver.setExceptionMappings(properties);
		return resolver;
	}

	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
}
