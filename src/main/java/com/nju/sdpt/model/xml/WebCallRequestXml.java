package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 发起外呼请求类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WebCallRequestXml")
@XmlType(propOrder = {
        "callId",
        "batchId",
        "sdpIdCard",
        "operatorType",
        "repairNumber",
        "repairBatch",
        "seatNumber",
        "seatId",
        "showNumber"
})
public class WebCallRequestXml {

    /**
     * 内网电话记录标识
     */
    private Integer callId;

    /**
     * 请求修复时返回的批次号
     */
    private String batchId;

    /**
     * 当事人身份证密文 SHA256
     */
    private String sdpIdCard;

    /**
     * 号码操作类型
     */
    private String operatorType;

    /**
     * 号码密码
     */
    private String repairNumber;

    /**
     * 修复号码批次
     */
    private String repairBatch;

    /**
     * 送达专员电话号码
     */
    private String seatNumber;

    /**
     * 线路
     */
    private String seatId;

    /**
     * 外显号码
     */
    private String showNumber;

    public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
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

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getRepairNumber() {
        return repairNumber;
    }

    public void setRepairNumber(String repairNumber) {
        this.repairNumber = repairNumber;
    }

    public String getRepairBatch() {
        return repairBatch;
    }

    public void setRepairBatch(String repairBatch) {
        this.repairBatch = repairBatch;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }
}
