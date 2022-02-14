package com.nju.sdpt.schedule;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.service.ScheduleWebCallService;
import com.nju.sdpt.service.WebcallSeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 外呼相关定时任务
 */
@Component
@Slf4j
public class ScheduleWebCall {

    @Autowired
    ScheduleWebCallService scheduleWebCallService;
    @Autowired
    WebcallSeatService webcallSeatService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    /**
     * 7-20时 每个半点都会处理从外网回来的信息
     */
    @Scheduled(cron="0 30 7-20  * * ?")
    public void handPhoneCallResponseXML(){
        log.error("开始处理电话信息");
        DynamicDataSource.router(SDPT_FYDM);
        scheduleWebCallService.handPhoneCallResponseXML();
    }

    /**
     * 7-20时 每个半点都会处理从外网回来的录音
     */
    @Scheduled(cron="0 30 7-20  * * ?")
    public void handleWebCallRecord(){
        DynamicDataSource.router(SDPT_FYDM);
        scheduleWebCallService.handleWebCallRecord();
    }

}
