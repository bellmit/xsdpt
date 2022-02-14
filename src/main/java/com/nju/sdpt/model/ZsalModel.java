package com.nju.sdpt.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ZsalModel {
    @ExcelProperty("法院名称")
    private String fymc;
    @ExcelProperty("收案量")
    private Integer sal;
    @ExcelProperty("涉及当事人")
    private Integer sjdsr;
    @ExcelProperty("送达成功案件量")
    private Integer sdcgajl;
    @ExcelProperty("成功送达涉及当事人")
    private Integer cgsdsjdsr;

    public ZsalModel(String fymc, Integer sal, Integer sjdsr, Integer sdcgajl, Integer cgsdsjdsr) {
        this.fymc = fymc;
        this.sal = sal;
        this.sjdsr = sjdsr;
        this.sdcgajl = sdcgajl;
        this.cgsdsjdsr = cgsdsjdsr;
    }

    public ZsalModel() {
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getSjdsr() {
        return sjdsr;
    }

    public void setSjdsr(Integer sjdsr) {
        this.sjdsr = sjdsr;
    }

    public Integer getSdcgajl() {
        return sdcgajl;
    }

    public void setSdcgajl(Integer sdcgajl) {
        this.sdcgajl = sdcgajl;
    }

    public Integer getCgsdsjdsr() {
        return cgsdsjdsr;
    }

    public void setCgsdsjdsr(Integer cgsdsjdsr) {
        this.cgsdsjdsr = cgsdsjdsr;
    }
}
