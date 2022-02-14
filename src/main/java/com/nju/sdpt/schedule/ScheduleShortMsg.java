package com.nju.sdpt.schedule;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.service.DxtzService;
import com.nju.sdpt.service.ScheduleShortMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 短信通知相关定时任务
 */
@Component
@Slf4j
public class ScheduleShortMsg {

    @Autowired
    ScheduleShortMsgService scheduleShortMsgService;

    @Autowired
    DxtzService dxtzService;

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    /**
     * 获取短链接访问状态
     */
    @Scheduled(cron = "0 30 7-20 * * ?")
    public void getShortUrlStatus() {
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        dxtzService.HandlerLinkStatus();
    }

    /**
     * 获取短信发送状态
     */
    @Scheduled(cron = "0 30 7-20 * * ?")
    public void getShortStatus() {
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        dxtzService.HandlerMsgStatus();
    }

}
