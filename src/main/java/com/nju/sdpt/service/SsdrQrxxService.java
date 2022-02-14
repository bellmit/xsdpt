package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubSsdrQrxxEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.viewobject.PubSsdrQrxxEntityVO;

public interface SsdrQrxxService {

    void save(Integer ssdrbh, Integer ajxh, String fybh);

    PubSsdrQrxxEntity findByAjxhAndFybhAndDsrbh(Integer ajxh, String fybh, Integer ssdrbh);

    void qsqrsUpdate(PubSsdrQrxxEntityVO pubSsdrQrxxEntityVO);

    void tydzsdUpdate(PubSsdrQrxxEntityVO pubSsdrQrxxEntityVO);
}
