package com.nju.sdpt.viewobject.openApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基本信息数据类
 */
@Data
public class DataPushObj{
    /**
     * 号码类型  1.（个人）短信告知号码 2.（单位）短信告知号码 3.（机关）短信告知号码 4.单位联系电话
     * 地址类型  1.工作单位 2.经常居住地 3.户籍所在地 4.个人送达地址 5.单位地址 6.单位送达地址 7.机关地址 8.机关送达地址
     */
    @ApiModelProperty("数据类型")
    private Integer type;

    @ApiModelProperty("数据内容")
    private String content;
}
