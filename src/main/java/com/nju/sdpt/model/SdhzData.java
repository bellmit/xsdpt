package com.nju.sdpt.model;

import java.util.List;

/**
 * 回证接口标准(调用方：送达管家）的请求参数
 */
public class SdhzData {

    // 案件编号
    private String cbhaj;
    //
    private String najlb;
    // 送达编号
    private String cbh;
    // 送达类型
    private String nsdlx;
    private List<SdhzDataHcws> hcwsList;
    private List<SdhzDataHzwsxx> hzwsxx;
    // 送达文书信息
    private List<SdhzDataCws> cws;
    // 送达情况
    private List<SdhzDataSdqk> sdqk;
    // 送达方式
    private String nsdfs;

    public String getCbhaj() {
        return cbhaj;
    }

    public void setCbhaj(String cbhaj) {
        this.cbhaj = cbhaj;
    }

    public String getNajlb() {
        return najlb;
    }

    public void setNajlb(String najlb) {
        this.najlb = najlb;
    }

    public String getCbh() {
        return cbh;
    }

    public void setCbh(String cbh) {
        this.cbh = cbh;
    }

    public String getNsdlx() {
        return nsdlx;
    }

    public void setNsdlx(String nsdlx) {
        this.nsdlx = nsdlx;
    }

    public List<SdhzDataHcws> getHcwsList() {
        return hcwsList;
    }

    public void setHcwsList(List<SdhzDataHcws> hcwsList) {
        this.hcwsList = hcwsList;
    }

    public List<SdhzDataHzwsxx> getHzwsxx() {
        return hzwsxx;
    }

    public void setHzwsxx(List<SdhzDataHzwsxx> hzwsxx) {
        this.hzwsxx = hzwsxx;
    }

    public List<SdhzDataCws> getCws() {
        return cws;
    }

    public void setCws(List<SdhzDataCws> cws) {
        this.cws = cws;
    }

    public List<SdhzDataSdqk> getSdqk() {
        return sdqk;
    }

    public void setSdqk(List<SdhzDataSdqk> sdqk) {
        this.sdqk = sdqk;
    }

    public String getNsdfs() {
        return nsdfs;
    }

    public void setNsdfs(String nsdfs) {
        this.nsdfs = nsdfs;
    }


}
