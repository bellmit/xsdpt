package com.nju.sdpt.service;

public interface ScheduleShortMsgService {

    void handleShortMsgResponseXML();

    void handleShortMsgQueryStatusRequestXML();

    void handleShortMsgQueryStatusResponseXML();

    void handlePlaintextMsgResponseXML();

    void handlePlaintextMsgQueryStatusRequestXML();

    void handlePlaintextMsgQueryStatusResponseXML();

    void getShortUrlStatus();

    void getPlaintextShortUrlStatus();

    void handleShortMsgSxQueryStatusRequestXML();

    void sendMzsjtj();
}
