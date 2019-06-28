package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.NeedJoinMapper;
import com.cdtu.support.pojo.NeedJoin;
import com.cdtu.support.service.NeedJoinService;
import com.cdtu.support.util.SupportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeedJoinServiceImpl implements NeedJoinService {

	@Autowired
	NeedJoinMapper needJoinMapper;

	@Override
	public Integer addNeedJoin(NeedJoin needJoin) {
		needJoin.setId(SupportUtil.getUUID());
		int insertResult = needJoinMapper.insertSelective(needJoin);

		return insertResult;
	}
}
