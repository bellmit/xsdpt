package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 提交修复响应类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CallRepairResponseXml")
@XmlType(propOrder = {
        "yysdbh",
        "ssdrbh",
        "batchId"
})
@Data
public class CallRepairResponseXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 受送达人编号
     */
    private Integer ssdrbh;


    /**
     * 本次提交修复的批次号
     */
    private String batchId;

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
