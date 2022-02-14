package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2021/11/5 16:44
 */
public class LoginModel {
    String yhmc;
    String nr;
    String fybh;

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public LoginModel(String yhmc, String nr) {
        this.yhmc = yhmc;
        this.nr = nr;
    }

    public LoginModel() {
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
