package com.tinymore.dsp.service;

import java.util.List;

import com.tinymore.dsp.model.MUser;

public interface IUserService {
	
	public void addUser(MUser user);
	
	public MUser getUserByAccount(String uAccount);
	
	public List<MUser> getUserListBySearchKey(String searchkey);

}
