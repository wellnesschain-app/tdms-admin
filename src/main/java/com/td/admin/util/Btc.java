package com.td.admin.util;

import com.googlecode.jsonrpc4j.Base64;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.util.List;
public class Btc {


    //usdt转账
    public String omni_funded_send(Map<String,Object> map){
        String user = "tjj666";
        String password = "tjj888888";
        String cred = Base64.encodeBytes((user + ":" +password).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        //身份认证
        headers.put("Authorization", "Basic " + cred);
        String result=null;
        String fromaddress="1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn";//提币手续费钱包
        String toaddress=map.get("toaddress").toString();//转出钱包
        String amount=map.get("amount").toString();//转出金额
        String feeaddress="1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn";
        try{
            JsonRpcHttpClient client = new JsonRpcHttpClient( new URL("http://47.91.221.217:19910"),headers);
            result = (String) client.invoke("omni_funded_send",new Object[]{fromaddress,toaddress,31,amount,feeaddress},Object.class);
        } catch (MalformedURLException e) {
             e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }




    //查询手续费钱包余额
    public boolean btcbalance() throws Throwable {
        Map<String, Object> map = new HashMap<>();
        String user = "tjj666";
        String password = "tjj888888";
        String cred = Base64.encodeBytes((user + ":" +password).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        //身份认证
        headers.put("Authorization", "Basic " + cred);
        JsonRpcHttpClient client = new JsonRpcHttpClient( new URL("http://47.91.221.217:19910"),headers);
        Object[] test = new Object[] {"1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn"};
        List<Map<String, Object>> omni_listtransactions = (List<Map<String, Object>>) client.invoke("listunspent", new Object[] {0,9999,test},Object.class);
        String btcbalance= omni_listtransactions.get(0).get("amount").toString();
        if(new BigDecimal(btcbalance).compareTo(new BigDecimal("0.0004"))>0){
            return true;
        }else{
            return false;
        }
    }



    //查询提币钱包usdt余额
    public  BigDecimal usdtbalance()throws Throwable{
        Map<String, Object> map = new HashMap<>();
        String user = "tjj666";
        String password = "tjj888888";
        String cred = Base64.encodeBytes((user + ":" +password).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        //身份认证
        headers.put("Authorization", "Basic " + cred);
        JsonRpcHttpClient client = new JsonRpcHttpClient( new URL("http://47.91.221.217:19910"),headers);
        Map<String, Object> omni_listtransactions = (Map<String, Object>)  client.invoke("omni_getbalance", new Object[] {"1GqEckbS1fnSUd2p4TcgRNBjzA5KRTkMFn",31}, Object.class);
        String balance = omni_listtransactions.get("balance").toString();
        BigDecimal bd=new BigDecimal(balance);
        return bd;
    }



}
