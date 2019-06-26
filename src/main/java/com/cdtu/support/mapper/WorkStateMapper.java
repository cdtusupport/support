package com.cdtu.support.mapper;

import com.cdtu.support.pojo.WorkState;
import com.cdtu.support.pojo.WorkStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkStateMapper {
    long countByExample(WorkStateExample example);

    int deleteByExample(WorkStateExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkState record);

    int insertSelective(WorkState record);

    List<WorkState> selectByExampleWithBLOBs(WorkStateExample example);

    List<WorkState> selectByExample(WorkStateExample example);

    WorkState selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkState record, @Param("example") WorkStateExample example);

    int updateByExampleWithBLOBs(@Param("record") WorkState record, @Param("example") WorkStateExample example);

    int updateByExample(@Param("record") WorkState record, @Param("example") WorkStateExample example);

    int updateByPrimaryKeySelective(WorkState record);

    int updateByPrimaryKeyWithBLOBs(WorkState record);

    int updateByPrimaryKey(WorkState record);
}