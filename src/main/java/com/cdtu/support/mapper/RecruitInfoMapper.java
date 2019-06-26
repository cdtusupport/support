package com.cdtu.support.mapper;

import com.cdtu.support.pojo.RecruitInfo;
import com.cdtu.support.pojo.RecruitInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitInfoMapper {
    long countByExample(RecruitInfoExample example);

    int deleteByExample(RecruitInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(RecruitInfo record);

    int insertSelective(RecruitInfo record);

    List<RecruitInfo> selectByExampleWithBLOBs(RecruitInfoExample example);

    List<RecruitInfo> selectByExample(RecruitInfoExample example);

    RecruitInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByExample(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByPrimaryKeySelective(RecruitInfo record);

    int updateByPrimaryKeyWithBLOBs(RecruitInfo record);

    int updateByPrimaryKey(RecruitInfo record);
}