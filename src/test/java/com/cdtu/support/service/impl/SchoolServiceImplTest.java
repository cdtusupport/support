package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.SchoolService;

import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolServiceImplTest {

	@Autowired
	SchoolService schoolService;

	@Autowired
	SchoolMapper schoolMapper;

	@Test
	public void addSchool() {
		for (int i = 0; i < 50; i++) {
			SchoolWithBLOBs schoolWithBLOBs = new SchoolWithBLOBs();
			schoolWithBLOBs.setSchoolname(UUID.randomUUID().toString().substring(0,5));
			schoolWithBLOBs.setCity(UUID.randomUUID().toString().substring(0,5));
			schoolWithBLOBs.setInfo(UUID.randomUUID().toString().substring(0,5));
			schoolWithBLOBs.setIsgo("yes");
			schoolWithBLOBs.setIsneed("No");
			schoolWithBLOBs.setHistory(UUID.randomUUID().toString().substring(0,5));
			schoolWithBLOBs.setOther("other");
			schoolWithBLOBs.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			Integer result = schoolService.addSchool(schoolWithBLOBs);
			System.out.println(result);
		}

	}

	@Test
	public void deleteSchool() {
	}

	@Test
	public void updateSchool() {
	}

	@Test
	public void queryByName() {
		String schoolName = "成都工业学院";
		SchoolWithBLOBs schoolWithBLOBs = schoolService.queryByName(schoolName);
		System.out.println(schoolWithBLOBs);
	}

	@Test
	public void queryByCity() {

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	@Test
	public void queryAll() {

		PageHelper.startPage(1,10);
		List<SchoolWithBLOBs> schoolWithBLOBs = schoolMapper.selectByExampleWithBLOBs(null);
		System.out.println(schoolWithBLOBs.size());

		/*
		PageHelper.startPage(1,10);
		List<SchoolWithBLOBs> schoolWithBLOBs = schoolMapper.selectByExampleWithBLOBs(null);

		PageInfo<SchoolWithBLOBs> page = new PageInfo<>(schoolWithBLOBs);

		System.out.println("总数量：" + page.getTotal());
		System.out.println("当前页查询记录：" + page.getList().size());
		System.out.println("当前页码：" + page.getPageNum());
		System.out.println("每页显示数量：" + page.getPageSize());
		System.out.println("总页：" + page.getPages());


		System.out.println(schoolWithBLOBs.size());
*/
	}
}