package com.nju.sdpt.viewobject;

public class DxtzMwdxVO {
    String ah;
    String dsrmc;
    String dh;
    String creattime;
    String msgContent;
    Integer sendStatus;
    Integer fwzt;
    String cusid;

    public DxtzMwdxVO() {
    }


    public DxtzMwdxVO(String ah, String dsrmc, String dh, String creattime, String msgContent, Integer sendStatus, Integer fwzt, String cusid) {
        this.ah = ah;
        this.dsrmc = dsrmc;
        this.dh = dh;
        this.creattime = creattime;
        this.msgContent = msgContent;
        this.sendStatus = sendStatus;
        this.fwzt = fwzt;
        this.cusid = cusid;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getDsrmc() {
        return dsrmc;
    }

    public void setDsrmc(String dsrmc) {
        this.dsrmc = dsrmc;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getFwzt() {
        return fwzt;
    }

    public void setFwzt(Integer fwzt) {
        this.fwzt = fwzt;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }
}
