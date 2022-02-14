package com.nju.sdpt.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ZwhlModel {
    @ExcelProperty("法院名称")
    private String fymc;
    @ExcelProperty("员工姓名")
    private String ygxm;
    @ExcelProperty("明文外呼总次数")
    private Integer mwwhzcs;
    @ExcelProperty("明文外呼接通总次数")
    private Integer mwwhjtzcs;
    @ExcelProperty("明文外呼通话总时长")
    private Integer mwwhthzsc;
    @ExcelProperty("修复外呼总次数")
    private Integer xfwhzcs;
    @ExcelProperty("修复外呼接通总次数")
    private Integer xfwhjtzcs;
    @ExcelProperty("修复外呼通话总时长")
    private Integer xfwhthzsc;


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

    public Integer getMwwhzcs() {
        return mwwhzcs;
    }

    public void setMwwhzcs(Integer mwwhzcs) {
        this.mwwhzcs = mwwhzcs;
    }

    public Integer getMwwhjtzcs() {
        return mwwhjtzcs;
    }

    public void setMwwhjtzcs(Integer mwwhjtzcs) {
        this.mwwhjtzcs = mwwhjtzcs;
    }

    public Integer getMwwhthzsc() {
        return mwwhthzsc;
    }

    public void setMwwhthzsc(Integer mwwhthzsc) {
        this.mwwhthzsc = mwwhthzsc;
    }

    public Integer getXfwhjtzcs() {
        return xfwhjtzcs;
    }

    public void setXfwhjtzcs(Integer xfwhjtzcs) {
        this.xfwhjtzcs = xfwhjtzcs;
    }

    public Integer getXfwhthzsc() {
        return xfwhthzsc;
    }

    public void setXfwhthzsc(Integer xfwhthzsc) {
        this.xfwhthzsc = xfwhthzsc;
    }

    public Integer getXfwhzcs() {
        return xfwhzcs;
    }

    public void setXfwhzcs(Integer xfwhzcs) {
        this.xfwhzcs = xfwhzcs;
    }
}
