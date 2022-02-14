package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubDxtzInfoEntity;

/**
 * 来院领取 - 查询列表 - VO
 */
public class DxtzLoadListVo extends PubDxtzInfoEntity {

    //工单承办人员标识
    private String gdryxm;

    public String getGdryxm() {
        return gdryxm;
    }

    public void setGdryxm(String gdryxm) {
        this.gdryxm = gdryxm;
    }


}
