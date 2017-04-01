package com.tinymore.dsp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinymore.dsp.dao.MRoleMapper;
import com.tinymore.dsp.model.MRole;
import com.tinymore.dsp.service.IRoleService;

@Service("IRoleService")
public class IRoleServiceImpl implements IRoleService {
	
	@Autowired
	private MRoleMapper roleDao;

	@Override
	public void addRole(MRole role) {
		roleDao.insert(role);
	}
	
	@Override
	public void updateRole(MRole role) {
		roleDao.updateByPrimaryKey(role);
	}
	
	@Override
	public void deleteRole(Integer id) {
		roleDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public MRole getRoleByTitle(String title) {
		return roleDao.selectByTitle(title);
	}
	
	@Override
	public List<MRole> getRoleListBySearchKey(String searchkey){
		return roleDao.selectListBySearchKey(searchkey);
	}

}
