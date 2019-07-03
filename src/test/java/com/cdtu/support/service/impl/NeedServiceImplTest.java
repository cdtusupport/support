package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.NeedInfoMapper;
import com.cdtu.support.mapper.RoadMapper;
import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.Road;
import com.cdtu.support.util.SupportUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeedServiceImplTest {
    @Autowired
    private NeedInfoMapper needInfoMapper;

    @Autowired
    private RoadMapper roadMapper;
    @Test
    public void test01(){
        for (int i = 0; i < 20; i++) {
            NeedInfo needInfo = new NeedInfo();
            needInfo.setId(SupportUtil.getUUID());
            needInfo.setName(SupportUtil.getUUID());
            needInfo.setUserid("15554");
            needInfo.setSchoolid("12268");
            needInfo.setLevel("1");
            needInfo.setInfo(SupportUtil.getUUID());
            needInfo.setPublishtime(SupportUtil.getTime());
            needInfo.setEdittime(SupportUtil.getTime());
            needInfoMapper.insertSelective(needInfo);
        }
    }
    
    @Test 
    public void test02(){
        for (int i = 0; i < 20; i++) {
            Road road = new Road();
            road.setId(SupportUtil.getUUID());
            road.setName(SupportUtil.getUUID());
            road.setContent("15554");
            road.setPublishtime(SupportUtil.getTime());
            roadMapper.insertSelective(road);
        }
    }

}