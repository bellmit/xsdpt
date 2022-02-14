package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.mapper.AjJbMapper;
import com.nju.sdpt.mapper.SpryMapper;
import com.nju.sdpt.service.SpryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpryServiceImpl implements SpryService {
    @Autowired
    SpryMapper spryMapper;
    @Autowired
    AjJbMapper ajJbMapper;

    @Override
    public String getAuditorByAjxh(Integer ajxh) {
        AjJbEntity ajJbEntity = ajJbMapper.findById(ajxh);
        String cbr = spryMapper.getCbrByajxh(ajxh);
        return cbr==null?ajJbEntity.getLar():cbr;
    }

    @Override
    public String getCbrByAjxh(Integer ajxh) {
        return spryMapper.getCbrByajxh(ajxh);
    }

    @Override
    public String getSjyByAjxh(Integer ajxh, String fybh) {
        return spryMapper.getSjyByAjxh(ajxh);
    }
}
