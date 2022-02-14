package com.nju.sdpt.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ZxflModel {
    @ExcelProperty("法院名称")
    private String fymc;
    @ExcelProperty("员工姓名")
    private String ygxm;
    @ExcelProperty("总修复数")
    private Integer ltxftjzs;
    @ExcelProperty("联通修复成功次数")
    private Integer ltxfcgcs;
    @ExcelProperty("移动修复成功次数")
    private Integer ydxfcgcs;
    @ExcelProperty("企业修复成功次数")
    private Integer qyxfcgcs;


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

    public Integer getLtxftjzs() {
        return ltxftjzs;
    }

    public void setLtxftjzs(Integer ltxftjzs) {
        this.ltxftjzs = ltxftjzs;
    }

    public Integer getLtxfcgcs() {
        return ltxfcgcs;
    }

    public void setLtxfcgcs(Integer ltxfcgcs) {
        this.ltxfcgcs = ltxfcgcs;
    }

    public Integer getYdxfcgcs() {
        return ydxfcgcs;
    }

    public void setYdxfcgcs(Integer ydxfcgcs) {
        this.ydxfcgcs = ydxfcgcs;
    }


    public Integer getQyxfcgcs() {
        return qyxfcgcs;
    }

    public void setQyxfcgcs(Integer qyxfcgcs) {
        this.qyxfcgcs = qyxfcgcs;
    }
}
