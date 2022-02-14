package com.nju.sdpt.model;

import java.util.Date;

public class PubLcspFjbModel {

    private Date date;
    private byte[] content;
    private String ajxh;
    private String id;
    private String description;

    public PubLcspFjbModel() {
    }

    public PubLcspFjbModel(Date date, byte[] content, String ajxh, String id, String description) {
        this.date = date;
        this.content = content;
        this.ajxh = ajxh;
        this.id = id;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getAjxh() {
        return ajxh;
    }

    public void setAjxh(String ajxh) {
        this.ajxh = ajxh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
