package com.tinymore.dsp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.dsp.model.MAuthority;
import com.tinymore.dsp.model.MUser;

public interface MUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(Integer uid);
    
    MUser selectByAccount(@Param("uaccount")String uaccount);
    
    List<MUser> selectListBySearchKey(@Param("searchkey")String searchkey,@Param("rid")Integer rid);
    
    List<MAuthority> selectUserAuthority(@Param("rid")Integer rid);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
}