package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubQtsscyrEntity;
import com.nju.sdpt.entity.PubYysdSsdrQtsscyrEntity;


import java.util.List;

public interface QtsscyrService {
    List<PubQtsscyrEntity> getQtsscyr(Integer ajxh, Integer dsrbh);

    List<PubYysdSsdrQtsscyrEntity> getpubYysdSsdrQtsscyrEntitiesByYysdbh(Integer yysdbh);
}
