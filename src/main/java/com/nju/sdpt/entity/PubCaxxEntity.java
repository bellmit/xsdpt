package com.nju.sdpt.entity;

public class PubCaxxEntity {
    private Integer bh;

    private Integer yysdbh;

    private Integer ajxh;

    private Integer dsrbh;

    public PubCaxxEntity(Integer bh, Integer yysdbh, Integer ajxh, Integer dsrbh) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.ajxh = ajxh;
        this.dsrbh = dsrbh;
    }

    public PubCaxxEntity() {
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

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }
}