package com.nju.sdpt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/11/8 10:53
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DXFWINFOS")
@XmlType(propOrder = {
        "DXFWINFO"
})
public class DxlinkStatuses {
    private List<DxlinkStatus> DXFWINFO;

    public DxlinkStatuses() {
    }

    public DxlinkStatuses(List<DxlinkStatus> DXFWINFO) {
        this.DXFWINFO = DXFWINFO;
    }

    public List<DxlinkStatus> getDXFWINFO() {
        return DXFWINFO;
    }

    public void setDXFWINFO(List<DxlinkStatus> DXFWINFO) {
        this.DXFWINFO = DXFWINFO;
    }
}
