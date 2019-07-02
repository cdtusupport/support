package com.cdtu.support.controller;


import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.pojo.User;
import com.cdtu.support.service.SchoolService;
import com.cdtu.support.service.UserService;
import com.cdtu.support.util.SupportUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequiresRoles("super")
@SuppressWarnings("All")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	SchoolService schoolService;


	@GetMapping("/user/list")
	public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                           @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                           Map<String, Object> model) {
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userService.queryAll();
		model.put("userList", userList);
		model.put("pageNum", pageNum);
		model.put("pages", page.getPages());
		model.put("pageSize", pageSize);
		return "user/list";
	}

	@PostMapping(value = "/user")
	public String addUser(User user) {
		user.setId(SupportUtil.getUUID());
		List<SchoolWithBLOBs> schoolWithBLOBs = schoolService.queryByName(user.getSchoolname());
		user.setSchoolid(schoolWithBLOBs.get(0).getId());
		user.setCreatetime(SupportUtil.getTime());
		userService.addUser(user);
		return "redirect:/user/list";
	}
	//更新用户
	@PostMapping(value = "/updateUser")
	public String updateUser(User user) {
		List<SchoolWithBLOBs> schoolWithBLOBs = schoolService.queryByName(user.getSchoolname());
		user.setSchoolid(schoolWithBLOBs.get(0).getId());
		user.setCreatetime(SupportUtil.getTime());
		userService.updateUser(user);
		return "redirect:/user/list";
	}
	@DeleteMapping("/user")
	public String deleteUser(String id) {
		userService.deleteByPrimaryKey(id);
		return "redirect:/user/list";
	}

	@GetMapping("/user/queryByName")
	public String SelectByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                           @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                           @RequestParam("userName") String userName, Map<String, Object> model) {
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		if (StringUtils.isEmpty(userName)) {
			return "redirect:/user/list";
		}
		List<User> userList = userService.queryByName(userName);
		if (userList == null) {
			return "redirect:/user/list";
		}
        model.put("userList", userList);
        model.put("pageNum", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "user/list";
	}


	@GetMapping("/user/toAddPage")
	public String toAddUserPage(Map<String, Object> model) {
		List<SchoolWithBLOBs> schoolList = schoolService.queryAll();
		model.put("schools", schoolList);
		return "user/add";
	}

	@GetMapping("/user/toUpdatePage")
	public String toUpdatePage(String id, Map<String, Object> model) {
		List<SchoolWithBLOBs> schoolList = schoolService.queryAll();
		User user = userService.queryById(id);
		model.put("user", user);
		model.put("schools", schoolList);
		return "user/update";
	}

}
