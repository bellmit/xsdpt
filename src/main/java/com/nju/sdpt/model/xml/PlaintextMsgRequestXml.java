package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PlaintextMsgRequestXml")
@XmlType(propOrder = {
        "mwdxId",
        "uuid",
        "yysdbh",
        "targetTel",
        "msgContent",
        "urlList"
})
public class PlaintextMsgRequestXml {
    /**
     * 短信通知ID 即内网短信通知记录主键
     */
    private Integer mwdxId;
    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;

    /**
     * 实际接收号码(如果是运营商修复号码该字段为运营商给的加密串,明文就直接为明文手机号)
     */
    private String targetTel;

    /**
     * 短信内容 (填充完成后完整的短信内容)
     */
    private String msgContent;
    /**
     * 路径集合
     */
    private List<String> urlList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public Integer getMwdxId() {
        return mwdxId;
    }

    public void setMwdxId(Integer mwdxId) {
        this.mwdxId = mwdxId;
    }

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getTargetTel() {
        return targetTel;
    }

    public void setTargetTel(String targetTel) {
        this.targetTel = targetTel;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

}
