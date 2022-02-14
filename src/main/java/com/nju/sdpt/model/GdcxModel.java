package com.nju.sdpt.model;

public class GdcxModel {
    String fybh;
    Integer yysdbh;
    String ah;
    String ajmc;
    String ssdr;
    String yyr;
    Integer sdzt;
    String startTime;
    String endTime;
    String sdr;//送达人员姓名

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getSsdr() {
        return ssdr;
    }

    public void setSsdr(String ssdr) {
        this.ssdr = ssdr;
    }

    public String getYyr() {
        return yyr;
    }

    public void setYyr(String yyr) {
        this.yyr = yyr;
    }

    public Integer getSdzt() {
        return sdzt;
    }

    public void setSdzt(Integer sdzt) {
        this.sdzt = sdzt;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GdcxModel() {
    }


    public String getSdr() {
        return sdr;
    }

    public void setSdr(String sdr) {
        this.sdr = sdr;
    }

    public GdcxModel(String fybh, Integer yysdbh, String ah, String ajmc, String ssdr, String yyr, Integer sdzt, String startTime, String endTime, String sdr) {
        this.fybh = fybh;
        this.yysdbh = yysdbh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.ssdr = ssdr;
        this.yyr = yyr;
        this.sdzt = sdzt;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sdr = sdr;
    }
}
