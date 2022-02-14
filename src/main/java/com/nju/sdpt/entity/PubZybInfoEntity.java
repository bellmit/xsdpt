package com.nju.sdpt.entity;

public class PubZybInfoEntity {
    private Integer id;

    private Integer ywlx;

    private String ywid;

    private String zyurl;

    private String zymc;

    public PubZybInfoEntity(Integer id, Integer ywlx, String ywid, String zyurl, String zymc) {
        this.id = id;
        this.ywlx = ywlx;
        this.ywid = ywid;
        this.zyurl = zyurl;
        this.zymc = zymc;
    }

    public PubZybInfoEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYwlx() {
        return ywlx;
    }

    public void setYwlx(Integer ywlx) {
        this.ywlx = ywlx;
    }

    public String getYwid() {
        return ywid;
    }

    public void setYwid(String ywid) {
        this.ywid = ywid == null ? null : ywid.trim();
    }

    public String getZyurl() {
        return zyurl;
    }

    public void setZyurl(String zyurl) {
        this.zyurl = zyurl == null ? null : zyurl.trim();
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc == null ? null : zymc.trim();
    }
}