package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 查询修复结果请求类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CallRepairQueryResultRequestXml")
@XmlType(propOrder = {
        "yysdbh",
        "ssdrbh",
        "batchId",
        "sdpIdCard",
})
@Data
public class CallRepairQueryResultRequestXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 受送达人编号
     */
    private Integer ssdrbh;

    /**
     * 请求修复时返回的批次号
     */
    private String batchId;

    /**
     * 当事人身份证密文 SHA256
     */
    private String sdpIdCard;

    public Integer getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(Integer ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSdpIdCard() {
        return sdpIdCard;
    }

    public void setSdpIdCard(String sdpIdCard) {
        this.sdpIdCard = sdpIdCard;
    }
}
