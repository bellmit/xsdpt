package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/10/29 14:28
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DHSDINFOS")
@XmlType(propOrder = {
        "DHSDINFO"
})
public class DhsdHjmxs {

    private List<DhsdHjmx> DHSDINFO;

    public DhsdHjmxs() {
    }

    public DhsdHjmxs(List<DhsdHjmx> DHSDINFO) {
        this.DHSDINFO = DHSDINFO;
    }

    public List<DhsdHjmx> getDHSDINFO() {
        return DHSDINFO;
    }

    public void setDHSDINFO(List<DhsdHjmx> DHSDINFO) {
        this.DHSDINFO = DHSDINFO;
    }
}
