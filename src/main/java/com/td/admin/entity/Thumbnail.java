package com.td.admin.entity;

/**
 * 缩略图类
 */
public class Thumbnail {
    private Integer id;
    private String src;//路径
    private String href;//链接
    private String describe;//描述
    private Integer status;

    public Thumbnail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", href='" + href + '\'' +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                '}';
    }
}
