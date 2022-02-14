package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubLylqInfoEntity;

import java.util.List;

public class LylqModel extends PubLylqInfoEntity {

    /**
     * 预约时间格式化字符串
     */
    private String yylqsjStr;

    /**
     * 案号
     */
    private String ah;

    /**
     * 受送达人名称
     */
    private String ssdrmc;

    /**
     * 发起人
     * @return
     */
    private String fqr;

    /**
     * 当事人文书类别
     */
    List<String> dsrwslbArray;

    public String getFqr() {
        return fqr;
    }

    public void setFqr(String fqr) {
        this.fqr = fqr;
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

    public String getYylqsjStr() {
        return yylqsjStr;
    }

    public void setYylqsjStr(String yylqsjStr) {
        this.yylqsjStr = yylqsjStr;
    }

    public List<String> getDsrwslbArray() {
        return dsrwslbArray;
    }

    public void setDsrwslbArray(List<String> dsrwslbArray) {
        this.dsrwslbArray = dsrwslbArray;
    }
}
