package com.tinymore.dsp.service;

import java.util.List;

import com.tinymore.dsp.model.MRole;

public interface IRoleService {
	
	public void addRole(MRole role);
	
	public void updateRole(MRole role);
	
	public void deleteRole(Integer id);
	
	public MRole getRoleByTitle(String title);
	
	public List<MRole> getRoleListBySearchKey(String searchkey);

}
