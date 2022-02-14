package com.nju.sdpt.entity;

import java.util.Date;

public class PubDxmbInfoEntity {
    private Integer bh;

    private String mbmc;

    private String mbhsy;

    private String fybh;

    private Integer mbzt;

    private Date cjsj;

    private String mbnr;

    public PubDxmbInfoEntity(Integer bh, String mbmc, String mbhsy, String fybh, Integer mbzt, Date cjsj, String mbnr) {
        this.bh = bh;
        this.mbmc = mbmc;
        this.mbhsy = mbhsy;
        this.fybh = fybh;
        this.mbzt = mbzt;
        this.cjsj = cjsj;
        this.mbnr = mbnr;
    }

    public PubDxmbInfoEntity() {
        super();
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public String getMbmc() {
        return mbmc;
    }

    public void setMbmc(String mbmc) {
        this.mbmc = mbmc == null ? null : mbmc.trim();
    }

    public String getMbhsy() {
        return mbhsy;
    }

    public void setMbhsy(String mbhsy) {
        this.mbhsy = mbhsy == null ? null : mbhsy.trim();
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public Integer getMbzt() {
        return mbzt;
    }

    public void setMbzt(Integer mbzt) {
        this.mbzt = mbzt;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getMbnr() {
        return mbnr;
    }

    public void setMbnr(String mbnr) {
        this.mbnr = mbnr == null ? null : mbnr.trim();
    }
}