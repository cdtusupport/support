package com.cdtu.support.service.impl;


import com.cdtu.support.mapper.RecruitInfoMapper;
import com.cdtu.support.pojo.RecruitInfo;
import com.cdtu.support.pojo.RecruitInfoExample;
import com.cdtu.support.service.RecruitService;
import com.cdtu.support.util.SupportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecruitServiceImpl implements RecruitService {

	@Autowired
	RecruitInfoMapper recruitInfoMapper;

	@Override
	public Integer addRecruit(RecruitInfo recruitInfo) {
		recruitInfo.setId(UUID.randomUUID().toString().substring(0,5));
		recruitInfo.setPublishtime(SupportUtil.getTime());
		int result = recruitInfoMapper.insertSelective(recruitInfo);
		return result;
	}

	@Override
	public Integer deleteRecruit(String id) {
		int deleteResult = recruitInfoMapper.deleteByPrimaryKey(id);
		return deleteResult;
	}

	@Override
	public Integer updateRecruit(RecruitInfo recruitInfo) {
		int updateResult = recruitInfoMapper.updateByPrimaryKeySelective(recruitInfo);
		return updateResult;
	}

	@Override
	public List<RecruitInfo> queryAllRecruit() {
		List<RecruitInfo> recruitInfoList = recruitInfoMapper.selectByExampleWithBLOBs(null);
		return recruitInfoList;
	}

	@Override
	public List<RecruitInfo> queryByName(String name) {

		RecruitInfoExample recruitInfoExample = new RecruitInfoExample();
		RecruitInfoExample.Criteria criteria = recruitInfoExample.createCriteria();
		criteria.andNameLike("%" + name +"%");
		// criteria.andNameEqualTo(name);

		List<RecruitInfo> recruitInfoList = recruitInfoMapper.selectByExample(recruitInfoExample);
		return recruitInfoList;
	}

	@Override
	public RecruitInfo queryById(String id) {

		RecruitInfo recruitInfo = recruitInfoMapper.selectByPrimaryKey(id);
		return recruitInfo;
	}
}
