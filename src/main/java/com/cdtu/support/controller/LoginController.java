package com.cdtu.support.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/toLogin")
	public String toLogin(){
		return "login";
	}

	@PostMapping("/login")
	public String login(String username, String password, Model model){

		//1、获取subject
		Subject subject = SecurityUtils.getSubject();

		//2、封装用户名与密码
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
		usernamePasswordToken.setUsername(username.trim());
		usernamePasswordToken.setPassword(password.trim().toCharArray());

		//3、执行登录方法


		try {
			subject.login(usernamePasswordToken);
			return "redirect:/index";
		} catch (UnknownAccountException e) {
			model.addAttribute("message", "用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e){
			model.addAttribute("message", "密码不正确");
			return "login";
		}
	}
}
