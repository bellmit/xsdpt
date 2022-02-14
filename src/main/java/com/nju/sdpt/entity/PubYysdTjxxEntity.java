package com.nju.sdpt.entity;

import java.util.Date;

public class PubYysdTjxxEntity {
    private String uuid;

    private String lx;

    private String sjwd;

    private Date time;

    private Float tjcount;

    public PubYysdTjxxEntity(String uuid, String lx, String sjwd, Date time, Float tjcount) {
        this.uuid = uuid;
        this.lx = lx;
        this.sjwd = sjwd;
        this.time = time;
        this.tjcount = tjcount;
    }

    public PubYysdTjxxEntity() {
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }

    public String getSjwd() {
        return sjwd;
    }

    public void setSjwd(String sjwd) {
        this.sjwd = sjwd == null ? null : sjwd.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Float getTjcount() {
        return tjcount;
    }

    public void setTjcount(Float tjcount) {
        this.tjcount = tjcount;
    }
}