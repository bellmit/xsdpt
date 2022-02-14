package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @Author: Diao Lin
 * @Date: 2019/11/28 9:16
 * @Description: [请求] 内网数据交换 短信下发请求类
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ShortMsgRequestXml")
@XmlType(propOrder = {
        "yysdbh",
        "dxtzId",
        "targetTel",
        "telBatchNo",
        "operatorType",
        "templateNo",
        "msgContent",
        "paramJson",
        "sdpIdCard",
        "templateValue",
        "urlList"
})
public class ShortMsgRequestXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 短信通知ID 即内网短信通知记录主键
     */
    private Integer dxtzId;

    /**
     * 实际接收号码(如果是运营商修复号码该字段为运营商给的加密串,明文就直接为明文手机号)
     */
    private String targetTel;

    /**
     * 号码批次  - 运营商修复号码类型使用
     */
    private String telBatchNo;

    /**
     * 号码类型  - (Entry:明文  CUCC/WCCMCC/QXB 其他运营商类型)
     */
    private String operatorType;

    /**
     * 短信模板 话术语
     */
    private String templateNo;

    /**
     * 短信内容 (填充完成后完整的短信内容)
     */
    private String msgContent;

    /**
     * 发送参数(字符串数组JSON)   例 :  ["参数1","参数2","参数3"...]  最多支持5个参数
     */
    private String paramJson;

    /**
     * 加密身份证
     */
    private String sdpIdCard;
    /**
     * 路径集合
     */
    private List<String> urlList;

    /**
     * 明文闪信参数  3个
     * 第一个参数：法院简称
     * 第二个参数：联系人
     * 第三个参数：联系电话
     */
    private List<String> templateValue;

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public String getSdpIdCard() {
        return sdpIdCard;
    }

    public void setSdpIdCard(String sdpIdCard) {
        this.sdpIdCard = sdpIdCard;
    }

    public List<String> getTemplateValue() {
        return templateValue;
    }

    public void setTemplateValue(List<String> templateValue) {
        this.templateValue = templateValue;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getDxtzId() {
        return dxtzId;
    }

    public void setDxtzId(Integer dxtzId) {
        this.dxtzId = dxtzId;
    }

    public String getTargetTel() {
        return targetTel;
    }

    public void setTargetTel(String targetTel) {
        this.targetTel = targetTel;
    }

    public String getTelBatchNo() {
        return telBatchNo;
    }

    public void setTelBatchNo(String telBatchNo) {
        this.telBatchNo = telBatchNo;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }
}
