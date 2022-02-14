package com.nju.sdpt.service;

import com.nju.sdpt.entity.XtglCygjbEntity;

public interface CygjService {
    /**
     * 根据构件名称查找构件
     */
    XtglCygjbEntity getCygjByName(String name);
}
