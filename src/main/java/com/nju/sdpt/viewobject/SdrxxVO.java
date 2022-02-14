package com.nju.sdpt.viewobject;

public class SdrxxVO {
    String sdrmc; //送达人名称
    String fymc;  //法院名称
    String bmmc;  //部门名称
    String bgdh;  //办公电话
    String sjhm;  //手机号码

    public String getSdrmc() {
        return sdrmc;
    }

    public void setSdrmc(String sdrmc) {
        this.sdrmc = sdrmc;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getBgdh() {
        return bgdh;
    }

    public void setBgdh(String bgdh) {
        this.bgdh = bgdh;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public SdrxxVO() {
    }

    public SdrxxVO(String sdrmc, String fymc, String bmmc, String bgdh, String sjhm) {
        this.sdrmc = sdrmc;
        this.fymc = fymc;
        this.bmmc = bmmc;
        this.bgdh = bgdh;
        this.sjhm = sjhm;
    }
}
