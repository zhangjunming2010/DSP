package com.tinymore.dsp.dao;

import org.apache.ibatis.annotations.Param;

import com.tinymore.dsp.model.MUser;

public interface MUserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(Long uid);
    
    MUser selectByAccount(@Param("uaccount")String uaccount);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
}