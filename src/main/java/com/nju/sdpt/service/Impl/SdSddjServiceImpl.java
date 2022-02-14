package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubSddjJbEntity;
import com.nju.sdpt.mapper.SdSddjMapper;
import com.nju.sdpt.service.SdSddjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SdSddjServiceImpl implements SdSddjService {
    @Autowired
    SdSddjMapper sdSddjMapper;
    @Override
    public List<PubSddjJbEntity> getPubSddjJbEntityListByAjxh(Integer ajxh) {
        return sdSddjMapper.getPubSddjJbEntityListByAjxh(ajxh);
    }
}
