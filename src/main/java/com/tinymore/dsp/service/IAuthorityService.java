package com.tinymore.dsp.service;

import java.util.List;

import com.tinymore.dsp.model.MAuthority;

public interface IAuthorityService {
	
public void addAuthority(MAuthority authority);
	
	public void updateAuthority(MAuthority authority);
	
	public void deleteAuthority(Integer id);
	
	public MAuthority getAuthorityByTitle(String title);
	
	public List<MAuthority> getAuthorityListBySearchKey(String searchkey);
	
	public List<MAuthority> selectListNotInRole(Integer rid);

}
