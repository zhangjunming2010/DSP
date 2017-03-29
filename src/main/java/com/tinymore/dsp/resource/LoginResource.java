package com.tinymore.dsp.resource;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinymore.dsp.utils.VerifyCodeUtil;

@Controller  
@RequestMapping("/")
@CrossOrigin(origins="*")
public class LoginResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(LoginResource.class);
	
	/**
	 * 验证码获取
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCode",method = RequestMethod.GET,produces="application/json; charset=utf-8")
	public void getCode(HttpServletResponse response) {
		// 设置响应的类型格式为图片格式  
	    response.setContentType("image/jpeg");  
	    //禁止图像缓存。  
	    response.setHeader("Pragma", "no-cache");  
	    response.setHeader("Cache-Control", "no-cache");  
	    response.setDateHeader("Expires", 0);
        
        //生成随机字串 
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4); 
        Cookie cookie = new Cookie("verifyCode", verifyCode.toLowerCase());
        cookie.setMaxAge(3 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
        //生成图片 
        int w = 100, h = 42; 
        try {
			VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			log.error(e);
		} 
	}
	
	/**
	 * 用户登录
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/userLogin",method = RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public void userLogin(@RequestBody String params) {
		System.out.println(params);
	}
	
}
