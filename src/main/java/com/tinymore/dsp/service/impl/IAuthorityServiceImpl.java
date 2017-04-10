package com.tinymore.dsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.dsp.dao.MAuthorityMapper;
import com.tinymore.dsp.model.MAuthority;
import com.tinymore.dsp.service.IAuthorityService;

@Service("IAuthorityService")
public class IAuthorityServiceImpl implements IAuthorityService {
	
	@Autowired
	private MAuthorityMapper authorityDao;

	@Override
	public void addAuthority(MAuthority authority) {
		authorityDao.insert(authority);
	}

	@Override
	public void updateAuthority(MAuthority authority) {
		authorityDao.updateByPrimaryKey(authority);
	}

	@Override
	public void deleteAuthority(Integer id) {
		authorityDao.deleteByPrimaryKey(id);
	}

	@Override
	public MAuthority getAuthorityByTitle(String title) {
		return authorityDao.selectByTitle(title);
	}

	@Override
	public List<MAuthority> getAuthorityListBySearchKey(String searchkey) {
		return authorityDao.selectListBySearchKey(searchkey);
	}

}
