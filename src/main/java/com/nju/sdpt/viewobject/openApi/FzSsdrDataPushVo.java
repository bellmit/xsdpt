package com.nju.sdpt.viewobject.openApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 法综端当事人基本数据推送请求类
 */
@Data
public class FzSsdrDataPushVo {

    @ApiModelProperty("法院编号")
    private String fybh;

    @ApiModelProperty("案件序号")
    private Integer ajxh;

    @ApiModelProperty("当事人编号")
    private Integer ssdrbh;

    @ApiModelProperty("当事人姓名")
    private String ssdrmc;

    @ApiModelProperty("当事人诉讼地位")
    private String dsrssdw;

    @ApiModelProperty("当事人身份证号码")
    private String ssdrsfzhm;

    @ApiModelProperty("号码集合")
    private List<DataPushObj> telList;

    @ApiModelProperty("地址集合")
    private List<DataPushObj> addressList;


}
