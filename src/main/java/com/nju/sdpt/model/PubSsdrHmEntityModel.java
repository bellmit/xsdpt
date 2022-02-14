package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubSsdrHmEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PubSsdrHmEntityModel extends PubSsdrHmEntity {

    @ApiModelProperty("最新下发状态")
    private Integer newSendState;

    @ApiModelProperty("最新访问状态")
    private Integer newFwzt;

}
