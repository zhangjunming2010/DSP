package com.tinymore.dsp.service;

import java.util.List;

import com.tinymore.dsp.model.MAuthority;
import com.tinymore.dsp.model.MUser;

public interface IUserService {
	
	public void addUser(MUser user);
	
	public void updateUser(MUser user);
	
	public void deleteRole(Integer id);
	
	public MUser getUserByAccount(String uAccount);
	
	public List<MUser> getUserListBySearchKey(String searchkey,Integer rid);
	
	public List<MAuthority> getUserAuthority(Integer rid);

}
