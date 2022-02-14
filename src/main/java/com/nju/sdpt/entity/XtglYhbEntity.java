package com.nju.sdpt.entity;

import java.util.Date;

/**
 * Created by XXT on 2019/5/8.
 */
public class XtglYhbEntity implements java.io.Serializable {

    private Integer yhbh;
    private String yhdm;
    private String yhmc;
    private String yhkl;
    private String yhbm;
    private String klts;
    private String klda;
    private String yhsf;
    private Date klszsj;
    private Date qjkssj;
    private Date qjjssj;
    private String qjyy;
    private Integer grnzb;
    private Integer qtnzb;
    private Integer grbajs;
    private String phone;
    private String xfqx;
    private String ssfwzxqx;
    private byte[] dzqmImg;
    private String jbxxjCode;
    private Integer fazt;
    private Integer fasl;
    private Integer fybh;
    private Integer yhzt;
    private String tel;
    private String sfptdl;//是否普通登录
    private String sfjagly; //是否结案管理员

    // Constructors

    /** default constructor */
    public XtglYhbEntity() {
    }

    /** minimal constructor */
    public XtglYhbEntity(Integer yhbh) {
        this.yhbh = yhbh;
    }

    /** full constructor */
    public XtglYhbEntity(Integer yhbh, String yhdm, String yhmc, String yhkl,
                         String yhbm, String klts, String klda, String yhsf, Date klszsj,
                         Date qjkssj, Date qjjssj, String qjyy, Integer grnzb,
                         Integer qtnzb, Integer grbajs, String phone, String xfqx,
                         String ssfwzxqx, byte[] dzqmImg, String jbxxjCode, Integer fazt,
                         Integer fasl, Integer fybh, Integer yhzt
    ) {
        this.yhbh = yhbh;
        this.yhdm = yhdm;
        this.yhmc = yhmc;
        this.yhkl = yhkl;
        this.yhbm = yhbm;
        this.klts = klts;
        this.klda = klda;
        this.yhsf = yhsf;
        this.klszsj = klszsj;
        this.qjkssj = qjkssj;
        this.qjjssj = qjjssj;
        this.qjyy = qjyy;
        this.grnzb = grnzb;
        this.qtnzb = qtnzb;
        this.grbajs = grbajs;
        this.phone = phone;
        this.xfqx = xfqx;
        this.ssfwzxqx = ssfwzxqx;
        this.dzqmImg = dzqmImg;
        this.jbxxjCode = jbxxjCode;
        this.fazt = fazt;
        this.fasl = fasl;
        this.fybh = fybh;
        this.yhzt = yhzt;

    }

    public XtglYhbEntity(Integer yhbh, Integer fybh, String yhdm, String yhmc, String yhkl, String yhbm, String yhsf, Integer yhzt) {
        this.yhbh = yhbh;
        this.fybh = fybh;
        this.yhdm = yhdm;
        this.yhmc = yhmc;
        this.yhkl = yhkl;
        this.yhbm = yhbm;
        this.yhsf = yhsf;
        this.yhzt = yhzt;
    }

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getKlts() {
        return klts;
    }

    public void setKlts(String klts) {
        this.klts = klts;
    }

    public String getKlda() {
        return klda;
    }

    public void setKlda(String klda) {
        this.klda = klda;
    }

    public String getYhsf() {
        return yhsf;
    }

    public void setYhsf(String yhsf) {
        this.yhsf = yhsf;
    }

    public Date getKlszsj() {
        return klszsj;
    }

    public void setKlszsj(Date klszsj) {
        this.klszsj = klszsj;
    }

    public Date getQjkssj() {
        return qjkssj;
    }

    public void setQjkssj(Date qjkssj) {
        this.qjkssj = qjkssj;
    }

    public Date getQjjssj() {
        return qjjssj;
    }

    public void setQjjssj(Date qjjssj) {
        this.qjjssj = qjjssj;
    }

    public String getQjyy() {
        return qjyy;
    }

    public void setQjyy(String qjyy) {
        this.qjyy = qjyy;
    }

    public Integer getGrnzb() {
        return grnzb;
    }

    public void setGrnzb(Integer grnzb) {
        this.grnzb = grnzb;
    }

    public Integer getQtnzb() {
        return qtnzb;
    }

    public void setQtnzb(Integer qtnzb) {
        this.qtnzb = qtnzb;
    }

    public Integer getGrbajs() {
        return grbajs;
    }

    public void setGrbajs(Integer grbajs) {
        this.grbajs = grbajs;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getXfqx() {
        return xfqx;
    }

    public void setXfqx(String xfqx) {
        this.xfqx = xfqx;
    }

    public String getSsfwzxqx() {
        return ssfwzxqx;
    }

    public void setSsfwzxqx(String ssfwzxqx) {
        this.ssfwzxqx = ssfwzxqx;
    }

    public byte[] getDzqmImg() {
        return dzqmImg;
    }

    public void setDzqmImg(byte[] dzqmImg) {
        this.dzqmImg = dzqmImg;
    }

    public String getJbxxjCode() {
        return jbxxjCode;
    }

    public void setJbxxjCode(String jbxxjCode) {
        this.jbxxjCode = jbxxjCode;
    }

    public Integer getFazt() {
        return fazt;
    }

    public void setFazt(Integer fazt) {
        this.fazt = fazt;
    }

    public Integer getFasl() {
        return fasl;
    }

    public void setFasl(Integer fasl) {
        this.fasl = fasl;
    }

    public Integer getFybh() {
        return fybh;
    }

    public void setFybh(Integer fybh) {
        this.fybh = fybh;
    }

    public Integer getYhzt() {
        return yhzt;
    }

    public void setYhzt(Integer yhzt) {
        this.yhzt = yhzt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSfptdl() {
        return sfptdl;
    }

    public void setSfptdl(String sfptdl) {
        this.sfptdl = sfptdl;
    }

    public String getSfjagly() {
        return sfjagly;
    }

    public void setSfjagly(String sfjagly) {
        this.sfjagly = sfjagly;
    }
}
