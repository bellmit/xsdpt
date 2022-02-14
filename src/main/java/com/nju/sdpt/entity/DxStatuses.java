package com.nju.sdpt.entity;

import com.nju.sdpt.viewobject.DxsdInfoVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/11/10 11:08
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DXZTINFOS")
@XmlType(propOrder = {
        "DXZTINFO"
})
public class DxStatuses {
    private List<DxStatus> DXZTINFO;

    public DxStatuses() {
    }

    public DxStatuses(List<DxStatus> DXZTINFO) {
        this.DXZTINFO = DXZTINFO;
    }

    public List<DxStatus> getDXZTINFO() {
        return DXZTINFO;
    }

    public void setDXZTINFO(List<DxStatus> DXZTINFO) {
        this.DXZTINFO = DXZTINFO;
    }
}
