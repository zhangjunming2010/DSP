package com.tinymore.dsp.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping("/login")
@CrossOrigin(origins="*")
public class LoginResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(LoginResource.class);
	
	@RequestMapping(value = "/getCode",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public void getCode() {
		
	}

}
