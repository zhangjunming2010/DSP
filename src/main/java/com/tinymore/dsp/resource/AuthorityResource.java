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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tinymore.dsp.model.MAuthority;
import com.tinymore.dsp.service.IAuthorityService;

@Controller  
@RequestMapping("/authority")
@CrossOrigin(origins="*")
public class AuthorityResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(AuthorityResource.class);
	
	@Autowired
	private IAuthorityService authorityService;
	
	@RequestMapping(value = "/addAuthority",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String addAuthority(@RequestBody String request) {
		String code = "0";
		String data = "新增失败，";
		JSONObject ret = new JSONObject();
		try {
			JSONObject params = JSON.parseObject(request);
			String title = params.getString("atitle");
			String value = params.getString("avalue");
			String desc = params.getString("adesc");
			MAuthority auth = authorityService.getAuthorityByTitle(title);
			if(auth == null) {
				auth = new MAuthority();
				auth.setAtitle(title);
				auth.setAvalue(value);
				auth.setApid(0);
				auth.setAstatus(1);
				auth.setAdesc(desc);
				authorityService.addAuthority(auth);
				code = "1";
				data = "新增权限成功！";
			}else {
				data = data + "该权限名称已存在！";
			}
		} catch (Exception e) {
			data = data + "新增权限接口异常！";
			log.error(e);
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/updateAuthority",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateRole(@RequestBody MAuthority authority) {
		String code = "0";
		String data = "修改失败，";
		JSONObject ret = new JSONObject();
		try {
			String title = authority.getAtitle();
			MAuthority tmp = authorityService.getAuthorityByTitle(title);
			if(tmp == null) {
				authorityService.updateAuthority(authority);
				code = "1";
				data = "修改权限成功！";
			}else {
				if(authority.getAid() == tmp.getAid()) {
					authorityService.updateAuthority(authority);
					code = "1";
					data = "修改权限成功！";
				}else {
					data = data + "该权限名称已存在！";
				}
			}
		} catch (Exception e) {
			data = data + "修改权限接口异常！";
			log.error(e);
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/deleteAuthority",method = RequestMethod.POST,produces="application/text; charset=utf-8")
	@ResponseBody
	public String deleteAuthority(@RequestBody String request) {
		String code = "0";
		String resp = "删除失败，";
		String atitle = "权限：";
		int err = 0;
		JSONArray arry = JSON.parseArray(request);
		for(Object obj : arry) {
			JSONObject json = (JSONObject)obj;
			Integer id = Integer.parseInt(json.get("aid").toString());
			try {
				authorityService.deleteAuthority(id);
			} catch (Exception e) {
				err++;
				log.error(e);
				atitle = atitle + json.get("atitle").toString()+"、";
			}
		}
		if(err == 0) {
			code = "1";
			resp = "所选权限删除成功！";
		}
		if(code.equals("0")) {
			resp = resp + atitle.substring(0, atitle.length()-1) + "存在关联角色！";
		}
		return resp;
	}
	
	@RequestMapping(value = "/selectAuthority",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MAuthority> selectAuthority(@RequestBody String request){
		List<MAuthority> authoritys = new ArrayList<MAuthority>();
		try {
			authoritys = authorityService.getAuthorityListBySearchKey(request);
		} catch (Exception e) {
			log.error(e);
		}
		return authoritys;
	}
	
	
	@RequestMapping(value = "/selectAuthorityNotInRole",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MAuthority> selectAuthorityNotInRole(@RequestBody String request){
		List<MAuthority> authoritys = new ArrayList<MAuthority>();
		try {
			authoritys = authorityService.selectListNotInRole(Integer.parseInt(request));
		} catch (Exception e) {
			log.error(e);
		}
		return authoritys;
	}
	

}
