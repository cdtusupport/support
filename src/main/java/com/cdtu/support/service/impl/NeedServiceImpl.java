package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.NeedInfoMapper;
import com.cdtu.support.pojo.*;
import com.cdtu.support.service.NeedService;
import com.cdtu.support.util.SupportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NeedServiceImpl implements NeedService {

	@Autowired
	NeedInfoMapper needInfoMapper;

	@Override
	public Integer addNeed(NeedInfo needInfo) {
		NeedInfoExample needInfoExample=new NeedInfoExample();
		NeedInfoExample .Criteria criteria=needInfoExample.createCriteria();
		criteria.andNameEqualTo(needInfo.getName());
		List<NeedInfo> needInfoList=needInfoMapper.selectByExampleWithBLOBs(needInfoExample);
		if (needInfoList.size()!=0)
		{

			return 0;
		}
		needInfo.setId(UUID.randomUUID().toString().substring(0,5));
		needInfo.setPublishtime(SupportUtil.getTime());
		needInfo.setOther("other");
		int insertResult= needInfoMapper.insert(needInfo);
		return insertResult;
	}


	@Override
	public Integer deleteNeed(String id) {

        NeedInfo needInfo = needInfoMapper.selectByPrimaryKey(id);
        if (needInfo==null)
        {
            return 0;
        }
        int result= needInfoMapper.deleteByPrimaryKey(id);
        return result;
	}


	@Override
	public Integer updateNeed(NeedInfo needInfo) {
		NeedInfo needInfo1 = needInfoMapper.selectByPrimaryKey(needInfo.getId());
		if (needInfo1==null){
			return 0;
		}

		int result = needInfoMapper.updateByPrimaryKeySelective(needInfo);

		return result;

    }


	@Override
	public List<NeedInfo> queryAll() {

		List<NeedInfo> needInfoList=needInfoMapper.selectByExampleWithBLOBs(null);
		return  needInfoList;

	}

    @Override
    public List<NeedInfo> queryByName(String needName) {
       NeedInfoExample needInfoExample=new NeedInfoExample();
       NeedInfoExample.Criteria criteria=needInfoExample.createCriteria();
       criteria.andNameEqualTo(needName);
       List<NeedInfo> needInfoList=needInfoMapper.selectByExampleWithBLOBs(needInfoExample);

       if (needInfoList==null)
	   {
	   	return  null;
	   }
	   return  needInfoList;
    }

	@Override
	public NeedInfo queryById(String id) {
		NeedInfo needInfo = needInfoMapper.selectByPrimaryKey(id);
		return needInfo;
	}


}
