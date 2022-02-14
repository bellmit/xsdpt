package com.nju.sdpt.entity;

import java.sql.Timestamp;

/**
 * @author jiaweiq
 * @date 2021/7/30 11:05
 */
public class lzEntity {

    private int fybh;
    private String targetname;
    private Timestamp createtime;
    private int yysdbh;
    private String yhdm;

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public lzEntity() {
    }

    public int getFybh() {
        return fybh;
    }

    public void setFybh(int fybh) {
        this.fybh = fybh;
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(int yysdbh) {
        this.yysdbh = yysdbh;
    }
}
