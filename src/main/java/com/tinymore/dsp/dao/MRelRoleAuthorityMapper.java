package com.tinymore.dsp.dao;

import org.apache.ibatis.annotations.Param;

import com.tinymore.dsp.model.MRelRoleAuthorityKey;

public interface MRelRoleAuthorityMapper {
	int deleteByRid(@Param("rid")Integer rid);
	
    int deleteByPrimaryKey(MRelRoleAuthorityKey key);

    int insert(MRelRoleAuthorityKey record);

    int insertSelective(MRelRoleAuthorityKey record);
}