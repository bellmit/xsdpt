package com.nju.sdpt.model;

public class CaseInfoRequestParam {
    /**
     * 案件编号
     */
    private String ajbh;
    /**
     * 案件类别
     */
    private String ajlb;
    /**
     * 法院id
     */
    private String fyid;

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getAjlb() {
        return ajlb;
    }

    public void setAjlb(String ajlb) {
        this.ajlb = ajlb;
    }

    public String getFyid() {
        return fyid;
    }

    public void setFyid(String fyid) {
        this.fyid = fyid;
    }
}
