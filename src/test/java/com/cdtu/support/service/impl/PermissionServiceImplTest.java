package com.cdtu.support.service.impl;

import com.cdtu.support.pojo.Permission;
import com.cdtu.support.pojo.Role;
import com.cdtu.support.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceImplTest {

	@Autowired
	PermissionService permissionService;
	@Test
	public void queryAllRole() {
		List<Role> roleList = permissionService.queryAllRole();
		for (Role role : roleList) {
			System.out.println(role);
		}
	}

	@Test
	public void queryAllPermission() {

		List<Permission> permissions = permissionService.queryAllPermission();
		System.out.println(permissions.size());
	}

	@Test
	public void updatePermission() {

		int id = 3;

		List<Integer> per = new ArrayList<>(Arrays.asList(1,3,5,7,9));

		permissionService.updatePermission(id, per);
	}
}