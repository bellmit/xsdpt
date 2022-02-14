package com.nju.sdpt.model;

public class DataResult {
    private String wjid;
    private String wjssml;
    private String wjmc;


    public DataResult() {
    }

    public DataResult(String wjid, String wjssml, String wjmc) {
        this.wjid = wjid;
        this.wjssml = wjssml;
        this.wjmc = wjmc;
    }

    public String getWjid() {
        return wjid;
    }

    public void setWjid(String wjid) {
        this.wjid = wjid;
    }

    public String getWjssml() {
        return wjssml;
    }

    public void setWjssml(String wjssml) {
        this.wjssml = wjssml;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }
}
