package com.cdtu.support.controller;

import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.service.PermissionService;
import com.cdtu.support.service.RolePermissionService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiresRoles("super")
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

	@PutMapping("/permission/update")
	public String update(@RequestParam("id") Integer id, String permissionList) {

		System.out.println(permissionList);
		String[] permissionArray = permissionList.split(",");
		List<Integer> permissions = new ArrayList<>();
		for (String permissionId : permissionArray) {
			permissions.add(Integer.parseInt(permissionId));
		}

		permissionService.updatePermission(id, permissions);

		return "redirect:/permission/list";
	}

	@GetMapping("/permission/toUpdatePage")
	public String toUpdatePage(Model model, Integer id) {
		List<Permission> permissionList = permissionService.queryAllPermission();

		List<Integer> permissionIdList = rolePermissionService.queryPermissionByRoleId(id);

		model.addAttribute("permissionList", permissionList);
		model.addAttribute("permissionIdList", permissionIdList);
		model.addAttribute("id", id);
		return "permission/update";
	}
}
