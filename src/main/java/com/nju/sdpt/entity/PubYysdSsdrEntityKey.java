package com.nju.sdpt.entity;

public class PubYysdSsdrEntityKey {
    private Integer ssdrbh;

    private Integer yysdbh;

    public PubYysdSsdrEntityKey(Integer ssdrbh, Integer yysdbh) {
        this.ssdrbh = ssdrbh;
        this.yysdbh = yysdbh;
    }

    public PubYysdSsdrEntityKey() {
        super();
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }
}