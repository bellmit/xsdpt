package com.nju.sdpt.entity;

/**
 * Created by LaoP on 2019/5/10.
 */
public class AyDmbEntity {

    /**
     * 版本号
     */
    private String bbh;
    /**
     * 案由代码编号
     */
    private String dmbh;
    /**
     * 案由代码描述
     */
    private String dmms;

    // Constructors


    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public String getDmbh() {
        return dmbh;
    }

    public void setDmbh(String dmbh) {
        this.dmbh = dmbh;
    }

    public String getDmms() {
        return dmms;
    }

    public void setDmms(String dmms) {
        this.dmms = dmms;
    }
}
