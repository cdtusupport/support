package com.cdtu.support.controller;

import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.pojo.RolePermission;
import com.cdtu.support.service.PermissionService;
import com.cdtu.support.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PermissionController {

	@Autowired
	PermissionService permissionService;

	@Autowired
	RolePermissionService rolePermissionService;

	@GetMapping("/permission/list")
	public String permission(Model model) {
		List<Role> roleList = permissionService.queryAllRole();

		model.addAttribute("roleList", roleList);

		return "permission/list";
	}

	@GetMapping("/permission/toUpdatePage")
	public String toUpdatePage(Model model, Integer id) {
		List<Permission> permissionList = permissionService.queryAllPermission();

		List<Integer> permissionIdList = rolePermissionService.queryPermissionByRoleId(id);

		model.addAttribute("permissionList", permissionList);
		model.addAttribute("permissionIdList", permissionIdList);
		return "permission/update";
	}

}
