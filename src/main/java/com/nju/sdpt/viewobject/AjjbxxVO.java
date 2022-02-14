package com.nju.sdpt.viewobject;

public class AjjbxxVO {
    String ajlx;
    String ajmc;
    String ah;
    String laay;
    Integer ajxh;
    String fybh;
    String ktsj;

    public String getKtsj() {
        return ktsj;
    }

    public void setKtsj(String ktsj) {
        this.ktsj = ktsj;
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
        this.fybh = fybh;
    }

    public String getAjlx() {
        return ajlx;
    }

    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getAh() {
        return ah;
    }


    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }

    public AjjbxxVO() {
    }

    public AjjbxxVO(String ajlx, String ajmc, String ah, String laay, Integer ajxh, String fybh,String ktsj) {
        this.ajlx = ajlx;
        this.ajmc = ajmc;
        this.ah = ah;
        this.laay = laay;
        this.ajxh = ajxh;
        this.fybh = fybh;
        this.ktsj=ktsj;
    }

    public AjjbxxVO(String ajlx, String ajmc, String ah, String laay, Integer ajxh, String fybh) {
        this.ajlx = ajlx;
        this.ajmc = ajmc;
        this.ah = ah;
        this.laay = laay;
        this.ajxh = ajxh;
        this.fybh = fybh;
    }
}
