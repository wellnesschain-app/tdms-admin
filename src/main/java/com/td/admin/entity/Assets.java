package com.td.admin.entity;

import org.springframework.stereotype.Component;

/**
 * 财产
 */
@Component
public class Assets {
    private Long id;
    private Long wid;//钱包自增id
    private String coolAssets;//冻结资产
    private Double useAssets;//可用资产
    private Double core;//积分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getCoolAssets() {
        return coolAssets;
    }

    public void setCoolAssets(String coolAssets) {
        this.coolAssets = coolAssets;
    }

    public Double getUseAssets() {
        return useAssets;
    }

    public void setUseAssets(Double useAssets) {
        this.useAssets = useAssets;
    }

    public Double getCore() {
        return core;
    }

    public void setCore(Double core) {
        this.core = core;
    }

    public Assets() {
    }

    @Override
    public String toString() {
        return "Assets{" +
                "id=" + id +
                ", wid=" + wid +
                ", coolAssets='" + coolAssets + '\'' +
                ", useAssets=" + useAssets +
                ", core=" + core +
                '}';
    }
}
