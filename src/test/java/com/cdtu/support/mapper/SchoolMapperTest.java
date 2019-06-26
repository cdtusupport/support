package com.cdtu.support.mapper;

import com.cdtu.support.pojo.SchoolExample;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolMapperTest {

	@Autowired
	SchoolMapper schoolMapper;

	@Test
	public void test01(){

		// SchoolWithBLOBs school = schoolMapper.selectByPrimaryKey("aa6b6");
		// System.out.println(school);

		SchoolExample schoolExample = new SchoolExample();
		SchoolExample.Criteria criteria = schoolExample.createCriteria();
		criteria.andIdIsNotNull();

		List<SchoolWithBLOBs> schoolWithBLOBs = schoolMapper.selectByExampleWithBLOBs(null);
		System.out.println(schoolWithBLOBs.size());

	}

	@Test
	public void test02(){

		for (int i = 0; i < 10; i++) {
			SchoolWithBLOBs schoolWithBLOBs = new SchoolWithBLOBs();
		}

	}

}