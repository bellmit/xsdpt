package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jiaweiq
 * @date 2021/11/4 11:46
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hzbList")
@XmlType(propOrder = {
        "hz"
})
public class HZModel {
    private String hz;

    public HZModel() {
    }

    public HZModel(String hz) {
        this.hz = hz;
    }

    public String getHz() {
        return hz;
    }

    public void setHz(String hz) {
        this.hz = hz;
    }
}
