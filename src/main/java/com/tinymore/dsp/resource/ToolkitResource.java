package com.tinymore.dsp.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tinymore.dsp.utils.IdCardUtil;


@Controller  
@RequestMapping("/toolkit")
@CrossOrigin(origins="*")
public class ToolkitResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(ToolkitResource.class);
	
	@RequestMapping(value = "/createIdCard",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public String selectRole(@RequestBody String request){
		String response = "";
		IdCardUtil util = new IdCardUtil();
		JSONObject obj = JSON.parseObject(request);
		String province = obj.getString("province");
		String birthday = obj.getString("birthday");
		String length = obj.getString("length");
		String last = obj.getString("last");
		try {
			response = util.createIdNumber(province, birthday, length, last);
		} catch (Exception e) {
			log.error(e);
		}
		return response;
	}

}
