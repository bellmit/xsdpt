package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubZgysdInfoEntity;

public interface ZgysdService {
    String insert(PubYysdJbEntity pubYysdJbEntity);

    PubYysdJbEntity getYysdByAjid(String logbh);

    Integer insert(PubZgysdInfoEntity pubZgysdInfoEntity);

    String insertForFz(int fybh,int ajxh);
}
