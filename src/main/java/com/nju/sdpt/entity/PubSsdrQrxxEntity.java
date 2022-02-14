package com.nju.sdpt.entity;

public class PubSsdrQrxxEntity {
    private Integer bh;

    private Integer ajxh;

    private String fybh;

    private Integer dsrbh;

    private Integer sfqssddzqrs;

    private Integer sftydzsd;

    public PubSsdrQrxxEntity(Integer bh, Integer ajxh, String fybh, Integer dsrbh, Integer sfqssddzqrs, Integer sftydzsd) {
        this.bh = bh;
        this.ajxh = ajxh;
        this.fybh = fybh;
        this.dsrbh = dsrbh;
        this.sfqssddzqrs = sfqssddzqrs;
        this.sftydzsd = sftydzsd;
    }

    public PubSsdrQrxxEntity() {
        super();
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }

    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }

    public Integer getSfqssddzqrs() {
        return sfqssddzqrs;
    }

    public void setSfqssddzqrs(Integer sfqssddzqrs) {
        this.sfqssddzqrs = sfqssddzqrs;
    }

    public Integer getSftydzsd() {
        return sftydzsd;
    }

    public void setSftydzsd(Integer sftydzsd) {
        this.sftydzsd = sftydzsd;
    }
}