package com.td.admin.entity;

import org.springframework.stereotype.Component;

/**
 * 实名认证
 */
@Component
public class Authen {
    private Long id;
    private String addr;//钱包编号
    private String uid;//身份证
    private String name;//真实姓名
    private String mid;//银行卡卡号
    private String sex;//性别
    private String homeAddr;//住址

    public Authen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    @Override
    public String toString() {
        return "Authen{" +
                "id=" + id +
                ", addr='" + addr + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", mid='" + mid + '\'' +
                ", sex='" + sex + '\'' +
                ", homeAddr='" + homeAddr + '\'' +
                '}';
    }
}
