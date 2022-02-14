package com.nju.sdpt.entity;

import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/11/13 17:23
 */
public class PostWfyModel {
    private int bmdh;
    private int yysdbh;
    private int ssdrbh;
    private int wsbh;
    private int ajxh;
    private String fybh;
    private String ah;
    private String ay;
    private String fsrid;
    private String fsrxm;

    private List<Wsxx> wsxx;
    private List<Ssdrxx> ssdrxx;

    public PostWfyModel(int bmdh, int yysdbh, int ssdrbh, int wsbh, String fybh, String ah, String ay, String fsrid, String fsrxm, List<Wsxx> wsxx, List<Ssdrxx> ssdrxx) {
        this.bmdh = bmdh;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.wsbh = wsbh;
        this.fybh = fybh;
        this.ah = ah;
        this.ay = ay;
        this.fsrid = fsrid;
        this.fsrxm = fsrxm;
        this.wsxx = wsxx;
        this.ssdrxx = ssdrxx;
    }

    public PostWfyModel(String fybh, String ah, String ay, String fsrid, String fsrxm, List<Wsxx> wsxx, List<Ssdrxx> ssdrxx) {
        this.fybh = fybh;
        this.ah = ah;
        this.ay = ay;
        this.fsrid = fsrid;
        this.fsrxm = fsrxm;
        this.wsxx = wsxx;
        this.ssdrxx = ssdrxx;
    }

    public PostWfyModel() {
    }

    public PostWfyModel(int bmdh, int yysdbh, int ssdrbh, int wsbh, int ajxh, String fybh, String ah, String ay, String fsrid, String fsrxm, List<Wsxx> wsxx, List<Ssdrxx> ssdrxx) {
        this.bmdh = bmdh;
        this.yysdbh = yysdbh;
        this.ssdrbh = ssdrbh;
        this.wsbh = wsbh;
        this.ajxh = ajxh;
        this.fybh = fybh;
        this.ah = ah;
        this.ay = ay;
        this.fsrid = fsrid;
        this.fsrxm = fsrxm;
        this.wsxx = wsxx;
        this.ssdrxx = ssdrxx;
    }

    public int getAjxh() {
        return ajxh;
    }

    public void setAjxh(int ajxh) {
        this.ajxh = ajxh;
    }

    public int getBmdh() {
        return bmdh;
    }

    public void setBmdh(int bmdh) {
        this.bmdh = bmdh;
    }

    public int getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(int yysdbh) {
        this.yysdbh = yysdbh;
    }

    public int getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(int ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public int getWsbh() {
        return wsbh;
    }

    public void setWsbh(int wsbh) {
        this.wsbh = wsbh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getFsrid() {
        return fsrid;
    }

    public void setFsrid(String fsrid) {
        this.fsrid = fsrid;
    }

    public String getFsrxm() {
        return fsrxm;
    }

    public void setFsrxm(String fsrxm) {
        this.fsrxm = fsrxm;
    }

    public List<Wsxx> getWsxx() {
        return wsxx;
    }

    public void setWsxx(List<Wsxx> wsxx) {
        this.wsxx = wsxx;
    }

    public List<Ssdrxx> getSsdrxx() {
        return ssdrxx;
    }

    public void setSsdrxx(List<Ssdrxx> ssdrxx) {
        this.ssdrxx = ssdrxx;
    }
}
