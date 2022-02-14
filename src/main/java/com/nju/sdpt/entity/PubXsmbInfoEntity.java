package com.nju.sdpt.entity;

public class PubXsmbInfoEntity {
    private Integer id;

    private String mbmc;

    private String mbid;

    private String scalling;

    private String mbnr;

    private String fybh;

    public PubXsmbInfoEntity(Integer id, String mbmc, String mbid, String scalling, String mbnr, String fybh) {
        this.id = id;
        this.mbmc = mbmc;
        this.mbid = mbid;
        this.scalling = scalling;
        this.mbnr = mbnr;
        this.fybh = fybh;
    }

    public PubXsmbInfoEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMbmc() {
        return mbmc;
    }

    public void setMbmc(String mbmc) {
        this.mbmc = mbmc == null ? null : mbmc.trim();
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid == null ? null : mbid.trim();
    }

    public String getScalling() {
        return scalling;
    }

    public void setScalling(String scalling) {
        this.scalling = scalling == null ? null : scalling.trim();
    }

    public String getMbnr() {
        return mbnr;
    }

    public void setMbnr(String mbnr) {
        this.mbnr = mbnr == null ? null : mbnr.trim();
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh == null ? null : fybh.trim();
    }
}