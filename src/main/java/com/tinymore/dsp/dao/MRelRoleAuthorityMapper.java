package com.tinymore.dsp.dao;

import com.tinymore.dsp.model.MRelRoleAuthorityKey;

public interface MRelRoleAuthorityMapper {
    int deleteByPrimaryKey(MRelRoleAuthorityKey key);

    int insert(MRelRoleAuthorityKey record);

    int insertSelective(MRelRoleAuthorityKey record);
}