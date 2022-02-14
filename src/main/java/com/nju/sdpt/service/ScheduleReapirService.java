package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubSsdrHmEntity;
import com.nju.sdpt.entity.PubZjsdInfoEntity;

public interface ScheduleReapirService {

    void handleCallRepairResponseXML();

    void handleCallRepairQueryResultRequestXML();

    void handleCallRepairQueryResultResponseXML();

    void addZjsdDataResponseXML();

    Integer savePubSsdrHmEntity(PubSsdrHmEntity pubSsdrHmEntity);
    Integer savePubZjsdInfoEntity(PubZjsdInfoEntity pubZjsdInfoEntity);
}
