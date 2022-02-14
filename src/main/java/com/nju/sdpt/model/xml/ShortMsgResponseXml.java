package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
        "msgContent",
        "serialNumber",
        "submitSuc",
        "msgReceipt",
        "flashLetterMsgReceipt",
        "flashLetterMsgSend"
})
public class ShortMsgResponseXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 短信通知ID 即内网短信通知记录主键
     */
    private Integer dxtzId;
    /**
     * 短信内容
     */
    private String msgContent;

    /**
     * 短信记录流水号
     */
    private String serialNumber;

    /**
     * 是否请求成功
     */
    private Boolean submitSuc;

    /**
     * 接口响应回执
     */
    private String msgReceipt;

    /**
     *  闪信 接口响应回执
     */
    private String flashLetterMsgReceipt;

    /**
     * 闪信是否发送成功
     */
    private Boolean flashLetterMsgSend;

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

    public Integer getDxtzId() {
        return dxtzId;
    }

    public void setDxtzId(Integer dxtzId) {
        this.dxtzId = dxtzId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getFlashLetterMsgReceipt() {
        return flashLetterMsgReceipt;
    }

    public void setFlashLetterMsgReceipt(String flashLetterMsgReceipt) {
        this.flashLetterMsgReceipt = flashLetterMsgReceipt;
    }

    public Boolean getFlashLetterMsgSend() {
        return flashLetterMsgSend;
    }

    public void setFlashLetterMsgSend(Boolean flashLetterMsgSend) {
        this.flashLetterMsgSend = flashLetterMsgSend;
    }
}
