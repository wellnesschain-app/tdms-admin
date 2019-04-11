package com.td.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class VerificationCode {
    //秒滴用户ID
    public static final String ACCOUNT_SID = "6705e10e103f4deebdc36728ff4e6b96";
    //秒滴密钥
    public static final String AUTH_TOKEN = "b8bc8c8068334f7fb225f12eacc0c816";
    //秒滴请求地址前半部分
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    //随机数
    public static String randNum = "";



    /*
     * 发送阿里云手机验证码
     * @param phoneNumber
     * @return 请求响应
     * @throws ClientException
     * */
    public static JSONObject sendAliyunMobileCode(String phoneNumber) throws ClientException {
        randNum = VerificationCode.createRandomVcode();
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");//不必修改
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");//不必修改
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIW37lDEgTKupH", "0SM7xOwPxOzixANJkAWiy9zZCj3uNT");//"***"分别填写自己的AccessKey ID和Secret
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");//不必修改
        IAcsClient acsClient = new DefaultAcsClient(profile);//不必修改
        SendSmsRequest request = new SendSmsRequest();//不必修改
        request.setPhoneNumbers(phoneNumber);//****处填写接收方的手机号码
        request.setSignName("巴马水积分");//此处填写已申请的短信签名
        request.setTemplateCode("SMS_151690270");//此处填写获得的短信模版CODE
        request.setTemplateParam("{\"code\":\""+randNum+"\"}");//笔者的短信模版中有${code}, 因此此处对应填写验证码
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//不必修改
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",randNum);
        jsonObject.put("status",sendSmsResponse.getCode());
        jsonObject.put("message",sendSmsResponse.getMessage());
        return jsonObject;
    }

/*    *//**
     * 发送邮箱验证码
     * @param eamil
     * @return 发送的验证码
     *//*
    public static String sendEamilCode(String eamil) {
        HtmlEmail send = new HtmlEmail();
        // 获取随机验证码
        String resultCode = achieveCode();
        try {
            send.setHostName("smtp.qq.com");
            send.setSmtpPort(465);  //端口号
            send.setSSLOnConnect(true); //开启SSL加密
            send.setCharset("utf-8");
            send.addTo(eamil);  //接收者的QQEamil
            send.setFrom("269735929@qq.com", "染月"); //第一个参数是发送者的QQEamil   第二个参数是发送者QQ昵称
            send.setAuthentication("269735929@qq.com", "trfcnohduynxbigj");  //第一个参数是发送者的QQEamil   第二个参数是刚刚获取的授权码
            send.setSubject("小渣渣特给您送上激活链接"); //Eamil的标题  第一个参数是我写的判断上下午，删掉即可
//            send.setMsg("HelloWorld!欢迎大大光临，点击此链接验证邮箱:   <a href='http://localhost:8080/dinner/user/update/email?code="+resultCode+"'>点击此处验证邮箱</a>   请大大签收");   //Eamil的内容
            send.setMsg("HelloWorld!欢迎大大光临，点击此链接验证邮箱:   <a href='http://www.ywming.com/dinner/user/update/email?code="+resultCode+"'>点击此处验证邮箱</a>   请大大签收");   //Eamil的内容
            send.send();//发送
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return resultCode; //同等验证码
    }*/

/*
    *//**
     * 发送通知管理员审核邮件
     * @return 发送的验证码
     *//*
    public static String sendAdminExamineEmail() {
        HtmlEmail send = new HtmlEmail();
        // 获取随机验证码
        String resultCode = achieveCode();
        try {
            send.setHostName("smtp.qq.com");
            send.setSmtpPort(465);  //端口号
            send.setSSLOnConnect(true); //开启SSL加密
            send.setCharset("utf-8");
            send.addTo("Roxywm@163.com");  //接收者的QQEamil
            send.setFrom("269735929@qq.com", "染月"); //第一个参数是发送者的QQEamil   第二个参数是发送者QQ昵称
            send.setAuthentication("269735929@qq.com", "trfcnohduynxbigj");  //第一个参数是发送者的QQEamil   第二个参数是刚刚获取的授权码
            send.setSubject("有新的用户申请信息，请尽快处理！"); //Eamil的标题  第一个参数是我写的判断上下午，删掉即可
//            send.setMsg("HelloWorld！点击此链接进行登录处理：   <a href='http://localhost:8080/dinner/admin/login/'>点击此处</a>");   //Eamil的内容
            send.setMsg("HelloWorld！点击此链接进行登录处理：   <a href='http://www.ywming.com/dinner/admin/login/'>点击此处</a>");   //Eamil的内容
            send.send();//发送
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return resultCode; //同等验证码
    }*/

    /**
     * 获取手机随机验证码
     * @return 所得验证码
     */
    public static String createRandomVcode(){
        Random r = new Random();
        String code = "";
        for(int i=0; i<6; i++){
            int anInt = r.nextInt(10);
            if(i==0&&anInt==0){
                i--;
                continue;
            }
            code+=anInt;
        }
        return code;
    }

    /**
     * 获取邮箱随机验证码
     * @return
     */
    public static String achieveCode() {
        String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }

    /**
     * 秒滴请求参数
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     * @param smsContent
     * @param to
     * @return
     */
    public static String qureyArguments(String ACCOUNT_SID,String AUTH_TOKEN,String smsContent,String to){
        //时间戳
        String timestamp = getTimestamp();
        //签名验证
        String sig = MD5(ACCOUNT_SID,AUTH_TOKEN,timestamp);
        //地址参数拼接
        String str = "accountSid="+ACCOUNT_SID+"&smsContent="+smsContent+
                "&to="+to+"&playTimes=2"+"&timestamp="+timestamp+"&sig="+sig;
        return str;
    }

    /**
     * 获取时间戳
     * @return
     */
    public static String getTimestamp(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * MD5加密
     * @param args
     * @return
     */
    public static String MD5(String... args){
        StringBuffer result = new StringBuffer();
        if(args==null||args.length==0){
            return "";
        }else{
            StringBuffer sb = new StringBuffer();
            for (String string : args) {
                sb.append(string);
            }
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(sb.toString().getBytes());
                for (byte b : bytes) {
                    String hex = Integer.toHexString(b&0xff);//16进制转换
                    if(hex.length()==1){
                        result.append("0"+hex);
                    }else{
                        result.append(hex);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }


    /**
     * 获取邀请码
     * @return
     * @throws Exception
     */
    public static String getRandomCode() throws Exception {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
        }
        code = MyCrypt.base64Encoder(code);

        return code;
    }


    public static void main(String[] args) throws InterruptedException {
        while (true){
            long nd = 1000 * 24 * 60 * 60;//每天毫秒数

            long nh = 1000 * 60 * 60;//每小时毫秒数

            long nm = 1000 * 60;//每分钟毫秒数
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, 1);
            cal.set(Calendar.HOUR_OF_DAY, 12);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            long l = System.currentTimeMillis();
            long l1 = cal.getTime().getTime() - l;
            long hour = l1 % nd / nh; // 计算差多少小时
            long min = l1 % nd % nh / nm;  // 计算差多少分钟
            long s = l1 % nd % nh % nm/1000;  // 计算差多少分钟
            String hours="";
            String mins="";
            String second="";
            if(String.valueOf(s).length()==1){
                second= "0" + String.valueOf(s);
            }else{
                second=String.valueOf(s);
            }
            if(String.valueOf(min).length()==1){
                mins= "0" + String.valueOf(min);
            }else{
                mins=String.valueOf(min);
            }
            if(String.valueOf(hour).length()==1){
                hours= "0" + String.valueOf(hour);
            }else{
                hours=String.valueOf(hour );
            }
            System.out.println(hours+":"+mins+":"+second);

            Thread.sleep(1000);
        }




    }
}
