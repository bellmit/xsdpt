package com.nju.sdpt.entity;

import java.util.Date;

public class PubWebcallSeatEntity {
    private Integer id;

    private String seatid;

    private String type;

    private Integer status;

    private Date updatetime;

    public PubWebcallSeatEntity(Integer id, String seatid, String type, Integer status, Date updatetime) {
        this.id = id;
        this.seatid = seatid;
        this.type = type;
        this.status = status;
        this.updatetime = updatetime;
    }

    public PubWebcallSeatEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeatid() {
        return seatid;
    }

    public void setSeatid(String seatid) {
        this.seatid = seatid == null ? null : seatid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}