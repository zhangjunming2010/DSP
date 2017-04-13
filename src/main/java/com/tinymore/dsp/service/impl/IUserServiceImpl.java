package com.tinymore.dsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.dsp.dao.MUserMapper;
import com.tinymore.dsp.model.MAuthority;
import com.tinymore.dsp.model.MUser;
import com.tinymore.dsp.service.IUserService;

@Service("IUserService")
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	private MUserMapper userDao;
	
	@Override
	public void addUser(MUser user) {
		userDao.insert(user);
	}
	
	@Override
	public void updateUser(MUser user) {
		userDao.updateByPrimaryKey(user);
	}
	
	@Override
	public void deleteRole(Integer id) {
		userDao.deleteByPrimaryKey(id);
	}

	@Override
	public MUser getUserByAccount(String uAccount) {
		return userDao.selectByAccount(uAccount);
	}
	
	@Override
	public List<MUser> getUserListBySearchKey(String searchkey,Integer rid){
		return userDao.selectListBySearchKey(searchkey,rid);
	}
	
	@Override
	public List<MAuthority> getUserAuthority(Integer rid){
		return userDao.selectUserAuthority(rid);
	}

}
