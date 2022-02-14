package com.nju.sdpt.entity;

public class PubYysdRyxxEntity {
    private Integer yhbh;

    private String yhdm;

    private String yhkl;

    private String yhmc;

    private String yhjs;

    private String seatnumber;

    private String entryshow;

    private String cuccshow;

    private String cmccshow;

    private String seatid;

    private Integer zhid;

    private String fybh;

    private String lxdh="";

    private Integer wwempid;

    public PubYysdRyxxEntity(Integer yhbh, String yhdm, String yhkl, String yhmc, String yhjs, String seatnumber, String entryshow, String cuccshow, String cmccshow, String seatid, Integer zhid, String fybh, String lxdh, Integer wwempid) {
        this.yhbh = yhbh;
        this.yhdm = yhdm;
        this.yhkl = yhkl;
        this.yhmc = yhmc;
        this.yhjs = yhjs;
        this.seatnumber = seatnumber;
        this.entryshow = entryshow;
        this.cuccshow = cuccshow;
        this.cmccshow = cmccshow;
        this.seatid = seatid;
        this.zhid = zhid;
        this.fybh = fybh;
        this.lxdh = lxdh;
        this.wwempid = wwempid;
    }

    public PubYysdRyxxEntity() {
        super();
    }

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm == null ? null : yhdm.trim();
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl == null ? null : yhkl.trim();
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc == null ? null : yhmc.trim();
    }

    public String getYhjs() {
        return yhjs;
    }

    public void setYhjs(String yhjs) {
        this.yhjs = yhjs == null ? null : yhjs.trim();
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber == null ? null : seatnumber.trim();
    }

    public String getEntryshow() {
        return entryshow;
    }

    public void setEntryshow(String entryshow) {
        this.entryshow = entryshow == null ? null : entryshow.trim();
    }

    public String getCuccshow() {
        return cuccshow;
    }

    public void setCuccshow(String cuccshow) {
        this.cuccshow = cuccshow == null ? null : cuccshow.trim();
    }

    public String getCmccshow() {
        return cmccshow;
    }

    public void setCmccshow(String cmccshow) {
        this.cmccshow = cmccshow == null ? null : cmccshow.trim();
    }

    public String getSeatid() {
        return seatid;
    }

    public void setSeatid(String seatid) {
        this.seatid = seatid == null ? null : seatid.trim();
    }

    public Integer getZhid() {
        return zhid;
    }

    public void setZhid(Integer zhid) {
        this.zhid = zhid;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    public Integer getWwempid() {
        return wwempid;
    }

    public void setWwempid(Integer wwempid) {
        this.wwempid = wwempid;
    }
}