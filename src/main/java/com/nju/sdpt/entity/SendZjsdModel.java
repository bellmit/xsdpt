package com.nju.sdpt.entity;

import sun.rmi.runtime.Log;

import java.util.Date;

/**
 * @author jiaweiq
 * @date 2021/11/3 15:02
 */
public class SendZjsdModel {
    private Long zjsdbh;
    private String fybh;
    private Long ajxh;
    private Long dsrid;
    private String ah;
    private String dsrmc;
    private String dsrdz;
    private Integer yhbh;
    private String yhdm;
    // 这里传递的是申请时间
    private Date jzrq;

    public Long getZjsdbh() {
        return zjsdbh;
    }

    public void setZjsdbh(Long zjsdbh) {
        this.zjsdbh = zjsdbh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public Long getAjxh() {
        return ajxh;
    }

    public void setAjxh(Long ajxh) {
        this.ajxh = ajxh;
    }

    public Long getDsrid() {
        return dsrid;
    }

    public void setDsrid(Long dsrid) {
        this.dsrid = dsrid;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getDsrmc() {
        return dsrmc;
    }

    public void setDsrmc(String dsrmc) {
        this.dsrmc = dsrmc;
    }

    public String getDsrdz() {
        return dsrdz;
    }

    public void setDsrdz(String dsrdz) {
        this.dsrdz = dsrdz;
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

    public Date getJzrq() {
        return jzrq;
    }

    public void setJzrq(Date jzrq) {
        this.jzrq = jzrq;
    }
}
