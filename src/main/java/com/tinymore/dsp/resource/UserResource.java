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
import com.tinymore.dsp.model.MUser;
import com.tinymore.dsp.service.IUserService;
import com.tinymore.dsp.utils.PasswordUtil;

@Controller  
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserResource {

	private static final Logger log = (Logger) LogManager.getLogger(UserResource.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String addUser(@RequestBody String request) {
		String code = "0";
		String data = "新增失败，";
		JSONObject ret = new JSONObject();
		try {
			JSONObject params = JSON.parseObject(request);
			String uaccount = params.getString("uaccount");
			String ucname = params.getString("ucname");
			String uemail = params.getString("uemail");
			String utelephone = params.getString("utelephone");
			String udesc = params.getString("udesc");
			String upassword = PasswordUtil.randomPwd(6);
			Integer rid = Integer.parseInt(params.getString("rid"));
			Integer ustatus = 1;
			MUser user = userService.getUserByAccount(uaccount);
			if(user == null) {
				user = new MUser();
				user.setRid(rid);
				user.setUaccount(uaccount);
				user.setUcname(ucname);
				user.setUdesc(udesc);
				user.setUemail(uemail);
				user.setUpassword(PasswordUtil.encode(upassword));
				user.setUstatus(ustatus);
				user.setUtelephone(utelephone);
				userService.addUser(user);
				code = "1";
				data = "新增用户成功！系统默认密码：" + upassword;
			}else {
				data = data + "该登录账号已存在！";
			}
		} catch (Exception e) {
			data = data + "新增用户接口异常！";
			log.error(e);
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String updateUser(@RequestBody MUser user) {
		String code = "0";
		String data = "修改失败，";
		JSONObject ret = new JSONObject();
		try {
			userService.updateUser(user);
			code = "1";
			data = "修改用户成功！";
		} catch (Exception e) {
			data = data + "修改用户接口异常！";
			log.error(e);
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	@RequestMapping(value = "/deleteUser",method = RequestMethod.POST,produces="application/text; charset=utf-8")
	@ResponseBody
	public String deleteUser(@RequestBody String request) {
		String code = "0";
		String resp = "删除失败，";
		String ucname = "用户：";
		int err = 0;
		JSONArray arry = JSON.parseArray(request);
		for(Object obj : arry) {
			JSONObject json = (JSONObject)obj;
			Integer uid  = Integer.parseInt(json.get("uid").toString());
			try {
				userService.deleteRole(uid);
			} catch (Exception e) {
				err++;
				log.error(e);
				ucname = ucname + json.get("ucname").toString()+"、";
			}
		}
		if(err == 0) {
			code = "1";
			resp = "所选角色删除成功！";
		}
		if(code.equals("0")) {
			resp = resp + ucname.substring(0, ucname.length()-1) + "删除失败！";
		}
		return resp;
	}
	
	@RequestMapping(value = "/selectUser",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MUser> selectRole(@RequestBody String request){
		List<MUser> users = new ArrayList<MUser>();
		JSONObject obj = JSON.parseObject(request);
		String searchKey = obj.getString("searchKey");
		Integer rid = obj.getInteger("rid");
		try {
			users = userService.getUserListBySearchKey(searchKey,rid);
		} catch (Exception e) {
			log.error(e);
		}
		return users;
	}
	
	@RequestMapping(value = "/selectUserAuthority",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MAuthority> selectUserAuthority(@RequestBody String request){
		List<MAuthority> auths = new ArrayList<MAuthority>();
		try {
			auths = userService.getUserAuthority(Integer.parseInt(request));
		} catch (Exception e) {
			log.error(e);
		}
		return auths;
	}
}
