package com.nju.sdpt.entity;

public class PubYysdSdwsEntity {
    private Integer bh;

    private Integer yysdbh;

    private Integer wsbh;

    private String sdly;

    private Integer sdbh;

    public PubYysdSdwsEntity(Integer bh, Integer yysdbh, Integer wsbh, String sdly, Integer sdbh) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.wsbh = wsbh;
        this.sdly = sdly;
        this.sdbh = sdbh;
    }

    public PubYysdSdwsEntity(Integer yysdbh, Integer wsbh, String sdly, Integer sdbh) {
        this.yysdbh = yysdbh;
        this.wsbh = wsbh;
        this.sdly = sdly;
        this.sdbh = sdbh;
    }

    public PubYysdSdwsEntity() {
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

    public Integer getWsbh() {
        return wsbh;
    }

    public void setWsbh(Integer wsbh) {
        this.wsbh = wsbh;
    }

    public String getSdly() {
        return sdly;
    }

    public void setSdly(String sdly) {
        this.sdly = sdly == null ? null : sdly.trim();
    }

    public Integer getSdbh() {
        return sdbh;
    }

    public void setSdbh(Integer sdbh) {
        this.sdbh = sdbh;
    }
}