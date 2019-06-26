package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.pojo.School;
import com.cdtu.support.pojo.SchoolExample;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.SchoolService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	SchoolMapper schoolMapper;

	@Override
	public Integer addSchool(SchoolWithBLOBs schoolWithBLOBs) {

		SchoolExample schoolExample = new SchoolExample();
		SchoolExample.Criteria criteria = schoolExample.createCriteria();
		criteria.andSchoolnameEqualTo(schoolWithBLOBs.getSchoolname());

		List<School> schoolList = schoolMapper.selectByExample(schoolExample);
		if (schoolList.size() != 0){
			return 0;
		}

		schoolWithBLOBs.setId(UUID.randomUUID().toString().substring(0,5));

		int result = schoolMapper.insertSelective(schoolWithBLOBs);

		return result;
	}

	@Override
	public Integer deleteSchool(String id) {

		SchoolWithBLOBs schoolWithBLOBs = schoolMapper.selectByPrimaryKey(id);
		if (schoolWithBLOBs == null){
			return 0;
		}

		int result = schoolMapper.deleteByPrimaryKey(id);

		return result;
	}

	@Override
	public Integer updateSchool(SchoolWithBLOBs schoolWithBLOBs) {

		SchoolWithBLOBs school = schoolMapper.selectByPrimaryKey(schoolWithBLOBs.getId());
		if (school == null){
			return 0;
		}

		int result = schoolMapper.updateByPrimaryKeySelective(schoolWithBLOBs);

		return result;
	}

	@Override
	public List<SchoolWithBLOBs> queryAll() {

		List<SchoolWithBLOBs> schoolWithBLOBsList = schoolMapper.selectByExampleWithBLOBs(null);

		return schoolWithBLOBsList;
	}

	@Override
	public SchoolWithBLOBs queryByName(String schoolName) {

		SchoolExample schoolExample = new SchoolExample();
		SchoolExample.Criteria criteria = schoolExample.createCriteria();
		criteria.andSchoolnameEqualTo(schoolName);

		List<SchoolWithBLOBs> schoolWithBLOBsList = schoolMapper.selectByExampleWithBLOBs(schoolExample);

		if (schoolWithBLOBsList == null){
			return null;
		}

		return schoolWithBLOBsList.get(0);
	}

	@Override
	public List<SchoolWithBLOBs> queryByCity(String city) {
		SchoolExample schoolExample = new SchoolExample();
		SchoolExample.Criteria criteria = schoolExample.createCriteria();
		criteria.andCityEqualTo(city);

		List<SchoolWithBLOBs> schoolWithBLOBsList = schoolMapper.selectByExampleWithBLOBs(schoolExample);

		if (schoolWithBLOBsList == null){
			return null;
		}

		return schoolWithBLOBsList;
	}

	@Override
	public List<SchoolWithBLOBs> queryByIsGo(String isGo) {
		return null;
	}

	@Override
	public List<SchoolWithBLOBs> queryByIsNeed(String isNeed) {
		return null;
	}
}
