package com.nju.sdpt.model;

/**
 * @author jiaweiq
 * @date 2021/11/8 21:23
 */
public class ZgysdModel {
    private String czrname;
    private String fybh;
    private String rybh;
    private String bmmc;
    private String sjhm;
    private String ajid;

    public ZgysdModel(String czrname, String fybh, String rybh, String bmmc, String sjhm) {
        this.czrname = czrname;
        this.fybh = fybh;
        this.rybh = rybh;
        this.bmmc = bmmc;
        this.sjhm = sjhm;
    }

    public ZgysdModel(String czrname, String fybh, String rybh, String bmmc, String sjhm, String ajid) {
        this.czrname = czrname;
        this.fybh = fybh;
        this.rybh = rybh;
        this.bmmc = bmmc;
        this.sjhm = sjhm;
        this.ajid = ajid;
    }

    public ZgysdModel() {
    }

    public String getAjid() {
        return ajid;
    }

    public void setAjid(String ajid) {
        this.ajid = ajid;
    }

    public String getCzrname() {
        return czrname;
    }

    public void setCzrname(String czrname) {
        this.czrname = czrname;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }
}
