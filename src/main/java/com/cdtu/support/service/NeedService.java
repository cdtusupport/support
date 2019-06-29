package com.cdtu.support.service;

import com.cdtu.support.pojo.NeedInfo;

import java.util.List;

public interface NeedService {

	Integer addNeed(NeedInfo needInfo);

	Integer deleteNeed(String id);

	Integer updateNeed(NeedInfo needInfo);

	List<NeedInfo> queryAll();

	List<NeedInfo> queryByName(String needName);

	NeedInfo queryById(String id);

//	List<SchoolWithBLOBs> queryByCity(String city);
//
//	List<SchoolWithBLOBs> queryByIsGo(String isGo);

	//List<SchoolWithBLOBs> queryByIsNeed(String isNeed);

}
