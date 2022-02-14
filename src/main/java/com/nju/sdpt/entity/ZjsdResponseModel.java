package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/11/4 11:13
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AchieveDataModel")
@XmlType(propOrder = {
        "recordId",
        "zjsdbh",
        "fybh",
        "ajxh",
        "dsrid",
        "dsrmc",
        "dsrdz",
        "yhbh",
        "yhdm",
        "qsjg",
        "sdbz",
        "szwz",
        "sdsj",
        "hzbList",
        "sdgcjlList",
})
public class ZjsdResponseModel {
    private String recordId;
    private String zjsdbh;
    private String fybh;
    private String ajxh;
    private String dsrid;
    private String dsrdz;
    private String dsrmc;
    private String yhbh;
    private String yhdm;
    private String qsjg;
    private String sdbz;
    private String szwz;
    private String sdsj;
    private List<HZModel> hzbList;
    private List<TPModel> sdgcjlList;

    public ZjsdResponseModel() {
    }

    public ZjsdResponseModel(String recordId, String zjsdbh, String fybh, String ajxh, String dsrid, String dsrdz, String dsrmc, String yhbh, String yhdm, String qsjg, String sdbz, String szwz, String sdsj, List<HZModel> hzbList, List<TPModel> sdgcjlList) {
        this.recordId = recordId;
        this.zjsdbh = zjsdbh;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.dsrid = dsrid;
        this.dsrdz = dsrdz;
        this.dsrmc = dsrmc;
        this.yhbh = yhbh;
        this.yhdm = yhdm;
        this.qsjg = qsjg;
        this.sdbz = sdbz;
        this.szwz = szwz;
        this.sdsj = sdsj;
        this.hzbList = hzbList;
        this.sdgcjlList = sdgcjlList;
    }

    public List<HZModel> getHzbList() {
        return hzbList;
    }

    public void setHzbList(List<HZModel> hzbList) {
        this.hzbList = hzbList;
    }

    public List<TPModel> getSdgcjlList() {
        return sdgcjlList;
    }

    public void setSdgcjlList(List<TPModel> sdgcjlList) {
        this.sdgcjlList = sdgcjlList;
    }

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

    public String getDsrdz() {
        return dsrdz;
    }

    public void setDsrdz(String dsrdz) {
        this.dsrdz = dsrdz;
    }

    public String getDsrmc() {
        return dsrmc;
    }

    public void setDsrmc(String dsrmc) {
        this.dsrmc = dsrmc;
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

}
