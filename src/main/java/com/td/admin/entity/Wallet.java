package com.td.admin.entity;

import java.io.Serializable;
import java.sql.Timestamp;


public class Wallet implements Serializable {
    private Long id;//自增id
    private String tPwd;//交易密码
    private String mnem1;//助记词
    private String mnem2;//助记词
    private String pKey;//私钥
    private String addr;//钱包地址
    private String pPwd;//app密码
    private String name;//昵称
    private String tel;//手机号
    private String code;//邀请码
    private Timestamp time;//注册时间
    private Integer level;

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettPwd() {
        return tPwd;
    }

    public void settPwd(String tPwd) {
        this.tPwd = tPwd;
    }

    public String getMnem1() {
        return mnem1;
    }

    public void setMnem1(String mnem1) {
        this.mnem1 = mnem1;
    }

    public String getMnem2() {
        return mnem2;
    }

    public void setMnem2(String mnem2) {
        this.mnem2 = mnem2;
    }

    public String getpKey() {
        return pKey;
    }

    public void setpKey(String pKey) {
        this.pKey = pKey;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getpPwd() {
        return pPwd;
    }

    public void setpPwd(String pPwd) {
        this.pPwd = pPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", tPwd='" + tPwd + '\'' +
                ", mnem1='" + mnem1 + '\'' +
                ", mnem2='" + mnem2 + '\'' +
                ", pKey='" + pKey + '\'' +
                ", addr='" + addr + '\'' +
                ", pPwd='" + pPwd + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", code='" + code + '\'' +
                ", time=" + time +
                ", level=" + level +
                '}';
    }
}
