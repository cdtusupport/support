package com.cdtu.support.mapper;

import com.cdtu.support.pojo.NeedJoin;
import com.cdtu.support.pojo.NeedJoinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeedJoinMapper {
    long countByExample(NeedJoinExample example);

    int deleteByExample(NeedJoinExample example);

    int deleteByPrimaryKey(String id);

    int insert(NeedJoin record);

    int insertSelective(NeedJoin record);

    List<NeedJoin> selectByExample(NeedJoinExample example);

    NeedJoin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NeedJoin record, @Param("example") NeedJoinExample example);

    int updateByExample(@Param("record") NeedJoin record, @Param("example") NeedJoinExample example);

    int updateByPrimaryKeySelective(NeedJoin record);

    int updateByPrimaryKey(NeedJoin record);
}