package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ShortUrlStatusXml")
@XmlType(propOrder = {
        "dxid",
        "status",
})
public class ShortUrlStatusXml {
    /**
     * 短信通知ID 即内网短信通知记录主键
     */
    private String dxid;

    /**
     * 流水号
     */
    private Integer status;

    public String getDxid() {
        return dxid;
    }

    public void setDxid(String dxid) {
        this.dxid = dxid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
