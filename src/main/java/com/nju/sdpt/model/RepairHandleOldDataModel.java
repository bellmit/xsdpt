package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubRepairInfoEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.entity.PubYysdSsdrdzEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 修复记录model
 */
@Data
public class RepairHandleOldDataModel extends PubYysdSsdrEntity {

    @ApiModelProperty("工单预约时间，维护历史数据使用")
    private Date gdyysj;

    @ApiModelProperty("案号")
    private String ah;
}
