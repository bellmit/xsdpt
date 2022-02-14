package com.nju.sdpt.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PlaintextQueryMsgStatusRequestXml")
@XmlType(propOrder = {
        "cusid"
})
public class PlaintextQueryMsgStatusRequestXml {

    /**
     * 实际接收号码(如果是运营商修复号码该字段为运营商给的加密串,明文就直接为明文手机号)
     */
    private String cusid;

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }
}
