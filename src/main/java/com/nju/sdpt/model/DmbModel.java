package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/7.
 */

import com.nju.sdpt.entity.DmbEntity;
import com.nju.sdpt.util.StringUtil;

import java.util.Date;

/**
 * @author Admin
 *
 */
public class DmbModel {

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
    private String xgdm2;

    public DmbModel() {
        super();
    }

    public DmbModel(String dmbh,String dmms) {
        super();
        this.lbbh = "";
        this.dmbh = dmbh;
        this.dmms = dmms;
        this.bz = "";
    }

//	public DmbModel(String lbbh, String dmbh, String dmms, String xgdm, String bz) {
//		super();
//		this.lbbh = lbbh;
//		this.dmbh = dmbh;
//		this.dmms = dmms;
//		this.xgdm = xgdm;
//		this.bz = bz;
//	}

    public DmbModel(DmbEntity dmbEntity) {
        this.lbbh = StringUtil.trim(dmbEntity.getLbbh());
        this.dmbh = StringUtil.trim(dmbEntity.getDmbh());
        this.dmms = StringUtil.trim(dmbEntity.getDmms());
        this.xgdm = StringUtil.trim(dmbEntity.getXgdm());
        this.bz = StringUtil.trim(dmbEntity.getBz());
        this.modflag=StringUtil.trim(dmbEntity.getModflag());
        this.transflag=StringUtil.trim(dmbEntity.getTransflag());
        this.xgsj= dmbEntity.getXgsj();
        this.xssx= dmbEntity.getXssx();
        this.dqbs=StringUtil.trim(dmbEntity.getDqbs());
        this.fybh= dmbEntity.getFybh();
        this.fysx=StringUtil.trim(dmbEntity.getFysx());
        this.fylbdm=StringUtil.trim(dmbEntity.getFylbdm());
        this.xzqdmbh= dmbEntity.getXzqdmbh();
        this.dmbbh= dmbEntity.getDmbbh();
        this.xgdm2=StringUtil.trim(dmbEntity.getXgdm2());
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
        return this.xgdm;
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

    public double getXssx() {
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

    @Override
    public String toString() {
        return "DmbModel [lbbh=" + lbbh + ", dmbh=" + dmbh + ", dmms=" + dmms
                + ", xgdm=" + xgdm + ", bz=" + bz + ", modflag=" + modflag
                + ", transflag=" + transflag + ", xgsj=" + xgsj + ", xssx="
                + xssx + ", dqbs=" + dqbs + ", fybh=" + fybh + ", fysx=" + fysx
                + ", fylbdm=" + fylbdm + ", xzqdmbh=" + xzqdmbh + ", dmbbh="
                + dmbbh + "]";
    }

    public String getXgdm2() {
        return xgdm2;
    }

    public void setXgdm2(String xgdm2) {
        this.xgdm2 = xgdm2;
    }

    public DmbEntity update(DmbEntity src) {
        src.setLbbh(lbbh);
        src.setDmbh(dmbh);
        src.setDmms(dmms);
        src.setXssx(xssx);
        src.setXgdm(xgdm);
        src.setFybh(fybh);
        src.setXgdm2(xgdm2);
        return src;
    }



}
