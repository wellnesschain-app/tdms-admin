package com.td.admin.entity;

import java.sql.Timestamp;

public class Audit {
    private Integer id;
    private String addr;
    private Integer integral;
    private String state;
    private Timestamp time;
    private String reason;


    public Audit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", addr='" + addr + '\'' +
                ", integral=" + integral +
                ", state='" + state + '\'' +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                '}';
    }
}
