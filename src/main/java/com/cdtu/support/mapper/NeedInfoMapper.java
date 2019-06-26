package com.cdtu.support.mapper;

import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.NeedInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeedInfoMapper {
    long countByExample(NeedInfoExample example);

    int deleteByExample(NeedInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(NeedInfo record);

    int insertSelective(NeedInfo record);

    List<NeedInfo> selectByExampleWithBLOBs(NeedInfoExample example);

    List<NeedInfo> selectByExample(NeedInfoExample example);

    NeedInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NeedInfo record, @Param("example") NeedInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") NeedInfo record, @Param("example") NeedInfoExample example);

    int updateByExample(@Param("record") NeedInfo record, @Param("example") NeedInfoExample example);

    int updateByPrimaryKeySelective(NeedInfo record);

    int updateByPrimaryKeyWithBLOBs(NeedInfo record);

    int updateByPrimaryKey(NeedInfo record);
}