package com.cdtu.support.service.impl;

import com.cdtu.support.mapper.WorkStateMapper;
import com.cdtu.support.pojo.User;
import com.cdtu.support.pojo.UserExample;
import com.cdtu.support.pojo.WorkState;
import com.cdtu.support.pojo.WorkStateExample;
import com.cdtu.support.service.workStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class workStateServiceImpl implements workStateService {
    @Autowired
    WorkStateMapper workStateMapper;
    public List<WorkState> queryAll() {
        List<WorkState> workStateList=workStateMapper.selectByExample(null);
        return workStateList;
    }

    @Override
    public List<WorkState> queryByName(String workStateName) {
        WorkStateExample workStateExample=new WorkStateExample();
        WorkStateExample.Criteria criteria=workStateExample.createCriteria();
        criteria.andNameEqualTo(workStateName);
        List<WorkState> workStateList=workStateMapper.selectByExample(workStateExample);
        return workStateList;
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        WorkState workState=workStateMapper.selectByPrimaryKey(id);
        if(workState==null){
            return;
        }
        else {
            workStateMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void addWorkState(WorkState workState) {
        workStateMapper.insertSelective(workState);
        return;
    }

    @Override
    public WorkState queryById(String id) {
        WorkState workState=workStateMapper.selectByPrimaryKey(id);
        return workState;
    }

    @Override
    public void updateWorkState(WorkState workState) {
        WorkState workState1=workStateMapper.selectByPrimaryKey(workState.getId());
        if (workState1==null){
            return;
        }
        workStateMapper.updateByPrimaryKeySelective(workState);
        return;
    }

}
