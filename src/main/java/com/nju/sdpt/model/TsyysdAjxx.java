package com.nju.sdpt.model;

/**
 * @author jiaweiq
 * @date 2021/11/26 11:37
 */
public class TsyysdAjxx {
    private String ah;
    private String ajmc;
    private String laay;
    private int fg;
    private String sfcbr;
    private String xm;
    private String yhmc;
    private int yhbh;
    private String dmms;
    private String phone;

    public TsyysdAjxx(String ah, String ajmc, String laay, int fg, String sfcbr, String xm, String yhmc, int yhbh) {
        this.ah = ah;
        this.ajmc = ajmc;
        this.laay = laay;
        this.fg = fg;
        this.sfcbr = sfcbr;
        this.xm = xm;
        this.yhmc = yhmc;
        this.yhbh = yhbh;
    }

    public TsyysdAjxx(String ah, String ajmc, String laay, int fg, String sfcbr, String xm, String yhmc, int yhbh, String dmms, String phone) {
        this.ah = ah;
        this.ajmc = ajmc;
        this.laay = laay;
        this.fg = fg;
        this.sfcbr = sfcbr;
        this.xm = xm;
        this.yhmc = yhmc;
        this.yhbh = yhbh;
        this.dmms = dmms;
        this.phone = phone;
    }

    public TsyysdAjxx() {
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

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }

    public int getFg() {
        return fg;
    }

    public void setFg(int fg) {
        this.fg = fg;
    }

    public String getSfcbr() {
        return sfcbr;
    }

    public void setSfcbr(String sfcbr) {
        this.sfcbr = sfcbr;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public int getYhbh() {
        return yhbh;
    }

    public void setYhbh(int yhbh) {
        this.yhbh = yhbh;
    }

    public String getDmms() {
        return dmms;
    }

    public void setDmms(String dmms) {
        this.dmms = dmms;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
