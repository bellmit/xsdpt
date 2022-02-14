package com.nju.sdpt.entity;

import java.util.Date;

public class RpoEmsInfoEntity {
    private Integer bh;

    private Integer yysdbh;

    private Integer dsrbh;

    private Date scrq;

    private Integer kdid;

    private Integer sdybh;

    private String sdjg;

    private Date submittime;

    private String sddz;

    private Integer wsnum;
    private String kddh;

    public RpoEmsInfoEntity(Integer bh, Integer yysdbh, Integer dsrbh, Date scrq, Integer kdid, Integer sdybh, String sdjg, Date submittime) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.dsrbh = dsrbh;
        this.scrq = scrq;
        this.kdid = kdid;
        this.sdybh = sdybh;
        this.sdjg = sdjg;
        this.submittime = submittime;
    }

    public RpoEmsInfoEntity(Integer bh, Integer yysdbh, Integer dsrbh, Date scrq, Integer kdid, Integer sdybh, String sdjg, Date submittime, String sddz, Integer wsnum) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.dsrbh = dsrbh;
        this.scrq = scrq;
        this.kdid = kdid;
        this.sdybh = sdybh;
        this.sdjg = sdjg;
        this.submittime = submittime;
        this.sddz = sddz;
        this.wsnum = wsnum;
    }

    public String getSddz() {
        return sddz;
    }

    public void setSddz(String sddz) {
        this.sddz = sddz;
    }

    public RpoEmsInfoEntity(Integer bh, Integer yysdbh, Integer dsrbh, Date scrq, Integer kdid, Integer sdybh, String sdjg, Date submittime, String sddz) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.dsrbh = dsrbh;
        this.scrq = scrq;
        this.kdid = kdid;
        this.sdybh = sdybh;
        this.sdjg = sdjg;
        this.submittime = submittime;
        this.sddz = sddz;
    }

    public RpoEmsInfoEntity(Integer bh, Integer yysdbh, Integer dsrbh, Date scrq, Integer kdid, Integer sdybh, String sdjg, Date submittime, String sddz, Integer wsnum, String kddh) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.dsrbh = dsrbh;
        this.scrq = scrq;
        this.kdid = kdid;
        this.sdybh = sdybh;
        this.sdjg = sdjg;
        this.submittime = submittime;
        this.sddz = sddz;
        this.wsnum = wsnum;
        this.kddh = kddh;
    }

    public RpoEmsInfoEntity() {
        super();
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }

    public Date getScrq() {
        return scrq;
    }

    public void setScrq(Date scrq) {
        this.scrq = scrq;
    }

    public Integer getKdid() {
        return kdid;
    }

    public void setKdid(Integer kdid) {
        this.kdid = kdid;
    }

    public Integer getSdybh() {
        return sdybh;
    }

    public void setSdybh(Integer sdybh) {
        this.sdybh = sdybh;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg == null ? null : sdjg.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public Integer getWsnum() {
        return wsnum;
    }

    public void setWsnum(Integer wsnum) {
        this.wsnum = wsnum;
    }

    public String getKddh() {
        return kddh;
    }

    public void setKddh(String kddh) {
        this.kddh = kddh;
    }
}