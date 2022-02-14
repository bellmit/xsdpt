package com.nju.sdpt.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



@Data
public class SdxxData {
    @ExcelProperty("工单号")
    private int yysdbh;
    @ExcelProperty("案号")
    private String ah;
    @ExcelProperty("当事人姓名")
    private String dsrxm;
    @ExcelProperty("诉讼地位")
    private String ssdw;
    @ExcelProperty("文书数量")
    private int wssl;
    @ExcelProperty("文书类型")
    private String wslx;
    @ExcelProperty("案由")
    private String ay;
    @ExcelProperty("送达结果")
    private String sdjg;
    @ExcelProperty("当事人送达结果")
    private String dsrsdjg;
    @ExcelProperty("工单接收时间")
    private String yysj;
    @ExcelProperty("工单反馈时间")
    private String fksj;
    @ExcelProperty("送达周期")
    private int sdzq;
    @ExcelProperty("发起人所在庭")
    private String ts ;
    @ExcelProperty("任务发起人姓名")
    private String yysxm ;
    @ExcelProperty("法官预约备注")
    private String bz;
    @ExcelProperty("明文号码")
    private String mwhm;
    @ExcelProperty("修复号码")
    private String xfhm;
    @ExcelProperty("邮寄日期")
    private String emsrq ;
    @ExcelProperty("邮寄结果")
    private String emsjg ;
    @ExcelProperty("预约来院时间")
    private String lylqsj;
    @ExcelProperty("开庭时间")
    private String ktsj;
    @ExcelProperty("是否同意电子送达")
    private String sftydzsd;

    public SdxxData() {
        this.yysdbh = 0;
        this.ah = " ";
        this.dsrxm = " ";
        this.ssdw = " ";
        this.wslx = " ";
        this.ay = " ";
        this.sdjg = " ";
        this.dsrsdjg = " ";
        this.yysj = " ";
        this.fksj = " ";
        this.ts = " ";
        this.yysxm = " ";
        this.bz = " ";
        this.mwhm = " ";
        this.xfhm = " ";
        this.emsrq = " ";
        this.emsjg = " ";
        this.lylqsj = " ";
        this.ktsj = " ";
        this.sftydzsd = " ";
    }



    public int getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(int yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getDsrxm() {
        return dsrxm;
    }

    public void setDsrxm(String dsrxm) {
        this.dsrxm = dsrxm;
    }

    public String getSsdw() {
        return ssdw;
    }

    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }

    public int getWssl() {
        return wssl;
    }

    public void setWssl(int wssl) {
        this.wssl = wssl;
    }

    public String getWslx() {
        return wslx;
    }

    public void setWslx(String wslx) {
        this.wslx = wslx;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }

    public String getDsrsdjg() {
        return dsrsdjg;
    }

    public void setDsrsdjg(String dsrsdjg) {
        this.dsrsdjg = dsrsdjg;
    }

    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    public String getFksj() {
        return fksj;
    }

    public void setFksj(String fksj) {
        this.fksj = fksj;
    }

    public int getSdzq() {
        return sdzq;
    }

    public void setSdzq(int sdzq) {
        this.sdzq = sdzq;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getYysxm() {
        return yysxm;
    }

    public void setYysxm(String yysxm) {
        this.yysxm = yysxm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getMwhm() {
        return mwhm;
    }

    public void setMwhm(String mwhm) {
        this.mwhm = mwhm;
    }

    public String getXfhm() {
        return xfhm;
    }

    public void setXfhm(String xfhm) {
        this.xfhm = xfhm;
    }

    public String getEmsrq() {
        return emsrq;
    }

    public void setEmsrq(String emsrq) {
        this.emsrq = emsrq;
    }

    public String getLylqsj() {
        return lylqsj;
    }

    public void setLylqsj(String lylqsj) {
        this.lylqsj = lylqsj;
    }

    public String getKtsj() {
        return ktsj;
    }

    public void setKtsj(String ktsj) {
        this.ktsj = ktsj;
    }

    public String getSftydzsd() {
        return sftydzsd;
    }

    public void setSftydzsd(String sftydzsd) {
        this.sftydzsd = sftydzsd;
    }
}
