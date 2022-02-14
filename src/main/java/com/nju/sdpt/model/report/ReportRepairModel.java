package com.nju.sdpt.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 累计统计响应类
 */
@Data
public class ReportRepairModel {

    @ApiModelProperty("法院名称")
    private String fymc;

    private String fybh;

    @ApiModelProperty("提交修复次数")
    private Integer repairNum;

    @ApiModelProperty("修复成功次数")
    private Integer repairSucNum;

    @ApiModelProperty("修复接听数量")
    private Integer repairJtNum;

    @ApiModelProperty("有效本人标记数量")
    private Integer repairBrNum;

}
