package com.nju.sdpt.entity;

import java.util.Date;

public class PubRwwtEntity {
    private Integer rwwtid;

    private Integer yysdbh;

    private Date wtsj;

    private Integer ssdrbh;

    private String ssdrmc;

    private String wtrmc;

    private String clrmc;

    private String wtfs;

    private Date clsj;

    private String cljg;

    public PubRwwtEntity(Integer rwwtid, Integer yysdbh, Date wtsj, Integer ssdrbh, String ssdrmc, String wtrmc, String clrmc, String wtfs, Date clsj, String cljg) {
        this.rwwtid = rwwtid;
        this.yysdbh = yysdbh;
        this.wtsj = wtsj;
        this.ssdrbh = ssdrbh;
        this.ssdrmc = ssdrmc;
        this.wtrmc = wtrmc;
        this.clrmc = clrmc;
        this.wtfs = wtfs;
        this.clsj = clsj;
        this.cljg = cljg;
    }

    public PubRwwtEntity() {
        super();
    }

    public Integer getRwwtid() {
        return rwwtid;
    }

    public void setRwwtid(Integer rwwtid) {
        this.rwwtid = rwwtid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Date getWtsj() {
        return wtsj;
    }

    public void setWtsj(Date wtsj) {
        this.wtsj = wtsj;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc == null ? null : ssdrmc.trim();
    }

    public String getWtrmc() {
        return wtrmc;
    }

    public void setWtrmc(String wtrmc) {
        this.wtrmc = wtrmc == null ? null : wtrmc.trim();
    }

    public String getClrmc() {
        return clrmc;
    }

    public void setClrmc(String clrmc) {
        this.clrmc = clrmc == null ? null : clrmc.trim();
    }

    public String getWtfs() {
        return wtfs;
    }

    public void setWtfs(String wtfs) {
        this.wtfs = wtfs == null ? null : wtfs.trim();
    }

    public Date getClsj() {
        return clsj;
    }

    public void setClsj(Date clsj) {
        this.clsj = clsj;
    }

    public String getCljg() {
        return cljg;
    }

    public void setCljg(String cljg) {
        this.cljg = cljg == null ? null : cljg.trim();
    }
}