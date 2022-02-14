package com.nju.sdpt.viewobject;

import java.util.Date;
import java.util.List;

public class CaDsrjbVO extends DsrjbVO {
    String szah;//案号拼接之后的案号
    Integer[] ajxhs;

    public CaDsrjbVO(DsrjbVO dsrjbVO) {
        super.setAjxh(dsrjbVO.getAjxh());
        super.setDsrbh(dsrjbVO.getDsrbh());
        super.setDsrssdw(dsrjbVO.getDsrssdw());
        super.setDsrlb(dsrjbVO.getDsrlb());
        super.setDsrjc(dsrjbVO.getDsrjc());
        super.setSfssdbr(dsrjbVO.getSfssdbr());
        super.setDsrbgfs(dsrjbVO.getDsrbgfs());
        super.setDsrbgyy(dsrjbVO.getDsrbgyy());
        super.setAjxh(dsrjbVO.getAjxh());
        super.setDsrbh(dsrjbVO.getDsrbh());
        super.setDsrssdw(dsrjbVO.getDsrssdw());
        super.setDsrlb(dsrjbVO.getDsrlb());
        super.setDsrjc(dsrjbVO.getDsrjc());
        super.setSfssdbr(dsrjbVO.getSfssdbr());
        super.setDsrbgfs(dsrjbVO.getDsrbgfs());
        super.setDsrbgyy(dsrjbVO.getDsrbgyy());
        super.setDsrbgsj(dsrjbVO.getDsrbgsj());
        super.setSfsa(dsrjbVO.getSfsa());
        super.setSfsq(dsrjbVO.getSfsq());
        super.setSfst(dsrjbVO.getSfst());
        super.setSfsg(dsrjbVO.getSfsg());
        super.setSfsw(dsrjbVO.getSfsw());
        super.setQqpcje(dsrjbVO.getQqpcje());
        super.setScbc(dsrjbVO.getScbc());
        super.setHpje(dsrjbVO.getHpje());
        super.setSfqxsp(dsrjbVO.getSfqxsp());
        super.setDsrqxyy(dsrjbVO.getDsrqxyy());
        super.setSsdr(dsrjbVO.getSsdr());
        super.setSddz(dsrjbVO.getSddz());
        super.setXwnlqk(dsrjbVO.getXwnlqk());
        super.setZxakzh(dsrjbVO.getZxakzh());
        super.setDh(dsrjbVO.getDh());
        super.setSfzhm(dsrjbVO.getSfzhm());
        super.setDaisr(dsrjbVO.getDaisr());
        if(null != dsrjbVO.getWsnum()) {
            super.setWsnum(dsrjbVO.getWsnum());
        }
    }

    public String getSzah() {
        return szah;
    }

    public void setSzah(String szah) {
        this.szah = szah;
    }

    public Integer[] getAjxhs() {
        return ajxhs;
    }

    public void setAjxhs(Integer[] ajxhs) {
        this.ajxhs = ajxhs;
    }

    public CaDsrjbVO(){

    }
    public CaDsrjbVO(String szah, Integer[] ajxhs) {
        this.szah = szah;
        this.ajxhs = ajxhs;
    }

    public CaDsrjbVO(Integer ajxh, Integer dsrbh, String szah, Integer[] ajxhs) {
        super(ajxh, dsrbh);
        this.szah = szah;
        this.ajxhs = ajxhs;
    }

    public CaDsrjbVO(Integer ajxh, Integer dsrbh, String dsrssdw, String dsrlb, String dsrjc, String sfssdbr, String dsrbgfs, String dsrbgyy, Date dsrbgsj, String sfsa, String sfsg, String sfsq, String sfst, String sfsw, double qqpcje, String scbc, double hpje, String sfqxsp, String dsrqxyy, String szah, Integer[] ajxhs) {
        super(ajxh, dsrbh, dsrssdw, dsrlb, dsrjc, sfssdbr, dsrbgfs, dsrbgyy, dsrbgsj, sfsa, sfsg, sfsq, sfst, sfsw, qqpcje, scbc, hpje, sfqxsp, dsrqxyy);
        this.szah = szah;
        this.ajxhs = ajxhs;
    }

    public CaDsrjbVO(Integer ajxh, Integer dsrbh, String dsrssdw, String dsrlb, String dsrjc, String sfssdbr, String dsrbgfs, String dsrbgyy, Date dsrbgsj, String sfsa, String sfsg, String sfsq, String sfst, String sfsw, Double qqpcje, String scbc, Double hpje, String sfqxsp, String dsrqxyy, String ssdr, String sddz, String xwnlqk, String zxakzh, String dh, String sfzhm, String daisr, String szah, Integer[] ajxhs) {
        super(ajxh, dsrbh, dsrssdw, dsrlb, dsrjc, sfssdbr, dsrbgfs, dsrbgyy, dsrbgsj, sfsa, sfsg, sfsq, sfst, sfsw, qqpcje, scbc, hpje, sfqxsp, dsrqxyy, ssdr, sddz, xwnlqk, zxakzh, dh, sfzhm, daisr);
        this.szah = szah;
        this.ajxhs = ajxhs;
    }
}
