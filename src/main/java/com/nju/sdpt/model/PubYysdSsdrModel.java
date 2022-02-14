package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubSsdrHmEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.entity.PubYysdSsdrdzEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PubYysdSsdrModel extends PubYysdSsdrEntity {

    /**
     * 受送达人号码集合
     */
    List<PubSsdrHmEntity> pubSsdrHmEntityList;

    /**
     * 受送达人地址集合
     */
    List<PubYysdSsdrdzEntity> pubYysdSsdrdzEntityList;

}
