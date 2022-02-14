package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换查询通话记录请求类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WebCallRecordRequestXml")
@XmlType(propOrder = {
        "callId",
        "telBatchNo",
        "operatorTel",
        "sdpIdCard",
        "operatorType",
        "uuid"
})
public class WebCallRecordRequestXml {

    /**
     * 内网电话记录标识
     */
    private Integer callId;

    /**
     * 号码批次 (修复外呼使用)
     */
    private String telBatchNo;

    /**
     * 呼叫号码暗码（修复外呼使用）
     */
    private String operatorTel;

    /**
     * 当事人身份证密文 SHA256
     */
    private String sdpIdCard;

    /**
     * 号码操作类型
     */
    private String operatorType;


    /**
     * 通话唯一标识
     */
    private String uuid;

    public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public String getTelBatchNo() {
        return telBatchNo;
    }

    public void setTelBatchNo(String telBatchNo) {
        this.telBatchNo = telBatchNo;
    }

    public String getOperatorTel() {
        return operatorTel;
    }

    public void setOperatorTel(String operatorTel) {
        this.operatorTel = operatorTel;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
