package com.cdtu.support.mapper;

import com.cdtu.support.pojo.Policy;
import com.cdtu.support.pojo.PolicyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PolicyMapper {
    long countByExample(PolicyExample example);

    int deleteByExample(PolicyExample example);

    int deleteByPrimaryKey(String id);

    int insert(Policy record);

    int insertSelective(Policy record);

    List<Policy> selectByExample(PolicyExample example);

    Policy selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Policy record, @Param("example") PolicyExample example);

    int updateByExample(@Param("record") Policy record, @Param("example") PolicyExample example);

    int updateByPrimaryKeySelective(Policy record);

    int updateByPrimaryKey(Policy record);
}