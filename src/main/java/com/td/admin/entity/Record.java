package com.td.admin.entity;

import java.sql.Timestamp;

public class Record {
    private long id;
    private String addr;
    private long integral;
    private Timestamp time;



    public Record() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", addr='" + addr + '\'' +
                ", integral=" + integral +
                ", time=" + time +
                '}';
    }
}
