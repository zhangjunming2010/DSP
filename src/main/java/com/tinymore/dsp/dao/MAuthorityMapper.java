package com.tinymore.dsp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.dsp.model.MAuthority;

public interface MAuthorityMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(MAuthority record);

    int insertSelective(MAuthority record);

    MAuthority selectByPrimaryKey(Integer aid);
    
    MAuthority selectByTitle(@Param("atitle")String atitle);
    
    List<MAuthority> selectListBySearchKey(@Param("searchkey")String searchkey);

    int updateByPrimaryKeySelective(MAuthority record);

    int updateByPrimaryKey(MAuthority record);
}