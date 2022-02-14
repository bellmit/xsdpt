package com.nju.sdpt.entity;

public class PubYysdSsdrEntity extends PubYysdSsdrEntityKey {
    private String ssdrmc;

    private String ssdrssdw;

    private String sfzhm;

    private String repairbatchno;

    private String repairstatus;

    private String daisr;

    private Integer sdjg;

    private Integer sfqssddzqrs;

    private Integer sftydzsd;

    private Integer wsnum;

    public PubYysdSsdrEntity(Integer ssdrbh, Integer yysdbh, String ssdrmc, String ssdrssdw, String sfzhm, String repairbatchno, String repairstatus, String daisr, Integer sdjg, Integer sfqssddzqrs, Integer sftydzsd) {
        super(ssdrbh, yysdbh);
        this.ssdrmc = ssdrmc;
        this.ssdrssdw = ssdrssdw;
        this.sfzhm = sfzhm;
        this.repairbatchno = repairbatchno;
        this.repairstatus = repairstatus;
        this.daisr = daisr;
        this.sdjg = sdjg;
        this.sfqssddzqrs = sfqssddzqrs;
        this.sftydzsd = sftydzsd;
    }

    public PubYysdSsdrEntity(Integer ssdrbh, Integer yysdbh, String ssdrmc, String ssdrssdw, String sfzhm, String repairbatchno, String repairstatus, String daisr, Integer sdjg, Integer sfqssddzqrs, Integer sftydzsd, Integer wsnum) {
        super(ssdrbh, yysdbh);
        this.ssdrmc = ssdrmc;
        this.ssdrssdw = ssdrssdw;
        this.sfzhm = sfzhm;
        this.repairbatchno = repairbatchno;
        this.repairstatus = repairstatus;
        this.daisr = daisr;
        this.sdjg = sdjg;
        this.sfqssddzqrs = sfqssddzqrs;
        this.sftydzsd = sftydzsd;
        this.wsnum = wsnum;
    }

    public PubYysdSsdrEntity(String ssdrmc, String ssdrssdw, String sfzhm, String repairbatchno, String repairstatus, String daisr, Integer sdjg, Integer sfqssddzqrs, Integer sftydzsd, Integer wsnum) {
        this.ssdrmc = ssdrmc;
        this.ssdrssdw = ssdrssdw;
        this.sfzhm = sfzhm;
        this.repairbatchno = repairbatchno;
        this.repairstatus = repairstatus;
        this.daisr = daisr;
        this.sdjg = sdjg;
        this.sfqssddzqrs = sfqssddzqrs;
        this.sftydzsd = sftydzsd;
        this.wsnum = wsnum;
    }

    public PubYysdSsdrEntity() {
        super();
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc == null ? null : ssdrmc.trim();
    }

    public String getSsdrssdw() {
        return ssdrssdw;
    }

    public void setSsdrssdw(String ssdrssdw) {
        this.ssdrssdw = ssdrssdw == null ? null : ssdrssdw.trim();
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm == null ? null : sfzhm.trim();
    }

    public String getRepairbatchno() {
        return repairbatchno;
    }

    public void setRepairbatchno(String repairbatchno) {
        this.repairbatchno = repairbatchno == null ? null : repairbatchno.trim();
    }

    public String getRepairstatus() {
        return repairstatus;
    }

    public void setRepairstatus(String repairstatus) {
        this.repairstatus = repairstatus == null ? null : repairstatus.trim();
    }

    public String getDaisr() {
        return daisr;
    }

    public void setDaisr(String daisr) {
        this.daisr = daisr == null ? null : daisr.trim();
    }

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public Integer getSfqssddzqrs() {
        return sfqssddzqrs;
    }

    public void setSfqssddzqrs(Integer sfqssddzqrs) {
        this.sfqssddzqrs = sfqssddzqrs;
    }

    public Integer getSftydzsd() {
        return sftydzsd;
    }

    public void setSftydzsd(Integer sftydzsd) {
        this.sftydzsd = sftydzsd;
    }

    public Integer getWsnum() {
        return wsnum;
    }

    public void setWsnum(Integer wsnum) {
        this.wsnum = wsnum;
    }
}