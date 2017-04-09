package com.tinymore.dsp.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tinymore.dsp.model.MUser;
import com.tinymore.dsp.service.IUserService;
import com.tinymore.dsp.utils.PasswordUtil;
import com.tinymore.dsp.utils.VerifyCodeUtil;

@Controller  
@RequestMapping("/")
@CrossOrigin(origins="*")
public class LoginResource {
	
	private static final Logger log = (Logger) LogManager.getLogger(LoginResource.class);
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 验证码生成并反馈给前端
	 * @return 验证码
	 */
	@RequestMapping(value = "/getCode",method = RequestMethod.POST,produces="application/text; charset=utf-8")
	@ResponseBody
	public String getCode() {
		String verifyCode = VerifyCodeUtil.generateVerifyCode(4); 
		return verifyCode;
	}
	
	/**
	 * 生成验证码图片
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/getCodeImg",method = RequestMethod.GET,produces="application/json; charset=utf-8")
	public void getCodeImg(HttpServletResponse response,HttpServletRequest request) {
		// 设置响应的类型格式为图片格式  
	    response.setContentType("image/jpeg");
	    //禁止图像缓存。  
	    response.setHeader("Pragma", "no-cache");  
	    response.setHeader("Cache-Control", "no-cache");  
	    response.setDateHeader("Expires", 0);
	    String verifyCode = request.getParameter("verifyCode");
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
	public String userLogin(@RequestBody String request) {
		String code = "0";
		String data = "账号或则密码错误！";
		JSONObject ret = new JSONObject();
		try {
			JSONObject params = JSON.parseObject(request);
			String account = params.getString("account");
			String password = PasswordUtil.encode(params.getString("password"));
			MUser user = userService.getUserByAccount(account);
			if(user != null) {
				if(user.getUpassword().equals(password)) {
					code = "1";
					user.setRid(null);
					user.setUid(null);
					user.setUstatus(null);
					user.setUpassword(null);
					data = JSON.toJSONString(user);
				}
			}
		} catch (Exception e) {
			data = "登录接口异常！";
			log.error(e);
		}		
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	
	
}
