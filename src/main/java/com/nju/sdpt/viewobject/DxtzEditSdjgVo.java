package com.nju.sdpt.viewobject;

import java.util.List;

/**
 * 来院领取 - 编辑送达结果 - VO
 */
public class DxtzEditSdjgVo {

    /**
     * 短信记录 - 唯一标识
     */
    private Integer dxtzId;


    /**
     * 短信记录 - 唯一标识集合
     */
    private String dxtzIds;

    public String getDxtzIds() {
        return dxtzIds;
    }

    public void setDxtzIds(String dxtzIds) {
        this.dxtzIds = dxtzIds;
    }

    /**
     * 送达结果 0: 待送达  1：送达成功  2：送达失败  默认0)
     */
    private Integer sdjg;

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public Integer getDxtzId() {
        return dxtzId;
    }

    public void setDxtzId(Integer dxtzId) {
        this.dxtzId = dxtzId;
    }

}
