package com.nju.sdpt.entity;

import java.util.Date;

/**
 * @author jiaweiq
 * @date 2021/11/9 16:39
 */
public class ZgysdLogEntity {
    private Integer bh;

    private Integer yysdbh;

    private Date scsj;

    private int fybh;

    private int ajxh;

    public ZgysdLogEntity() {
    }

    public ZgysdLogEntity(Integer bh, Integer yysdbh, Date scsj, int fybh, int ajxh) {
        this.bh = bh;
        this.yysdbh = yysdbh;
        this.scsj = scsj;
        this.fybh = fybh;
        this.ajxh = ajxh;
    }

    public int getFybh() {
        return fybh;
    }

    public void setFybh(int fybh) {
        this.fybh = fybh;
    }

    public int getAjxh() {
        return ajxh;
    }

    public void setAjxh(int ajxh) {
        this.ajxh = ajxh;
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
