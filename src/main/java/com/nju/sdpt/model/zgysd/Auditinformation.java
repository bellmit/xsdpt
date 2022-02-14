package com.nju.sdpt.model.zgysd;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Auditinformation implements Serializable {
    @JSONField(ordinal = 1)
    private String auditstatus;
    @JSONField(ordinal = 2)
    private String auditor;
    @JSONField(ordinal = 4)
    private Auditmsg auditmsg;
    @JSONField(ordinal = 3)
    private String auditorlev;

    public Auditinformation(String auditstatus, String auditor, Auditmsg auditmsg, String auditorlev) {
        this.auditstatus = auditstatus;
        this.auditor = auditor;
        this.auditmsg = auditmsg;
        this.auditorlev = auditorlev;
    }

    public Auditinformation() {
    }

    public Auditmsg getAuditmsg() {
        return auditmsg;
    }

    public void setAuditmsg(Auditmsg auditmsg) {
        this.auditmsg = auditmsg;
    }

    public String getAuditorlev() {
        return auditorlev;
    }

    public void setAuditorlev(String auditorlev) {
        this.auditorlev = auditorlev;
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

}
