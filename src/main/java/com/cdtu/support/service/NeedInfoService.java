package com.cdtu.support.service;

import com.cdtu.support.pojo.NeedInfo;

import java.util.List;

public interface NeedInfoService {

	Integer addNeed(NeedInfo needInfo);

	Integer deleteNeed(String id);

	Integer updateNeed(NeedInfo needInfo);

	List<NeedInfo> queryAll();

	List<NeedInfo> queryByName(String needName);

	NeedInfo queryById(String id);



}
