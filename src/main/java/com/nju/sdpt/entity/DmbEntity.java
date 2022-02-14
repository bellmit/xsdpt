package com.nju.sdpt.entity;

import java.util.Date;

/**
 * Created by XXT on 2019/5/7.
 */


public class DmbEntity implements java.io.Serializable {
    // Fields

    private String lbbh;
    private String dmbh;

    private String dmms;
    private String xgdm;
    private String bz;
    private String modflag;
    private String transflag;
    private Date xgsj;
    private Double xssx;
    private String dqbs;
    private Integer fybh;
    private String fysx;
    private String fylbdm;
    private Integer xzqdmbh;
    private Integer dmbbh;
    private String spbm;
    private String bmzt;
    private String xgdm2;

    // Constructors

    /** default constructor */
    public DmbEntity() {
    }

    /** minimal constructor */
    public DmbEntity(String lbbh, String dmbh) {
        this.lbbh = lbbh;
        this.dmbh = dmbh;
    }

    /** normal constructor1 */
    public DmbEntity(String lbbh, String dmbh, String dmms, String xgdm,
                     String bz, Integer fybh) {
        this.lbbh = lbbh;
        this.dmbh = dmbh;
        this.dmms = dmms;
        this.xgdm = xgdm;
        this.bz = bz;
        this.fybh = fybh;

    }

    /** full constructor */
    public DmbEntity(String lbbh, String dmbh, String dmms, String xgdm, String bz,
                     String modflag, String transflag, Date xgsj, Double xssx,
                     String dqbs, Integer fybh, String fysx, String fylbdm,
                     Integer xzqdmbh, Integer dmbbh, String spbm, String bmzt, String xgdm2) {
        this.lbbh = lbbh;
        this.dmbh = dmbh;
        this.dmms = dmms;
        this.xgdm = xgdm;
        this.bz = bz;
        this.modflag = modflag;
        this.transflag = transflag;
        this.xgsj = xgsj;
        this.xssx = xssx;
        this.dqbs = dqbs;
        this.fybh = fybh;
        this.fysx = fysx;
        this.fylbdm = fylbdm;
        this.xzqdmbh = xzqdmbh;
        this.dmbbh = dmbbh;
        this.spbm = spbm;
        this.bmzt = bmzt;
        this.xgdm2=xgdm2;
    }

    public String getLbbh() {
        return lbbh;
    }

    public void setLbbh(String lbbh) {
        this.lbbh = lbbh;
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

    public String getXgdm() {
        return xgdm;
    }

    public void setXgdm(String xgdm) {
        this.xgdm = xgdm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getModflag() {
        return modflag;
    }

    public void setModflag(String modflag) {
        this.modflag = modflag;
    }

    public String getTransflag() {
        return transflag;
    }

    public void setTransflag(String transflag) {
        this.transflag = transflag;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public Double getXssx() {
        return xssx;
    }

    public void setXssx(Double xssx) {
        this.xssx = xssx;
    }

    public String getDqbs() {
        return dqbs;
    }

    public void setDqbs(String dqbs) {
        this.dqbs = dqbs;
    }

    public Integer getFybh() {
        return fybh;
    }

    public void setFybh(Integer fybh) {
        this.fybh = fybh;
    }

    public String getFysx() {
        return fysx;
    }

    public void setFysx(String fysx) {
        this.fysx = fysx;
    }

    public String getFylbdm() {
        return fylbdm;
    }

    public void setFylbdm(String fylbdm) {
        this.fylbdm = fylbdm;
    }

    public Integer getXzqdmbh() {
        return xzqdmbh;
    }

    public void setXzqdmbh(Integer xzqdmbh) {
        this.xzqdmbh = xzqdmbh;
    }

    public Integer getDmbbh() {
        return dmbbh;
    }

    public void setDmbbh(Integer dmbbh) {
        this.dmbbh = dmbbh;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getBmzt() {
        return bmzt;
    }

    public void setBmzt(String bmzt) {
        this.bmzt = bmzt;
    }

    public String getXgdm2() {
        return xgdm2;
    }

    public void setXgdm2(String xgdm2) {
        this.xgdm2 = xgdm2;
    }
}
