package com.tinymore.dsp.utils;

import java.util.Random;

import org.glassfish.jersey.internal.util.Base64;

public class PasswordUtil {

	/**
	 * 加密
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encode(String str) {
		return Base64.encodeAsString(str.getBytes());
	}

	/**
	 * 解密
	 * 
	 * @param bytes
	 * @return
	 */
	public static String decode(String str) {
		return Base64.decodeAsString(str.getBytes());
	}

	/**
	 * 随机密码生成长度自定义
	 * 
	 * @param length
	 * @return
	 */
	public static String randomPwd(Integer length) {
		String password = "";
		char[] charAndnum = "234567890ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz!@#`".toCharArray();
		Random random = new Random(); //用于产生随机数
		for(int n=0;n<length;n++) {
			password = password + charAndnum[random.nextInt(61)];
		}
		return password;
	}

//	public static void main(String[] args) {
//		System.out.println(randomPwd(8));
//	}

}
