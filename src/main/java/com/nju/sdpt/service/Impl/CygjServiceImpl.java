package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.XtglCygjbEntity;
import com.nju.sdpt.mapper.XtglCygjMapper;
import com.nju.sdpt.service.CygjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CygjServiceImpl implements CygjService {
    @Autowired
    XtglCygjMapper xtglCygjMapper;
    @Override
    public XtglCygjbEntity getCygjByName(String name) {
            return xtglCygjMapper.getCygjByGjmc(name);
    }
}
