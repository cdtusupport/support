package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.RoadMapper;
import com.cdtu.support.pojo.*;
import com.cdtu.support.service.RoadService;
import com.cdtu.support.util.SupportUtil;
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
        RoadExample roadExample=new RoadExample();
        RoadExample.Criteria criteria=roadExample.createCriteria();
        criteria.andNameEqualTo(road.getName());
        List<Road> roadList=roadMapper.selectByExampleWithBLOBs(roadExample);
        if(roadList.size() !=0)
        {
            return 0;
        }
        road.setId(UUID.randomUUID().toString().substring(0,5));
        road.setPublishtime(SupportUtil.getTime());
        road.setOther("other");
        int result = roadMapper.insertSelective(road);
        return  result;
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

        Road road1 = roadMapper.selectByPrimaryKey(road.getId());
        if (road1 == null){
            return 0;
        }
        int result = roadMapper.updateByPrimaryKeySelective(road);

        return result;
    }

    @Override
    public List<Road> queryAll() {

        List<Road> roadList=roadMapper.selectByExampleWithBLOBs(null);
        return  roadList;

    }
    @Override
    public List<Road> queryByName(String needName) {
        RoadExample RoadExample=new RoadExample();
        RoadExample.Criteria criteria=RoadExample.createCriteria();
        criteria.andNameEqualTo(needName);
        List<Road> roadList=roadMapper.selectByExampleWithBLOBs(RoadExample);

        if (roadList==null)
        {
            return  null;
        }
        return  roadList;
    }

    @Override
    public Road queryById(String id) {

        Road road = roadMapper.selectByPrimaryKey(id);
        return road;
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

}
