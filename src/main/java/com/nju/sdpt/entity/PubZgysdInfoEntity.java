package com.nju.sdpt.entity;

import com.nju.sdpt.model.zgysd.Yjsd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PubZgysdInfoEntity {
    private Integer zgysdbh;

    private Integer logbh;

    private Integer ssdrbh;

    private String ssdrmc;

    private String uuid;

    private String lxdh;

    private String yjdz;

    private String jjr;

    private String wsmc;

    private Date yjsj;

    private String ydh;

    private String gdzt;

    private String ycyy;

    private String hzmc;

    private String hzurl;

    public PubZgysdInfoEntity(Yjsd yjsd) {
        this.ssdrmc = yjsd.getSsdr();
        this.uuid = yjsd.getUuid();
        this.lxdh = yjsd.getLxdh();
        this.yjdz = yjsd.getYjdz();
        this.jjr = yjsd.getJjr();
        this.wsmc = yjsd.getWsmc();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.yjsj = sm.parse(yjsd.getYjsj());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.ydh = yjsd.getYdh();
        this.gdzt = yjsd.getGdzt();
        this.ycyy = yjsd.getYcyy();
        this.hzmc = yjsd.getHzmc();
    }

    public PubZgysdInfoEntity(Integer zgysdbh, Integer logbh, Integer ssdrbh, String ssdrmc, String uuid, String lxdh, String yjdz, String jjr, String wsmc, Date yjsj, String ydh, String gdzt, String ycyy, String hzmc, String hzurl) {
        this.zgysdbh = zgysdbh;
        this.logbh = logbh;
        this.ssdrbh = ssdrbh;
        this.ssdrmc = ssdrmc;
        this.uuid = uuid;
        this.lxdh = lxdh;
        this.yjdz = yjdz;
        this.jjr = jjr;
        this.wsmc = wsmc;
        this.yjsj = yjsj;
        this.ydh = ydh;
        this.gdzt = gdzt;
        this.ycyy = ycyy;
        this.hzmc = hzmc;
        this.hzurl = hzurl;
    }

    public PubZgysdInfoEntity() {
        super();
    }

    public Integer getZgysdbh() {
        return zgysdbh;
    }

    public void setZgysdbh(Integer zgysdbh) {
        this.zgysdbh = zgysdbh;
    }

    public Integer getLogbh() {
        return logbh;
    }

    public void setLogbh(Integer logbh) {
        this.logbh = logbh;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    public String getYjdz() {
        return yjdz;
    }

    public void setYjdz(String yjdz) {
        this.yjdz = yjdz == null ? null : yjdz.trim();
    }

    public String getJjr() {
        return jjr;
    }

    public void setJjr(String jjr) {
        this.jjr = jjr == null ? null : jjr.trim();
    }

    public String getWsmc() {
        return wsmc;
    }

    public void setWsmc(String wsmc) {
        this.wsmc = wsmc == null ? null : wsmc.trim();
    }

    public Date getYjsj() {
        return yjsj;
    }

    public void setYjsj(Date yjsj) {
        this.yjsj = yjsj;
    }

    public String getYdh() {
        return ydh;
    }

    public void setYdh(String ydh) {
        this.ydh = ydh == null ? null : ydh.trim();
    }

    public String getGdzt() {
        return gdzt;
    }

    public void setGdzt(String gdzt) {
        this.gdzt = gdzt == null ? null : gdzt.trim();
    }

    public String getYcyy() {
        return ycyy;
    }

    public void setYcyy(String ycyy) {
        this.ycyy = ycyy == null ? null : ycyy.trim();
    }

    public String getHzmc() {
        return hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc == null ? null : hzmc.trim();
    }

    public String getHzurl() {
        return hzurl;
    }

    public void setHzurl(String hzurl) {
        this.hzurl = hzurl == null ? null : hzurl.trim();
    }
}