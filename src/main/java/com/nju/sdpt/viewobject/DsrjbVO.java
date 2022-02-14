package com.nju.sdpt.viewobject;

import java.util.Date;

/**
 * Created by XXT on 2019/5/24.
 */
public class DsrjbVO {

    // Fields

    private Integer ajxh;
    private Integer dsrbh;
    private String dsrssdw;
    private String dsrlb;
    private String dsrjc;
    private String sfssdbr;
    private String dsrbgfs;
    private String dsrbgyy;
    private Date dsrbgsj;
    private String sfsa;
    private String sfsg;
    private String sfsq;
    private String sfst;
    private String sfsw;
    private Double qqpcje;
    private String scbc;
    private Double hpje;
    private String sfqxsp;
    private String dsrqxyy;

    private String ssdr;//受送达人
    private String sddz;//送达地址

    private String xwnlqk;
    private String zxakzh;

    private String dh;//电话
    private String sfzhm;//身份证号码

    private String daisr;//代收人

    private Integer wsnum;


    // Constructors

    /** default constructor */
    public DsrjbVO() {
    }

    /** minimal constructor */
    public DsrjbVO(Integer ajxh,
                   Integer dsrbh) {
        this.ajxh = ajxh;
        this.dsrbh=dsrbh;
    }

    /** full constructor */
    public DsrjbVO(Integer ajxh,
                   Integer dsrbh, String dsrssdw, String dsrlb, String dsrjc,
                   String sfssdbr, String dsrbgfs, String dsrbgyy, Date dsrbgsj,
                   String sfsa, String sfsg, String sfsq, String sfst, String sfsw,
                   double qqpcje, String scbc, double hpje, String sfqxsp, String dsrqxyy) {
        this.ajxh = ajxh;
        this.dsrbh=dsrbh;
        this.dsrssdw = dsrssdw;
        this.dsrlb = dsrlb;
        this.dsrjc = dsrjc;
        this.sfssdbr = sfssdbr;
        this.dsrbgfs = dsrbgfs;
        this.dsrbgyy = dsrbgyy;
        this.dsrbgsj = dsrbgsj;
        this.sfsa = sfsa;
        this.sfsg = sfsg;
        this.sfsq = sfsq;
        this.sfst = sfst;
        this.sfsw = sfsw;
        this.qqpcje = qqpcje;
        this.scbc = scbc;
        this.hpje = hpje;
        this.sfqxsp = sfqxsp;
        this.dsrqxyy = dsrqxyy;
    }

    public DsrjbVO(Integer ajxh, Integer dsrbh, String dsrssdw, String dsrlb, String dsrjc, String sfssdbr, String dsrbgfs, String dsrbgyy, Date dsrbgsj, String sfsa, String sfsg, String sfsq, String sfst, String sfsw, Double qqpcje, String scbc, Double hpje, String sfqxsp, String dsrqxyy, String ssdr, String sddz, String xwnlqk, String zxakzh, String dh, String sfzhm, String daisr) {
        this.ajxh = ajxh;
        this.dsrbh = dsrbh;
        this.dsrssdw = dsrssdw;
        this.dsrlb = dsrlb;
        this.dsrjc = dsrjc;
        this.sfssdbr = sfssdbr;
        this.dsrbgfs = dsrbgfs;
        this.dsrbgyy = dsrbgyy;
        this.dsrbgsj = dsrbgsj;
        this.sfsa = sfsa;
        this.sfsg = sfsg;
        this.sfsq = sfsq;
        this.sfst = sfst;
        this.sfsw = sfsw;
        this.qqpcje = qqpcje;
        this.scbc = scbc;
        this.hpje = hpje;
        this.sfqxsp = sfqxsp;
        this.dsrqxyy = dsrqxyy;
        this.ssdr = ssdr;
        this.sddz = sddz;
        this.xwnlqk = xwnlqk;
        this.zxakzh = zxakzh;
        this.dh = dh;
        this.sfzhm = sfzhm;
        this.daisr = daisr;
    }

