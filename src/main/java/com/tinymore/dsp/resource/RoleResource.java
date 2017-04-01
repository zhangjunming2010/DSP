package com.tinymore.dsp.resource;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tinymore.dsp.model.MRole;
import com.tinymore.dsp.service.IRoleService;

@Controller  
@RequestMapping("/role")
@CrossOrigin(origins="*")
public class RoleResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(RoleResource.class);
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/addRole",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String addRole(@RequestBody String request) {
		String code = "0";
		String data = "新增失败，";
		JSONObject ret = new JSONObject();
		try {
			JSONObject params = JSON.parseObject(request);
			String title = params.getString("rtitle");
			MRole role = roleService.getRoleByTitle(title);
			if(role == null) {
				role = new MRole();
				role.setRtitle(title);
				role.setRdesc(params.getString("rdesc"));
				role.setRstatus(1);
				roleService.addRole(role);
				code = "1";
				data = "新增角色成功！";
			}else {
				data = data + "该角色名称已存在！";
			}
		} catch (Exception e) {
			data = data + "新增角色接口异常！";
			log.error(e);
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/updateRole",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateRole(@RequestBody MRole role) {
		String code = "0";
		String data = "修改失败，";
		JSONObject ret = new JSONObject();
		try {
			String title = role.getRtitle();
			MRole tmp = roleService.getRoleByTitle(title);
			if(tmp == null) {
				roleService.updateRole(role);
				code = "1";
				data = "修改角色成功！";
			}else {
				data = data + "该角色名称已存在！";
			}
		} catch (Exception e) {
			data = data + "修改角色接口异常！";
			log.error(e);
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/deleteRole",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public void deleteRole(@RequestBody String request) {
		Integer rid = Integer.parseInt(request);
		try {
			roleService.deleteRole(rid);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	@RequestMapping(value = "/selectRole",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MRole> selectRole(@RequestBody String request){
		List<MRole> roles = new ArrayList<MRole>();
		try {
			roles = roleService.getRoleListBySearchKey(request);
		} catch (Exception e) {
			log.error(e);
		}
		return roles;
	}

}
