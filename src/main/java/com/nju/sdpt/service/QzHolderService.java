package com.nju.sdpt.service;
public interface QzHolderService {

    SNPServiceV2 getQzServiceIfOpen();

    void reset();

}
