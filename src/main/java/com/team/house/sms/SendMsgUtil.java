package com.team.house.sms;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class SendMsgUtil {
	
	//用户名
	private static String Uid = "青鸟111";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
/*	//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "18772790567";
	
	//短信内容
	private static String smsText = "验证码：8888";*/

	/**
	 * 发送短信
	 * @param tels  手机号
	 * @param msg  短信内容
	 * @return
	 */
	public int sendMsg(String tels,String msg) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key,msg,tels);
		return result;
	}

	public static void main(String[] args) {
		//测试发短信
		SendMsgUtil sendMsgUtil = new SendMsgUtil();
		int result = sendMsgUtil.sendMsg("18672688871", "帅哥你好");
		if (result>0){
			System.out.println("成功");
		}else {
			System.out.println("失败"+result);
		}
	}
}
