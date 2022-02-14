package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jiaweiq
 * @date 2021/11/4 11:49
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sdgcjlList")
@XmlType(propOrder = {
        "tp"
})
public class TPModel {

    private String tp;

    public TPModel() {
    }

    public TPModel(String tp) {
        this.tp = tp;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
}
