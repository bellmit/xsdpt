package com.nju.sdpt.entity;

public class PubYysdWsEntityKey {
    private Integer yysdbh;

    private Integer bh;

    public PubYysdWsEntityKey(Integer yysdbh, Integer bh) {
        this.yysdbh = yysdbh;
        this.bh = bh;
    }

    public PubYysdWsEntityKey() {
        super();
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }
}