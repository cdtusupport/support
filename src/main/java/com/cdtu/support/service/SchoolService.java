package com.cdtu.support.service;

import com.cdtu.support.pojo.SchoolWithBLOBs;

import java.util.List;

public interface SchoolService {

	Integer addSchool(SchoolWithBLOBs schoolWithBLOBs);

	Integer deleteSchool(String id);

	Integer updateSchool(SchoolWithBLOBs schoolWithBLOBs);

	List<SchoolWithBLOBs> queryAll();

	SchoolWithBLOBs queryByName(String schoolName);

	List<SchoolWithBLOBs> queryByCity(String city);

	List<SchoolWithBLOBs> queryByIsGo(String isGo);

	List<SchoolWithBLOBs> queryByIsNeed(String isNeed);

}
