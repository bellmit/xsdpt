package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubDjSdxxEntity;

import java.text.SimpleDateFormat;


/**
 * @author chikuo
 */
public class DzsdModel {

    /**
     * 送达日期
     */
    private String sdrq;
    /**
     * 送达内容
     */
    private String sdnr;
    /**
     * 签收人
     */
    private String qsr;
    /**
     * 签收时间
     */
    private String qsrq;
    /**
     * 送达回执
     */
    private String sdhz;

    /**
     * 被送达人
     */
    private String bsdr;

    public DzsdModel() {

    }


    public DzsdModel(PubDjSdxxEntity pubDjSdxxEntity) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        if (pubDjSdxxEntity.getSdrq()!=null){
        this.sdrq = sdf.format(pubDjSdxxEntity.getSdrq());
        }else {
            this.sdrq ="";
        }
        this.sdnr = pubDjSdxxEntity.getSdws();
        this.qsr = pubDjSdxxEntity.getQsr();
        if(pubDjSdxxEntity.getQssj()!=null){
        this.qsrq = sdf.format(pubDjSdxxEntity.getQssj());
        }else {
            this.qsrq="";
        }
        this.bsdr = pubDjSdxxEntity.getBsdr();
        this.sdhz = pubDjSdxxEntity.getCpath();

    }

    public DzsdModel(String sdrq, String sdnr, String qsr, String qsrq, String sdhz, String bsdr) {
        this.sdrq = sdrq;
        this.sdnr = sdnr;
        this.qsr = qsr;
        this.qsrq = qsrq;
        this.sdhz = sdhz;
        this.bsdr = bsdr;
    }

    public String getSdrq() {
        return sdrq;
    }

    public void setSdrq(String sdrq) {
        this.sdrq = sdrq;
    }

    public String getSdnr() {
        return sdnr;
    }

    public void setSdnr(String sdnr) {
        this.sdnr = sdnr;
    }

    public String getQsr() {
        return qsr;
    }

    public void setQsr(String qsr) {
        this.qsr = qsr;
    }

    public String getQsrq() {
        return qsrq;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public String getSdhz() {
        return sdhz;
    }

    public void setSdhz(String sdhz) {
        this.sdhz = sdhz;
    }

    public String getBsdr() {
        return bsdr;
    }

    public void setBsdr(String bsdr) {
        this.bsdr = bsdr;
    }
}
