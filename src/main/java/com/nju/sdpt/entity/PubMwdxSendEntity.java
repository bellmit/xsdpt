package com.nju.sdpt.entity;

import java.util.Date;

public class PubMwdxSendEntity {
    private Integer id;

    private String sendphone;

    private String lsh;

    private Integer sendtype;

    private Integer sendstatus;

    private Date cratetime;

    private Integer yysdbh;

    private String cusid;

    private String fybh;

    private Integer ajxh;

    private String fgbh;

    private Integer ssdrbh;

    private Integer fwzt;

    private String msgcontent;

    public PubMwdxSendEntity(Integer id, String sendphone, String lsh, Integer sendtype, Integer sendstatus, Date cratetime, Integer yysdbh, String cusid, String fybh, Integer ajxh, String fgbh, Integer ssdrbh, Integer fwzt, String msgcontent) {
        this.id = id;
        this.sendphone = sendphone;
        this.lsh = lsh;
        this.sendtype = sendtype;
        this.sendstatus = sendstatus;
        this.cratetime = cratetime;
        this.yysdbh = yysdbh;
        this.cusid = cusid;
        this.fybh = fybh;
        this.ajxh = ajxh;
        this.fgbh = fgbh;
        this.ssdrbh = ssdrbh;
        this.fwzt = fwzt;
        this.msgcontent = msgcontent;
    }

    public PubMwdxSendEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendphone() {
        return sendphone;
    }

    public void setSendphone(String sendphone) {
        this.sendphone = sendphone == null ? null : sendphone.trim();
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh == null ? null : lsh.trim();
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public Integer getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(Integer sendstatus) {
        this.sendstatus = sendstatus;
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid == null ? null : cusid.trim();
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getFgbh() {
        return fgbh;
    }

    public void setFgbh(String fgbh) {
        this.fgbh = fgbh == null ? null : fgbh.trim();
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getFwzt() {
        return fwzt;
    }

    public void setFwzt(Integer fwzt) {
        this.fwzt = fwzt;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }
}