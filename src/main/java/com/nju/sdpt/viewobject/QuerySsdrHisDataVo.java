package com.nju.sdpt.viewobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuerySsdrHisDataVo {

    @ApiModelProperty("受送达人名称")
    private String ssdrmc;

    @ApiModelProperty("受送达人身份证号码")
    private String sfzhm;

    @ApiModelProperty("当前工单号")
    private Integer yysdbh;
}
