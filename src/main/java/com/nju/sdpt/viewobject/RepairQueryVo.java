package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubDxtzInfoEntity;
import com.nju.sdpt.entity.PubRepairInfoEntity;
import lombok.Data;

/**
 * 修复记录 - 查询VO
 */
@Data
public class RepairQueryVo extends PubRepairInfoEntity {

    //工单承办人员标识
    private String gdryxm;

    public String getGdryxm() {
        return gdryxm;
    }

    public void setGdryxm(String gdryxm) {
        this.gdryxm = gdryxm;
    }
}
