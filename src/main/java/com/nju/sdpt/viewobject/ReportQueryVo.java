package com.nju.sdpt.viewobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 数据统计 - 查询VO
 */
@Data
public class ReportQueryVo {

    //工单承办人员标识
    @ApiModelProperty("统计维度 TS: 庭室  FG: 法官")
    private String reportType;

    @ApiModelProperty("统计时间-开始 Y-M-D")
    private String startTime;

    @ApiModelProperty("统计时间-结束 Y-M-D")
    private String endTime;
    @ApiModelProperty("庭室名称")
    private String tsName;

    @ApiModelProperty("工单/案号统计维度")
    private String gdOrAhType;

    @ApiModelProperty("当事人地位")
    private String dsrDw;

    /**
     * com.nju.sdpt.constant.SdptConstants.REPORT_CUS_TYPE
     */
    @ApiModelProperty("其他自定义统计维度  PEO:人数 COUNT:次数 ")
    private String reportCusType;

    @ApiModelProperty("从session中获取的登陆yhm，后端赋值")
    private String loginYhm;

    @ApiModelProperty("从session中获取的登陆yhbh，后端赋值")
    private Integer yhbh;

    @ApiModelProperty("数据权限使用，后端赋值")
    private List<String> fybhSet;

    @ApiModelProperty("部门编号,数据权限使用，后端赋值")
    private String bmbh;

    @ApiModelProperty("法官名称")
    private String fgmc;

    @ApiModelProperty("权限等级")
    private Integer authority;

    @ApiModelProperty("是否送达平台人员（反之为法官）")
    private Boolean sdptRy = true;

    public String getReportType() {
        return reportType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReportCusType() {
        return reportCusType;
    }

    public void setReportCusType(String reportCusType) {
        this.reportCusType = reportCusType;
    }

    public String getLoginYhm() {
        return loginYhm;
    }

    public void setLoginYhm(String loginYhm) {
        this.loginYhm = loginYhm;
    }

    public List<String> getFybhSet() {
        return fybhSet;
    }

    public void setFybhSet(List<String> fybhSet) {
        this.fybhSet = fybhSet;
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh;
    }

    public String getFgmc() {
        return fgmc;
    }

    public void setFgmc(String fgmc) {
        this.fgmc = fgmc;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Boolean getSdptRy() {
        return sdptRy;
    }

    public void setSdptRy(Boolean sdptRy) {
        this.sdptRy = sdptRy;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public String getDsrDw() {
        return dsrDw;
    }

    public void setDsrDw(String dsrDw) {
        this.dsrDw = dsrDw;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public String getGdOrAhType() {
        return gdOrAhType;
    }

    public void setGdOrAhType(String gdOrAhType) {
        this.gdOrAhType = gdOrAhType;
    }
}
