package com.cdtu.support.mapper;

import com.cdtu.support.pojo.School;
import com.cdtu.support.pojo.SchoolExample;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolMapper {
    long countByExample(SchoolExample example);

    int deleteByExample(SchoolExample example);

    int deleteByPrimaryKey(String id);

    int insert(SchoolWithBLOBs record);

    int insertSelective(SchoolWithBLOBs record);

    List<SchoolWithBLOBs> selectByExampleWithBLOBs(SchoolExample example);

    List<School> selectByExample(SchoolExample example);

    SchoolWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SchoolWithBLOBs record, @Param("example") SchoolExample example);

    int updateByExampleWithBLOBs(@Param("record") SchoolWithBLOBs record, @Param("example") SchoolExample example);

    int updateByExample(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByPrimaryKeySelective(SchoolWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SchoolWithBLOBs record);

    int updateByPrimaryKey(School record);
}