package com.cdtu.support.controller;

import com.cdtu.support.pojo.User;
import com.cdtu.support.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//登录
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@PostMapping("/login")
	public String login(String username,
	                    String password,
	                    Model model,
	                    HttpServletRequest httpServletRequest) {

		//1、获取subject
		Subject subject = SecurityUtils.getSubject();

		//2、封装用户名与密码
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
		usernamePasswordToken.setUsername(username.trim());
		usernamePasswordToken.setPassword(password.trim().toCharArray());

		List<User> userList = userService.queryByName(username.trim());

		//3、执行登录方法

		try {
			subject.login(usernamePasswordToken);
			httpServletRequest.getSession().setAttribute("userId", userList.get(0).getId());
			httpServletRequest.getSession().setAttribute("username", userList.get(0).getUsername());
			return "redirect:/index";
		} catch (UnknownAccountException e) {
			model.addAttribute("message", "用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("message", "密码不正确");
			return "login";
		}
	}
}
