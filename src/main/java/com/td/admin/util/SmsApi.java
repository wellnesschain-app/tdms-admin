package com.td.admin.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SmsApi {

	public static void main(String[] args) {
		//接口地址
		String url = "http://XXXXXXXX:XXXXX/HttpSmsMt";
		//下发时间
		String mttime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.print(mttime);
		Map<String,String> param = new HashMap<String,String>();
		param.put("name","******");
		param.put("pwd", SmsApi.MD51("****************************" + mttime));
		try {
			param.put("content", "【阅信】验证码888888.");
			//param.put("content","123456");语音验证码内容
		} catch (Exception e) {
			e.printStackTrace();
		}
		param.put("phone", "13800000000");
		param.put("subid","");
		param.put("mttime", mttime);
		param.put("rpttype", "1");
		String result = sendPost(url,param);
		System.out.print(result);
	}
	public static String sendPost(String url,Map<String,String> params) {
		URL u = null;
		HttpURLConnection con = null;
		//构建请求参数
		StringBuffer sb = new StringBuffer();
		if(params != null) {
			for(Map.Entry<String,String> e : params.entrySet()) {
				sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
			}
			sb.substring(0,sb.length() - 1);
		}
		//尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(6000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.disconnect();
			}
		}
		//读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp).append("\n");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	public static String MD51(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for(int i = 0;i < charArray.length;i++)
			byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for(int i = 0; i < md5Bytes.length;i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if(val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	public final static String MD5(String s) {
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = s.getBytes();
			//获得MD5摘要算法的MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			//使用指定的字节更新摘要
			mdInst.update(btInput);
			//获得密文
			byte[] md = mdInst.digest();
			//把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0;i < j;i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>>4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

