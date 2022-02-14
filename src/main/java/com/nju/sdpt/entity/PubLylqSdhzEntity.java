package com.nju.sdpt.entity;

public class PubLylqSdhzEntity {
    private Integer sdhzid;

    private Integer lylqid;

    private Integer yysdbh;

    private String sdhzfolder;

    private String sdhzimage;

    public PubLylqSdhzEntity(Integer sdhzid, Integer lylqid, Integer yysdbh, String sdhzfolder, String sdhzimage) {
        this.sdhzid = sdhzid;
        this.lylqid = lylqid;
        this.yysdbh = yysdbh;
        this.sdhzfolder = sdhzfolder;
        this.sdhzimage = sdhzimage;
    }

    public PubLylqSdhzEntity() {
        super();
    }

    public Integer getSdhzid() {
        return sdhzid;
    }

    public void setSdhzid(Integer sdhzid) {
        this.sdhzid = sdhzid;
    }

    public Integer getLylqid() {
        return lylqid;
    }

    public void setLylqid(Integer lylqid) {
        this.lylqid = lylqid;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getSdhzfolder() {
        return sdhzfolder;
    }

    public void setSdhzfolder(String sdhzfolder) {
        this.sdhzfolder = sdhzfolder == null ? null : sdhzfolder.trim();
    }

    public String getSdhzimage() {
        return sdhzimage;
    }

    public void setSdhzimage(String sdhzimage) {
        this.sdhzimage = sdhzimage == null ? null : sdhzimage.trim();
    }
}