package com.tinymore.dsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.dsp.dao.MUserMapper;
import com.tinymore.dsp.model.MUser;
import com.tinymore.dsp.service.IUserService;

@Service("IUserService")
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	private MUserMapper userDao;

	@Override
	public MUser getUserByAccount(String uAccount) {
		return userDao.selectByAccount(uAccount);
	}

}