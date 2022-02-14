package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubZjsdInfoEntity;

public class ZjsdModel extends PubZjsdInfoEntity {


    /**
     * 案号
     */
    private String ah;

    /**
     * 受送达人名称
     */
    private String ssdrmc;

    /**
     * 时间转换
     * @return
     */
    private  String smsjStr;

    /**
     * 是否上传文件
     */
    private String sfscwj;
    /**
     * 文件地址
     */
    private String wjdz;
    /**
     * 文件id
     */
    private String wjid;

    public String getSmsjStr() {
        return smsjStr;
    }

    public void setSmsjStr(String smsjStr) {
        this.smsjStr = smsjStr;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getSsdrmc() {
        return ssdrmc;
    }

    public void setSsdrmc(String ssdrmc) {
        this.ssdrmc = ssdrmc;
    }

    public String getSfscwj() {
        return sfscwj;
    }

    public void setSfscwj(String sfscwj) {
        this.sfscwj = sfscwj;
    }

    public String getWjdz() {
        return wjdz;
    }

    public void setWjdz(String wjdz) {
        this.wjdz = wjdz;
    }

    public String getWjid() {
        return wjid;
    }

    public void setWjid(String wjid) {
        this.wjid = wjid;
    }
}
