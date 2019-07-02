package com.cdtu.support.service;

import com.cdtu.support.pojo.WorkState;

import java.util.List;

public interface workStateService {
     List<WorkState> queryAll();

     List<WorkState> queryByName(String workStateName);

     void deleteByPrimaryKey(String id);

     void addWorkState(WorkState workState);

     WorkState queryById(String id);

    void updateWorkState(WorkState workState);
}
