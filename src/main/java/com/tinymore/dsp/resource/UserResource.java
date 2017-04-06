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

import com.tinymore.dsp.model.MUser;
import com.tinymore.dsp.service.IUserService;

@Controller  
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserResource {

	private static final Logger log = (Logger) LogManager.getLogger(UserResource.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/selectUser",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public List<MUser> selectRole(@RequestBody String request){
		List<MUser> users = new ArrayList<MUser>();
		try {
			users = userService.getUserListBySearchKey(request);
		} catch (Exception e) {
			log.error(e);
		}
		return users;
	}
}
