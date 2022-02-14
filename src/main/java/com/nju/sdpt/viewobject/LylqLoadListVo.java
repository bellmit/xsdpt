package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubLylqInfoEntity;

/**
 * 来院领取 - 查询列表 - VO
 */
public class LylqLoadListVo extends PubLylqInfoEntity {

    //工单承办人员标识
    private String gdryxm;

    public String getGdryxm() {
        return gdryxm;
    }

    public void setGdryxm(String gdryxm) {
        this.gdryxm = gdryxm;
    }
}
