package com.td.admin.entity;

import java.sql.Timestamp;

public class Wealth {
    private Integer id;
    private String name;
    private Double rate;//利率
    private Integer day;//理财期限
    private String money;//起始金额
    private String crowMoney;//众筹目标金额
    private Integer crowDay;//众筹期限
    private String inMoney;//达到金额
    private String serviceMoney;//手续费
    private String explain;//赎回说明
    private Integer status;//状态

    private String plan_name;//计划名称
    private String min_money;//最小起投金额
    private String max_money;//最大起投金额
    private String interest_rate;//每日利率
    private String total_money;//达到金额
    private String instructions;//说明
    private Timestamp create_time;//创建时间

    public Wealth() {
    }


    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getMin_money() {
        return min_money;
    }

    public void setMin_money(String min_money) {
        this.min_money = min_money;
    }

    public String getMax_money() {
        return max_money;
    }

    public void setMax_money(String max_money) {
        this.max_money = max_money;
    }

    public String getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCrowMoney() {
        return crowMoney;
    }

    public void setCrowMoney(String crowMoney) {
        this.crowMoney = crowMoney;
    }

    public Integer getCrowDay() {
        return crowDay;
    }

    public void setCrowDay(Integer crowDay) {
        this.crowDay = crowDay;
    }

    public String getInMoney() {
        return inMoney;
    }

    public void setInMoney(String inMoney) {
        this.inMoney = inMoney;
    }

    public String getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Wealth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", day=" + day +
                ", money='" + money + '\'' +
                ", crowMoney='" + crowMoney + '\'' +
                ", crowDay=" + crowDay +
                ", inMoney='" + inMoney + '\'' +
                ", serviceMoney='" + serviceMoney + '\'' +
                ", explain='" + explain + '\'' +
                ", status=" + status +
                ", plan_name='" + plan_name + '\'' +
                ", min_money='" + min_money + '\'' +
                ", max_money='" + max_money + '\'' +
                ", interest_rate='" + interest_rate + '\'' +
                ", total_money='" + total_money + '\'' +
                ", instructions='" + instructions + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
