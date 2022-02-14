package com.nju.sdpt.entity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class AchieveDataModel implements Serializable {
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getZjsdbh() {
        return zjsdbh;
    }

    public void setZjsdbh(String zjsdbh) {
        this.zjsdbh = zjsdbh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getAjxh() {
        return ajxh;
    }

    public void setAjxh(String ajxh) {
        this.ajxh = ajxh;
    }

    public String getDsrid() {
        return dsrid;
    }

    public void setDsrid(String dsrid) {
        this.dsrid = dsrid;
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

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getQsjg() {
        return qsjg;
    }

    public void setQsjg(String qsjg) {
        this.qsjg = qsjg;
    }

    public String getSdbz() {
        return sdbz;
    }

    public void setSdbz(String sdbz) {
        this.sdbz = sdbz;
    }

    public String getSzwz() {
        return szwz;
    }

    public void setSzwz(String szwz) {
        this.szwz = szwz;
    }

    public String getSdsj() {
        return sdsj;
    }

    public void setSdsj(String sdsj) {
        this.sdsj = sdsj;
    }


    private String recordId;


    private String zjsdbh;


    private String fybh;


    private String ajxh;


    private String dsrid;


    private String dsrmc;


    private String dsrdz;


    private String yhbh;


    private String yhdm;


    private String qsjg;


    private String sdbz;


    private String szwz;

    private String sdsj;


    public List<String> getHzbList() {
        return hzbList;
    }

    public void setHzbList(List<String> hzbList) {
        this.hzbList = hzbList;
    }

    public List<String> getSdgcjlList() {
        return sdgcjlList;
    }

    public void setSdgcjlList(List<String> sdgcjlList) {
        this.sdgcjlList = sdgcjlList;
    }

    private List<String> hzbList;

    private List<String> sdgcjlList;

//    @XStreamAlias("hzbList")
//    private byte[][] hzbList;
//
//    @XStreamAlias("sdgcjlList")
//    private byte[][] sdgcjlList;
}


