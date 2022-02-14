package com.nju.sdpt.model.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AddNewAddressRequestXml")
@XmlType(propOrder = {
        "yysdbh",
        "ssdrbh",
        "address"
})
@Data
public class AddNewAddressRequestXml {

    /**
     * 预约送达编号  即工单编号
     */
    private Integer yysdbh;
    /**
     * 受送达人编号
     */
    private String ssdrbh;
    /**
     * 新增地址集合
     */
    private String address;

    public Integer getYysdbh() {
        return yysdbh;
    }

    public void setYysdbh(Integer yysdbh) {
        this.yysdbh = yysdbh;
    }

    public String getSsdrbh() {
        return ssdrbh;
    }

    public void setSsdrbh(String ssdrbh) {
        this.ssdrbh = ssdrbh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