    public DsrjbVO(Integer ajxh, Integer dsrbh, String dsrssdw, String dsrlb, String dsrjc, String sfssdbr, String dsrbgfs, String dsrbgyy, Date dsrbgsj, String sfsa, String sfsg, String sfsq, String sfst, String sfsw, Double qqpcje, String scbc, Double hpje, String sfqxsp, String dsrqxyy, String ssdr, String sddz, String xwnlqk, String zxakzh, String dh, String sfzhm, String daisr, Integer wsnum) {
        this.ajxh = ajxh;
        this.dsrbh = dsrbh;
        this.dsrssdw = dsrssdw;
        this.dsrlb = dsrlb;
        this.dsrjc = dsrjc;
        this.sfssdbr = sfssdbr;
        this.dsrbgfs = dsrbgfs;
        this.dsrbgyy = dsrbgyy;
        this.dsrbgsj = dsrbgsj;
        this.sfsa = sfsa;
        this.sfsg = sfsg;
        this.sfsq = sfsq;
        this.sfst = sfst;
        this.sfsw = sfsw;
        this.qqpcje = qqpcje;
        this.scbc = scbc;
        this.hpje = hpje;
        this.sfqxsp = sfqxsp;
        this.dsrqxyy = dsrqxyy;
        this.ssdr = ssdr;
        this.sddz = sddz;
        this.xwnlqk = xwnlqk;
        this.zxakzh = zxakzh;
        this.dh = dh;
        this.sfzhm = sfzhm;
        this.daisr = daisr;
        this.wsnum = wsnum;
    }

    public Integer getWsnum() {
        return wsnum;
    }

    public void setWsnum(Integer wsnum) {
        this.wsnum = wsnum;
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

    public String getDsrssdw() {
        return dsrssdw;
    }

    public void setDsrssdw(String dsrssdw) {
        this.dsrssdw = dsrssdw;
    }

    public String getDsrlb() {
        return dsrlb;
    }

    public void setDsrlb(String dsrlb) {
        this.dsrlb = dsrlb;
    }

    public String getDsrjc() {
        return dsrjc;
    }

    public void setDsrjc(String dsrjc) {
        this.dsrjc = dsrjc;
    }

    public String getSfssdbr() {
        return sfssdbr;
    }

    public void setSfssdbr(String sfssdbr) {
        this.sfssdbr = sfssdbr;
    }

    public String getDsrbgfs() {
        return dsrbgfs;
    }

    public void setDsrbgfs(String dsrbgfs) {
        this.dsrbgfs = dsrbgfs;
    }

    public String getDsrbgyy() {
        return dsrbgyy;
    }

    public void setDsrbgyy(String dsrbgyy) {
        this.dsrbgyy = dsrbgyy;
    }

    public Date getDsrbgsj() {
        return dsrbgsj;
    }

    public void setDsrbgsj(Date dsrbgsj) {
        this.dsrbgsj = dsrbgsj;
    }

    public String getSfsa() {
        return sfsa;
    }

    public void setSfsa(String sfsa) {
        this.sfsa = sfsa;
    }

    public String getSfsg() {
        return sfsg;
    }

    public void setSfsg(String sfsg) {
        this.sfsg = sfsg;
    }

    public String getSfsq() {
        return sfsq;
    }

    public void setSfsq(String sfsq) {
        this.sfsq = sfsq;
    }

    public String getSfst() {
        return sfst;
    }

    public void setSfst(String sfst) {
        this.sfst = sfst;
    }

    public String getSfsw() {
        return sfsw;
    }

    public void setSfsw(String sfsw) {
        this.sfsw = sfsw;
    }

    public Double getQqpcje() {
        return qqpcje;
    }

    public void setQqpcje(Double qqpcje) {
        this.qqpcje = qqpcje;
    }

    public String getScbc() {
        return scbc;
    }

    public void setScbc(String scbc) {
        this.scbc = scbc;
    }

    public Double getHpje() {
        return hpje;
    }

    public void setHpje(Double hpje) {
        this.hpje = hpje;
    }

    public String getSfqxsp() {
        return sfqxsp;
    }

    public void setSfqxsp(String sfqxsp) {
        this.sfqxsp = sfqxsp;
    }

    public String getDsrqxyy() {
        return dsrqxyy;
    }

    public void setDsrqxyy(String dsrqxyy) {
        this.dsrqxyy = dsrqxyy;
    }

    public String getSsdr() {
        return ssdr;
    }

    public void setSsdr(String ssdr) {
        this.ssdr = ssdr;
    }

    public String getSddz() {
        return sddz;
    }

    public void setSddz(String sddz) {
        this.sddz = sddz;
    }

    public String getXwnlqk() {
        return xwnlqk;
    }

    public void setXwnlqk(String xwnlqk) {
        this.xwnlqk = xwnlqk;
    }

    public String getZxakzh() {
        return zxakzh;
    }

    public void setZxakzh(String zxakzh) {
        this.zxakzh = zxakzh;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getDaisr() {
        return daisr;
    }

    public void setDaisr(String daisr) {
        this.daisr = daisr;
    }
}
