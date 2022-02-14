package com.nju.sdpt.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 累计统计响应类
 */
@Data
public class ReportLjModel {

    @ApiModelProperty("法院名称")
    private String fymc;

    private String fybh;

    private String businessName;

    @ApiModelProperty("当日推送案件量")
    private Integer drtsajNum;

    @ApiModelProperty("累计推送案件量")
    private Integer tsajNum;

    @ApiModelProperty("当日推送涉及人数")
    private Integer drtssjrsNum;

    @ApiModelProperty("累计推送涉及人数")
    private Integer tssjrsNum;

    @ApiModelProperty("累计撤回案件量")
    private Integer chajNum;

    @ApiModelProperty("累计撤回涉及人数")
    private Integer chsjrsNum;

    @ApiModelProperty("累计反馈送达成功的工单量")
    private Integer fkcgNum;

    @ApiModelProperty("累计反馈送达失败的工单量")
    private Integer fksbNum;

}
