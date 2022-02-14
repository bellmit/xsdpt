package com.nju.sdpt.viewobject;

public class TbsjVO {
    private String fydm;
    private String ah;
    private String lx;
    private String fw;

    public TbsjVO(String fydm, String ah, String lx, String fw) {
        this.fydm = fydm;
        this.ah = ah;
        this.lx = lx;
        this.fw = fw;


    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }
}
