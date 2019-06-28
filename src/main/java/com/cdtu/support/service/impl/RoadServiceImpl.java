package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.NeedInfoMapper;
import com.cdtu.support.mapper.RoadMapper;
import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.pojo.*;
import com.cdtu.support.service.NeedService;
import com.cdtu.support.service.RoadService;
import com.cdtu.support.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    RoadMapper roadMapper;

    @Override
    public Integer addRoad(Road road) {

        // Road.setId(UUID.randomUUID().toString().substring(0,5));
        int insertResult= roadMapper.insert(road);
        return insertResult;
    }

    @Override
    public Integer deleteRoad(String id) {
        Road road =  roadMapper.selectByPrimaryKey(id);
        if (road==null)
        {
            return 0;
        }
        int result= roadMapper.deleteByPrimaryKey(id);
        return result;
    }


    @Override
    public Integer updateRoad(Road road) {
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
    public List<Road> queryAll() {

		/*List<SchoolWithBLOBs> schoolWithBLOBsList = schoolMapper.selectByExampleWithBLOBs(null);

		return schoolWithBLOBsList;*/

        List<Road> roadList=roadMapper.selectByExampleWithBLOBs(null);
        return  roadList;

    }
    @Override
    public Road queryByName(String road) {
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
