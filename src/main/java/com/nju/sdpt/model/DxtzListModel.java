package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubDxtzInfoEntity;

public class DxtzListModel extends PubDxtzInfoEntity {

    /**
     * 创建时间格式化字符串
     */
    private String createtimeStr;


    /**
     * 案号
     */
    private String ah;

    /**
     * 当事人姓名
     */
    private String dsrmc;

    /**
     * 短信模板名称
     */
    private String mbmc;
    /**
     * 资源url
     */
    private String zyurl;

    /**
     * 业务id
     */
    private Integer ywid;

    /**
     * 當事人名稱
     */
    private String ssdrmc;

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }

    public Integer getYwid() {
        return ywid;
    }

    public void setYwid(Integer ywid) {
        this.ywid = ywid;
    }

    public String getZyurl() {
        return zyurl;
    }

    public void setZyurl(String zyurl) {
        this.zyurl = zyurl;
    }

    public String getMbmc() {
        return mbmc;
    }

    public void setMbmc(String mbmc) {
        this.mbmc = mbmc;
    }

    public String getCreatetimeStr() {
        return createtimeStr;
    }

    public void setCreatetimeStr(String createtimeStr) {
        this.createtimeStr = createtimeStr;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getDsrmc() {
        return dsrmc;
    }

    public void setDsrmc(String dsrmc) {
        this.dsrmc = dsrmc;
    }
}
