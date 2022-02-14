package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubSddjJbEntity;

import java.util.List;

/**
 * 送达登记Service层
 */
public interface SdSddjService {
    List<PubSddjJbEntity> getPubSddjJbEntityListByAjxh(Integer ajxh);
}
