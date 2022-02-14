package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 短信查询下发状态请求XML
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ShortMsgQueryStatusRequestXml")
@XmlType(propOrder = {
        "serialNumber",
        "operatorType",
        "telBatchNo",
        "targetTel",
        "sdpIdCard"
})
public class ShortMsgQueryStatusRequestXml {

    /**
     * 短信记录流水号
     */
    private String serialNumber;

    /**
     * 操作类型
     */
    private String operatorType;

    /**
     * 号码批次  - 运营商修复号码类型使用
     */
    private String telBatchNo;

    /**
     * 号码密码(修复下发短信使用)
     */
    private String targetTel;

    /**
     * 加密身份证(修复下发短信使用)
     */
    private String sdpIdCard;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getTelBatchNo() {
        return telBatchNo;
    }

    public void setTelBatchNo(String telBatchNo) {
        this.telBatchNo = telBatchNo;
    }

    public String getTargetTel() {
        return targetTel;
    }

    public void setTargetTel(String targetTel) {
        this.targetTel = targetTel;
    }

    public String getSdpIdCard() {
        return sdpIdCard;
    }

    public void setSdpIdCard(String sdpIdCard) {
        this.sdpIdCard = sdpIdCard;
    }
}
