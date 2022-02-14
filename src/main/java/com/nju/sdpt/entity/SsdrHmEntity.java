package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2022/1/10 16:15
 */
public class SsdrHmEntity {
    private Integer yysdbh;
    private Integer ssdrbh;
    private String ssdrmc;
    private String sfzhm;
    private String hm;

    public SsdrHmEntity() {
    }

    public SsdrHmEntity(Integer yysdbh, Integer ssdrbh, String ssdrmc, String sfzhm, String hm) {
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.ssdrmc = ssdrmc;
        this.sfzhm = sfzhm;
        this.hm = hm;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getHm() {
        return hm;
    }

    public void setHm(String hm) {
        this.hm = hm;
    }
}
