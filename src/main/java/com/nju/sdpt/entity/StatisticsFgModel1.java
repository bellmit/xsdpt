package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2022/1/20 10:15
 */
public class StatisticsFgModel1 {
    private String bmmc;
    private Integer wcsdsl;
    private Integer sdcgsl;
    private Integer wcsdrs;
    private Integer sdcgrs;
    private Double dzsdcgl;
    private Integer fsnum;
    private Integer cgnum;
    private Integer tsgdds;
    private Integer wcgdds;
    private Integer zjsdcs;
    private Integer wczjsdcs;

    public StatisticsFgModel1() {
        this.wcsdsl=0;
        this.sdcgsl=0;
        this.wcsdrs=0;
        this.sdcgrs=0;
        this.dzsdcgl=0.0;
        this.fsnum=0;
        this.cgnum=0;
        this.tsgdds=0;
        this.wcgdds=0;
        this.zjsdcs=0;
        this.wczjsdcs=0;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public Integer getWcsdsl() {
        return wcsdsl;
    }

    public void setWcsdsl(Integer wcsdsl) {
        this.wcsdsl = wcsdsl;
    }

    public Integer getSdcgsl() {
        return sdcgsl;
    }

    public void setSdcgsl(Integer sdcgsl) {
        this.sdcgsl = sdcgsl;
    }

    public Integer getWcsdrs() {
        return wcsdrs;
    }

    public void setWcsdrs(Integer wcsdrs) {
        this.wcsdrs = wcsdrs;
    }

    public Integer getSdcgrs() {
        return sdcgrs;
    }

    public void setSdcgrs(Integer sdcgrs) {
        this.sdcgrs = sdcgrs;
    }

    public Double getDzsdcgl() {
        return dzsdcgl;
    }

    public void setDzsdcgl(Double dzsdcgl) {
        this.dzsdcgl = dzsdcgl;
    }

    public Integer getFsnum() {
        return fsnum;
    }

    public void setFsnum(Integer fsnum) {
        this.fsnum = fsnum;
    }

    public Integer getCgnum() {
        return cgnum;
    }

    public void setCgnum(Integer cgnum) {
        this.cgnum = cgnum;
    }

    public Integer getTsgdds() {
        return tsgdds;
    }

    public void setTsgdds(Integer tsgdds) {
        this.tsgdds = tsgdds;
    }

    public Integer getWcgdds() {
        return wcgdds;
    }

    public void setWcgdds(Integer wcgdds) {
        this.wcgdds = wcgdds;
    }

    public Integer getZjsdcs() {
        return zjsdcs;
    }

    public void setZjsdcs(Integer zjsdcs) {
        this.zjsdcs = zjsdcs;
    }

    public Integer getWczjsdcs() {
        return wczjsdcs;
    }

    public void setWczjsdcs(Integer wczjsdcs) {
        this.wczjsdcs = wczjsdcs;
    }
}
