package com.tinymore.dsp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinymore.dsp.model.MRole;

public interface MRoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(MRole record);

    int insertSelective(MRole record);

    MRole selectByPrimaryKey(Integer rid);
    
    MRole selectByTitle(@Param("rtitle")String rtitle);
    
    List<MRole> selectListBySearchKey(@Param("searchkey")String searchkey);

    int updateByPrimaryKeySelective(MRole record);

    int updateByPrimaryKey(MRole record);
}