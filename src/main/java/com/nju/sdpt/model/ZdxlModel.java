package com.nju.sdpt.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ZdxlModel {
    @ExcelProperty("法院名称")
    private String fymc;
    @ExcelProperty("员工姓名")
    private String ygxm;
    @ExcelProperty("明文短信下发总条数")
    private Integer mwdxxfzts;
    @ExcelProperty("明文短信下发成功总数")
    private Integer mwdxxfcgzs;
    @ExcelProperty("修复短信下发总条数")
    private Integer xfdxxfzts;
    @ExcelProperty("修复短信下发成功总数")
    private Integer xfdxxfcgzs;
    @ExcelProperty("带链接短信下发数")
    private Integer dljdxxfs;
    @ExcelProperty("带链接短信访问数")
    private Integer dljdxfws;


    public ZdxlModel() {
    }

    public ZdxlModel(String fymc, String ygxm, Integer mwdxxfzts, Integer mwdxxfcgzs, Integer xfdxxfzts, Integer xfdxxfcgzs, Integer dljdxxfs, Integer dljdxfws) {
        this.fymc = fymc;
        this.ygxm = ygxm;
        this.mwdxxfzts = mwdxxfzts;
        this.mwdxxfcgzs = mwdxxfcgzs;
        this.xfdxxfzts = xfdxxfzts;
        this.xfdxxfcgzs = xfdxxfcgzs;
        this.dljdxxfs = dljdxxfs;
        this.dljdxfws = dljdxfws;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm;
    }

    public Integer getMwdxxfzts() {
        return mwdxxfzts;
    }

    public void setMwdxxfzts(Integer mwdxxfzts) {
        this.mwdxxfzts = mwdxxfzts;
    }

    public Integer getMwdxxfcgzs() {
        return mwdxxfcgzs;
    }

    public void setMwdxxfcgzs(Integer mwdxxfcgzs) {
        this.mwdxxfcgzs = mwdxxfcgzs;
    }

    public Integer getXfdxxfzts() {
        return xfdxxfzts;
    }

    public void setXfdxxfzts(Integer xfdxxfzts) {
        this.xfdxxfzts = xfdxxfzts;
    }

    public Integer getXfdxxfcgzs() {
        return xfdxxfcgzs;
    }

    public void setXfdxxfcgzs(Integer xfdxxfcgzs) {
        this.xfdxxfcgzs = xfdxxfcgzs;
    }

    public Integer getDljdxxfs() {
        return dljdxxfs;
    }

    public void setDljdxxfs(Integer dljdxxfs) {
        this.dljdxxfs = dljdxxfs;
    }

    public Integer getDljdxfws() {
        return dljdxfws;
    }

    public void setDljdxfws(Integer dljdxfws) {
        this.dljdxfws = dljdxfws;
    }
}
