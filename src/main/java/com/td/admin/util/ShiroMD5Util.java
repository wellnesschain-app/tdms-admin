package com.td.admin.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;

/**
 * ShiroMD5自定义加密工具类
 */
public class ShiroMD5Util {
    /**
     * ShiroMD5加密方法，参数为Map，提升可重用性
     * 注意 ： 要加密的密码，存Map时的 Key 必须 为 'password'
     * @param map
     * @return 加盐加密后的密码
     */
    public static String SysMD5(Map<String,Object> map){
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials =map.get("password");//密码原值

        ByteSource salt = ByteSource.Util.bytes(crdentials);//以账号作为盐值

        int hashIterations = 3;//加密3次

        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        return hash.toString();
    }

    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("password","wncadminjjj");
        String s="174f22864b7d03d270efe32b94e24ce9";
        String newPwd=ShiroMD5Util.SysMD5(map);
        System.out.println(newPwd);
        System.out.println(s.equals(newPwd));
        /*String date="1543971884";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(Long.valueOf(date)*1000));*/
    }
}