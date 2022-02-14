package com.nju.sdpt.entity;

import java.util.Date;

public class PubZjsdInfoEntity {
    private Integer zjsdbh;

    private Integer yysdbh;

    private Integer ssdrbh;

    private String zfzjbh;

    private String fybh;

    private String ajxh;

    private Date smsj;

    private String sddz;

    private String dwdz;

    private String sdgcjl;

    private String sdhz;

    private Date cjsj;

    private Integer cjr;

    private Integer qszt;

    private Integer zjsdjg;

    private String sfscwj;

    private String wjdz;

    private String wjid;

    private String wjmc;

    private String remark;

    private Date submittime;

    public PubZjsdInfoEntity(Integer zjsdbh, Integer yysdbh, Integer ssdrbh, String zfzjbh, String fybh, String ajxh, Date smsj, String sddz, String dwdz, String sdgcjl, String sdhz, Date cjsj, Integer cjr, Integer qszt, Integer zjsdjg, String sfscwj, String wjdz, String wjid, String wjmc, String remark, Date submittime) {
        this.zjsdbh = zjsdbh;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.zfzjbh = zfzjbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.smsj = smsj;
        this.sddz = sddz;
        this.dwdz = dwdz;
        this.sdgcjl = sdgcjl;
        this.sdhz = sdhz;
        this.cjsj = cjsj;
        this.cjr = cjr;
        this.qszt = qszt;
        this.zjsdjg = zjsdjg;
        this.sfscwj = sfscwj;
        this.wjdz = wjdz;
        this.wjid = wjid;
        this.wjmc = wjmc;
        this.remark = remark;
        this.submittime = submittime;
    }

    public PubZjsdInfoEntity() {
        super();
    }

    public Integer getZjsdbh() {
        return zjsdbh;
    }

    public void setZjsdbh(Integer zjsdbh) {
        this.zjsdbh = zjsdbh;
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

    public String getZfzjbh() {
        return zfzjbh;
    }

    public void setZfzjbh(String zfzjbh) {
        this.zfzjbh = zfzjbh == null ? null : zfzjbh.trim();
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public String getAjxh() {
        return ajxh;
    }

    public void setAjxh(String ajxh) {
        this.ajxh = ajxh == null ? null : ajxh.trim();
    }

    public Date getSmsj() {
        return smsj;
    }

    public void setSmsj(Date smsj) {
        this.smsj = smsj;
    }

    public String getSddz() {
        return sddz;
    }

    public void setSddz(String sddz) {
        this.sddz = sddz == null ? null : sddz.trim();
    }

    public String getDwdz() {
        return dwdz;
    }

    public void setDwdz(String dwdz) {
        this.dwdz = dwdz == null ? null : dwdz.trim();
    }

    public String getSdgcjl() {
        return sdgcjl;
    }

    public void setSdgcjl(String sdgcjl) {
        this.sdgcjl = sdgcjl == null ? null : sdgcjl.trim();
    }

    public String getSdhz() {
        return sdhz;
    }

    public void setSdhz(String sdhz) {
        this.sdhz = sdhz == null ? null : sdhz.trim();
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Integer getCjr() {
        return cjr;
    }

    public void setCjr(Integer cjr) {
        this.cjr = cjr;
    }

    public Integer getQszt() {
        return qszt;
    }

    public void setQszt(Integer qszt) {
        this.qszt = qszt;
    }

    public Integer getZjsdjg() {
        return zjsdjg;
    }

    public void setZjsdjg(Integer zjsdjg) {
        this.zjsdjg = zjsdjg;
    }

    public String getSfscwj() {
        return sfscwj;
    }

    public void setSfscwj(String sfscwj) {
        this.sfscwj = sfscwj == null ? null : sfscwj.trim();
    }

    public String getWjdz() {
        return wjdz;
    }

    public void setWjdz(String wjdz) {
        this.wjdz = wjdz == null ? null : wjdz.trim();
    }

    public String getWjid() {
        return wjid;
    }

    public void setWjid(String wjid) {
        this.wjid = wjid == null ? null : wjid.trim();
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc == null ? null : wjmc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }
}