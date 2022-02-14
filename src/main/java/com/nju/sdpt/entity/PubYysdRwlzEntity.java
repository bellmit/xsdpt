package com.nju.sdpt.entity;

import java.util.Date;

public class PubYysdRwlzEntity {
    private Integer bh;

    private Integer yysdbh;

    private Date createtime;

    private Integer creater;

    private Integer target;

    private String bz;

    public PubYysdRwlzEntity(Integer bh, Integer yysdbh, Date createtime, Integer creater, Integer target, String bz) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.createtime = createtime;
        this.creater = creater;
        this.target = target;
        this.bz = bz;
    }

    public PubYysdRwlzEntity() {
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}