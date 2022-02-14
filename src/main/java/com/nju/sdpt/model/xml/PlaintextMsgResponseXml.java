package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PlaintextMsgResponseXml")
@XmlType(propOrder = {
        "mwdxId",
        "yysdbh",
        "smsId",
        "submitSuc",
        "msgReceipt",
        "msgContent"
})
public class PlaintextMsgResponseXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 短信通知ID 即内网短信通知记录主键
     */
    private Integer mwdxId;

    /**
     * 短信记录流水号
     */
    private String smsId;

    /**
     * 是否请求成功
     */
    private Boolean submitSuc;

    /**
     * 接口响应回执
     */
    private String msgReceipt;
    /**
     * 短信内容
     */
    private String msgContent;

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public Integer getMwdxId() {
        return mwdxId;
    }

    public void setMwdxId(Integer mwdxId) {
        this.mwdxId = mwdxId;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public Boolean getSubmitSuc() {
        return submitSuc;
    }

    public void setSubmitSuc(Boolean submitSuc) {
        this.submitSuc = submitSuc;
    }

    public String getMsgReceipt() {
        return msgReceipt;
    }

    public void setMsgReceipt(String msgReceipt) {
        this.msgReceipt = msgReceipt;
    }
}
