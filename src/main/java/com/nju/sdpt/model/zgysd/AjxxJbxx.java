package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class AjxxJbxx implements Serializable {
    @JSONField(ordinal = 1)
    private String ah;
    @JSONField(ordinal = 2)
    private String ajid;
    @JSONField(ordinal = 3)
    private String ajlx;
    @JSONField(ordinal = 4)
    private String ay;
    @JSONField(ordinal = 5)
    private String larq;
    @JSONField(ordinal = 6)
    private String cbbm;
    @JSONField(ordinal = 7)
    private String jzdz;
    @JSONField(ordinal = 8)
    private String fycode;
    @JSONField(ordinal = 9)
    private String fydz;
    @JSONField(ordinal = 10)
    private String ajzt;
    @JSONField(ordinal = 11)
    private Auditinformation auditinformation;
    @JSONField(ordinal = 12)
    private String ajsltzs_dir;

    public AjxxJbxx() {
    }

    public AjxxJbxx(String ah, String ajid, String ajlx, String ay, String larq, String cbbm, String jzdz, String fycode, String fydz, String ajzt, Auditinformation auditinformation, String ajsltzs_dir) {
        this.ah = ah;
        this.ajid = ajid;
        this.ajlx = ajlx;
        this.ay = ay;
        this.larq = larq;
        this.cbbm = cbbm;
        this.jzdz = jzdz;
        this.fycode = fycode;
        this.fydz = fydz;
        this.ajzt = ajzt;
        this.auditinformation = auditinformation;
        this.ajsltzs_dir = ajsltzs_dir;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjid() {
        return ajid;
    }

    public void setAjid(String ajid) {
        this.ajid = ajid;
    }

    public String getAjlx() {
        return ajlx;
    }

    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getLarq() {
        return larq;
    }

    public void setLarq(String larq) {
        this.larq = larq;
    }

    public String getCbbm() {
        return cbbm;
    }

    public void setCbbm(String cbbm) {
        this.cbbm = cbbm;
    }

    public String getJzdz() {
        return jzdz;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
    }

    public String getFycode() {
        return fycode;
    }

    public void setFycode(String fycode) {
        this.fycode = fycode;
    }

    public String getFydz() {
        return fydz;
    }

    public void setFydz(String fydz) {
        this.fydz = fydz;
    }

    public String getAjzt() {
        return ajzt;
    }

    public void setAjzt(String ajzt) {
        this.ajzt = ajzt;
    }

    public Auditinformation getAuditinformation() {
        return auditinformation;
    }

    public void setAuditinformation(Auditinformation auditinformation) {
        this.auditinformation = auditinformation;
    }

    public String getAjsltzs_dir() {
        return ajsltzs_dir;
    }

    public void setAjsltzs_dir(String ajsltzs_dir) {
        this.ajsltzs_dir = ajsltzs_dir;
    }
}
