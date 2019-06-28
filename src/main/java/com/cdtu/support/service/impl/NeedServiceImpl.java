package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.NeedInfoMapper;
import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.pojo.*;
import com.cdtu.support.service.NeedService;
import com.cdtu.support.service.SchoolService;
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
		needInfo.setId(UUID.randomUUID().toString().substring(0,5));
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
		/*SchoolWithBLOBs school = schoolMapper.selectByPrimaryKey(schoolWithBLOBs.getId());
		if (school == null){
			return 0;
		}
		int result = schoolMapper.updateByPrimaryKeySelective(schoolWithBLOBs);
		return result;

        NeedInfo need =needInfoMapper.selectByPrimaryKey(needInfo.getId());
        needInfoMapper.updateByExampleSelective(need);*/
		return 0;
    }


	@Override
	public List<NeedInfo> queryAll() {

		List<NeedInfo> needInfoList=needInfoMapper.selectByExampleWithBLOBs(null);
		return  needInfoList;

	}

    @Override
    public NeedInfo queryByName(String needName) {
        return null;
    }

//	@Override
//	public SchoolWithBLOBs queryByName(String schoolName) {
//
//		SchoolExample schoolExample = new SchoolExample();
//		SchoolExample.Criteria criteria = schoolExample.createCriteria();
//		criteria.andSchoolnameEqualTo(schoolName);
//
//		List<SchoolWithBLOBs> schoolWithBLOBsList = schoolMapper.selectByExampleWithBLOBs(schoolExample);
//
//		if (schoolWithBLOBsList == null){
//			return null;
//		}
//
//		return schoolWithBLOBsList.get(0);
//	}
//通过城市查询
//	@Override
//	public List<SchoolWithBLOBs> queryByCity(String city) {
//		SchoolExample schoolExample = new SchoolExample();
//		SchoolExample.Criteria criteria = schoolExample.createCriteria();
//		criteria.andCityEqualTo(city);
//
//		List<SchoolWithBLOBs> schoolWithBLOBsList = schoolMapper.selectByExampleWithBLOBs(schoolExample);
//
//		if (schoolWithBLOBsList == null){
//			return null;
//		}

//		return schoolWithBLOBsList;
//	}
}
