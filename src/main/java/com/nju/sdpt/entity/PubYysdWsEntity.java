package com.nju.sdpt.entity;

public class PubYysdWsEntity extends PubYysdWsEntityKey {
    private String wslb;

    private Integer wsly;

    private Integer wslybh;

    private String xxdz;

    private String wsmc;

    private Integer qzbh;

    private Integer ssdrbh;

    private Integer failnum;

    private String status;

    private Integer ajxh;

    private String bz;

    private byte[] wsnr;

    public PubYysdWsEntity(Integer yysdbh, Integer bh, String wslb, Integer wsly, Integer wslybh, String xxdz, String wsmc, Integer qzbh, Integer ssdrbh, Integer failnum, String status, Integer ajxh, String bz) {
        super(yysdbh, bh);
        this.wslb = wslb;
        this.wsly = wsly;
        this.wslybh = wslybh;
        this.xxdz = xxdz;
        this.wsmc = wsmc;
        this.qzbh = qzbh;
        this.ssdrbh = ssdrbh;
        this.failnum = failnum;
        this.status = status;
        this.ajxh = ajxh;
        this.bz = bz;
    }

    public PubYysdWsEntity(Integer yysdbh, Integer bh, String wslb, Integer wsly, Integer wslybh, String xxdz, String wsmc, Integer qzbh, Integer ssdrbh, Integer failnum, String status, Integer ajxh, String bz, byte[] wsnr) {
        super(yysdbh, bh);
        this.wslb = wslb;
        this.wsly = wsly;
        this.wslybh = wslybh;
        this.xxdz = xxdz;
        this.wsmc = wsmc;
        this.qzbh = qzbh;
        this.ssdrbh = ssdrbh;
        this.failnum = failnum;
        this.status = status;
        this.ajxh = ajxh;
        this.bz = bz;
        this.wsnr = wsnr;
    }

    public PubYysdWsEntity() {
        super();
    }

    public String getWslb() {
        return wslb;
    }

    public void setWslb(String wslb) {
        this.wslb = wslb == null ? null : wslb.trim();
    }

    public Integer getWsly() {
        return wsly;
    }

    public void setWsly(Integer wsly) {
        this.wsly = wsly;
    }

    public Integer getWslybh() {
        return wslybh;
    }

    public void setWslybh(Integer wslybh) {
        this.wslybh = wslybh;
    }

    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz == null ? null : xxdz.trim();
    }

    public String getWsmc() {
        return wsmc;
    }

    public void setWsmc(String wsmc) {
        this.wsmc = wsmc == null ? null : wsmc.trim();
    }

    public Integer getQzbh() {
        return qzbh;
    }

    public void setQzbh(Integer qzbh) {
        this.qzbh = qzbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getFailnum() {
        return failnum;
    }

    public void setFailnum(Integer failnum) {
        this.failnum = failnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public byte[] getWsnr() {
        return wsnr;
    }

    public void setWsnr(byte[] wsnr) {
        this.wsnr = wsnr;
    }
}