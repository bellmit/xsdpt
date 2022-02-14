package com.nju.sdpt.entity;

import java.util.Date;

public class PubZgysdLogEntity {
    private Integer bh;

    private Integer yysdbh;

    private Date scsj;

    public PubZgysdLogEntity(Integer bh, Integer yysdbh, Date scsj) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.scsj = scsj;
    }

    public PubZgysdLogEntity() {
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

    public Date getScsj() {
        return scsj;
    }

    public void setScsj(Date scsj) {
        this.scsj = scsj;
    }
}