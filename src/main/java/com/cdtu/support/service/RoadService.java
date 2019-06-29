package com.cdtu.support.service;

import com.cdtu.support.pojo.Road;
import com.cdtu.support.pojo.SchoolWithBLOBs;

import java.util.List;

public interface RoadService {

    Integer addRoad(Road road);

    Integer deleteRoad(String id);

    Integer updateRoad(Road road);

    List<Road> queryAll();

    List<Road> queryByName(String Road);

    Road queryById(String id);

//	List<SchoolWithBLOBs> queryByCity(String city);
//
//	List<SchoolWithBLOBs> queryByIsGo(String isGo);

    //List<SchoolWithBLOBs> queryByIsNeed(String isNeed);

}
