package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PlaintextMsgQueryStatusRequestXml")
@XmlType(propOrder = {
        "mwdxId",
        "lsh",
})
public class PlaintextMsgQueryStatusRequestXml {
    /**
     * 短信通知ID 即内网短信通知记录主键
     */
    private Integer mwdxId;

    /**
     * 流水号
     */
    private String lsh;

    public Integer getMwdxId() {
        return mwdxId;
    }

    public void setMwdxId(Integer mwdxId) {
        this.mwdxId = mwdxId;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }
}
