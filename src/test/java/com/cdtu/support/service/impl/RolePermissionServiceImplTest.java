package com.cdtu.support.service.impl;

import com.cdtu.support.service.RolePermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RolePermissionServiceImplTest {

	@Autowired
	RolePermissionService rolePermissionService;
	@Test
	public void queryPermissionByRoleId() {
		List<Integer> permissionIdList = rolePermissionService.queryPermissionByRoleId(1);
		System.out.println(permissionIdList.size());
	}
}