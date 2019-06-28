package com.cdtu.support.service;

import com.cdtu.support.pojo.RecruitInfo;

import java.util.List;

public interface RecruitService {

	Integer addRecruit(RecruitInfo recruitInfo);

	Integer deleteRecruit(String id);

	Integer updateRecruit(RecruitInfo recruitInfo);

	List<RecruitInfo> queryAllRecruit();

	List<RecruitInfo> queryByName(String name);

	RecruitInfo queryById(String id);


}
