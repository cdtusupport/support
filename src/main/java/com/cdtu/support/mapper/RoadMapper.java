package com.cdtu.support.mapper;

import com.cdtu.support.pojo.Road;
import com.cdtu.support.pojo.RoadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoadMapper {
    long countByExample(RoadExample example);

    int deleteByExample(RoadExample example);

    int deleteByPrimaryKey(String id);

    int insert(Road record);

    int insertSelective(Road record);

    List<Road> selectByExampleWithBLOBs(RoadExample example);

    List<Road> selectByExample(RoadExample example);

    Road selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Road record, @Param("example") RoadExample example);

    int updateByExampleWithBLOBs(@Param("record") Road record, @Param("example") RoadExample example);

    int updateByExample(@Param("record") Road record, @Param("example") RoadExample example);

    int updateByPrimaryKeySelective(Road record);

    int updateByPrimaryKeyWithBLOBs(Road record);

    int updateByPrimaryKey(Road record);
}