package com.tinymore.dsp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class IdCardUtil {

	public String createIdNumber(String Province,String Birthday,String length,String last){
		JSONObject ret = new JSONObject();
		String code = "-1";
		String Ai,data = "";
		Ai = judgeBirthday(Birthday);
		if(Ai.equals("ok")){
			String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
					"3", "2" };
			String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
					"9", "10", "5", "8", "4", "2" };
			String s2 = randomNum(4);
			String s4 = randomNum(3);
			if(Birthday.length() == 0){
				GregorianCalendar gc = new GregorianCalendar();
				Birthday = gc.get(Calendar.YEAR)-randomYear()+"";
				int tmp = gc.get(Calendar.MONTH)+1;
				int tmpdate = gc.get(Calendar.DATE);
				String month = tmp+"";
				String date = tmpdate+"";
				if(tmp<10){
					month = "0"+tmp;
				}
				if(tmpdate<10){
					date = "0"+tmpdate;
				}
				Birthday = Birthday + month +date;
			}
			int TotalmulAiWi = 0;
			if(length.equals("18")){
				if(!last.equals("1")){
					Ai = Province+s2+Birthday+s4;
					for (int i = 0; i < 17; i++) {
						TotalmulAiWi = TotalmulAiWi
								+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
								* Integer.parseInt(Wi[i]);
					}
					int modValue = TotalmulAiWi % 11;
					String strVerifyCode = ValCodeArr[modValue];
					Ai = Ai + strVerifyCode;
				}else{
					Ai = Province+s2+Birthday;
					for (int i = 0; i < 14; i++) {
						TotalmulAiWi = TotalmulAiWi
								+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
								* Integer.parseInt(Wi[i]);
					}
					while(true){
						s4 = randomNum(3);
						for (int i = 0; i < 3; i++) {
							TotalmulAiWi = TotalmulAiWi
									+ Integer.parseInt(String.valueOf(s4.charAt(i)))
									* Integer.parseInt(Wi[i+14]);
						}
						int modValue = TotalmulAiWi % 11;
						if(modValue == 2){
							Ai = Ai + s4 +"X";
							break;
						}
					}
				}
			}else{
				Ai = Province+s2+Birthday.substring(2, Birthday.length())+s4;
			}
			JSONObject obj = new JSONObject();
			obj.put("year", Birthday.substring(0,4));
			obj.put("month", Birthday.substring(4,6));
			obj.put("day", Birthday.substring(6,8));
			obj.put("idcardnbr", Ai);
			data = JSON.toJSONString(obj);
			code = "0";
		}else{
			data = Ai;
		}
		ret.put("code", code);
		ret.put("data", data);
		return JSON.toJSONString(ret);
	}
	
	/**
	 * 判断出生年月日是否正确（格式：19881222）
	 * @param Birthday
	 * @return
	 */
	private String judgeBirthday(String Birthday){
		String info = "";
		int length = Birthday.length();
		if(length == 8){
			if(isNumeric(Birthday)){
				String strYear = Birthday.substring(0, 4);// 年
				String strMonth = Birthday.substring(4, 6);// 月
				String strDay = Birthday.substring(6, 8);// 日
				if (isDate(strYear + "-" + strMonth + "-" + strDay)) {
					GregorianCalendar gc = new GregorianCalendar();
					SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
								|| (gc.getTime().getTime() - s.parse(
										strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
							info = "年份无效！";
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (java.text.ParseException e) {
						e.printStackTrace();
					}
					if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
						info = "月份无效！";
					}
					if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
						info = "日期无效！";
					}
					info = "ok";
				}else{
					info = "出生年月日无效！";
				}
			}else{
				info = "出生年月日格式错误！格式：19001201";
			}
		}else if(length == 0){
			info = "ok";
		}else{
			info = "出生年月日长度错误！格式：19001201";
		}
		return info;
	}
	
	/**
	 * 判断是否为日期
	 * @param strDate
	 * @return
	 */
	private static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 生成N位随机正整数
	 * @param n
	 * @return
	 */
	private String randomNum(int n){
		Random ran=new Random();
		String s = "";
	      for(int  i=0;i<n;i++){
	    	  s = s + ran.nextInt(10);
	    }
		return s;
	}
	
	/**
	 * 生成随机4位年（如：1988）
	 * @return
	 */
	private int randomYear(){
		int ran = new Random().nextInt(50);
		if(ran == 0){
			ran = 2;
		}
		return ran;
	}

}
