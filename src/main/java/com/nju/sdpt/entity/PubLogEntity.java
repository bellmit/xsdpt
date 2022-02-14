package com.nju.sdpt.entity;

import java.util.Date;

public class PubLogEntity extends PubLogEntityKey {
    private String targetname;

    private Integer logtype;

    private String logcontent;

    private String creater;

    private Date creattime;

    private Integer yysdbh;

    public PubLogEntity(Integer logbh, Integer ajxh, String fybh, String targetname, Integer logtype, String logcontent, String creater, Date creattime, Integer yysdbh) {
        super(logbh, ajxh, fybh);
        this.targetname = targetname;
        this.logtype = logtype;
        this.logcontent = logcontent;
        this.creater = creater;
        this.creattime = creattime;
        this.yysdbh = yysdbh;
    }

    public PubLogEntity() {
        super();
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname == null ? null : targetname.trim();
    }

    public Integer getLogtype() {
        return logtype;
    }

    public void setLogtype(Integer logtype) {
        this.logtype = logtype;
    }

    public String getLogcontent() {
        return logcontent;
    }

    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent == null ? null : logcontent.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }
}