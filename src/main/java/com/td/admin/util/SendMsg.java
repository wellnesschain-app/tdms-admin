package com.td.admin.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendMsg {
	/**
	 * @param[0] 信息内容</br>
	 * @param[1] 发送地址/账号</br>
	 * @param[2] 标题【可为空】</br>
	 * 
	 * @param parameter
	 */

	static String[] email={"service@ubitpro.io","service@ubit.vip","admin@ubit.vip"};
	static String[] pass={"Admin.6655","Admin.6655","ubit^1688"};
	static String tomail="";
	static String topass="";
	static String[] blacklist = {"chacuo.net","027168.com"};

	public static void sendMsg(String... parameter) {
		try {
			if (parameter[1]!=null&&!parameter[1].equals("")) {
				sendSMS(parameter[0], parameter[1],parameter[3],parameter[4],parameter[5],parameter[6]);
			}
		} catch (Exception e) {
			System.out.printf(e.getMessage()+"发送信息失败,发送人:%s,发送内容%s\n", parameter[1], parameter[0]);
		}

	}


	public static void sendSMS(String content, String phone,String url,String userName,String pwd,String subid) throws Exception {
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		//下发时间
		String mttime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.print(mttime);
		Map<String,String> param = new HashMap<String,String>();
		param.put("name",userName);
		param.put("pwd", SmsApi.MD51(pwd + mttime));
		try {
			param.put("content", content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		param.put("phone", phone);
//		param.put("subid",subid);
		param.put("mttime", mttime);
		param.put("rpttype", "1");
		String result = SmsApi.sendPost(url,param);
	}

	public static void main(String[] args) {
		sendMsg("感谢您注册UBIT，您的验证码是：6473", "1048516133@qq.com","UBIT-注册验证码","smtp.bestedm.org","BZX@info.bzx.com","dfD47WAKuyM","2525");
//		sendMsg("感谢您注册UBIT，您的验证码是：6473", "18309295990","BZX-注册验证码","http://106.14.55.160:9000","gxlxtz","d41d8cd98f00b204e9800998ecf8427e","");
	}

}
