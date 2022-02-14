package com.nju.sdpt.entity;

/**
 * @author jiaweiq
 * @date 2021/11/13 15:22
 */
public class Wsxx {
    // 文书实体码
    private String wsstm;
    // 送达文书名称
    private String sdwsmc;
    // 送达文书id
    private String sdwsid;
    // 送达文书url
    private String sdwsurl;
    // 送达文书生成日期
    private String sdwsscrq;
    // 送达文书类型
    private String sdwslx;
    // 送达文书类型名称
    private String sdwslxmc;
    // 送达文书格式
    private String sdwsgs;

    public Wsxx(String wsstm, String sdwsmc, String sdwsid, String sdwsurl, String sdwsscrq, String sdwslx, String sdwslxmc, String sdwsgs) {
        this.wsstm = wsstm;
        this.sdwsmc = sdwsmc;
        this.sdwsid = sdwsid;
        this.sdwsurl = sdwsurl;
        this.sdwsscrq = sdwsscrq;
        this.sdwslx = sdwslx;
        this.sdwslxmc = sdwslxmc;
        this.sdwsgs = sdwsgs;
    }

    public Wsxx() {
    }

    public String getWsstm() {
        return wsstm;
    }

    public void setWsstm(String wsstm) {
        this.wsstm = wsstm;
    }

    public String getSdwsmc() {
        return sdwsmc;
    }

    public void setSdwsmc(String sdwsmc) {
        this.sdwsmc = sdwsmc;
    }

    public String getSdwsid() {
        return sdwsid;
    }

    public void setSdwsid(String sdwsid) {
        this.sdwsid = sdwsid;
    }

    public String getSdwsurl() {
        return sdwsurl;
    }

    public void setSdwsurl(String sdwsurl) {
        this.sdwsurl = sdwsurl;
    }

    public String getSdwsscrq() {
        return sdwsscrq;
    }

    public void setSdwsscrq(String sdwsscrq) {
        this.sdwsscrq = sdwsscrq;
    }

    public String getSdwslx() {
        return sdwslx;
    }

    public void setSdwslx(String sdwslx) {
        this.sdwslx = sdwslx;
    }

    public String getSdwslxmc() {
        return sdwslxmc;
    }

    public void setSdwslxmc(String sdwslxmc) {
        this.sdwslxmc = sdwslxmc;
    }

    public String getSdwsgs() {
        return sdwsgs;
    }

    public void setSdwsgs(String sdwsgs) {
        this.sdwsgs = sdwsgs;
    }
}
