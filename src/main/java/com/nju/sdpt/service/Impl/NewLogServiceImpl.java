package com.nju.sdpt.service.Impl;

import com.alibaba.fastjson.JSONObject;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.DmbEntity;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.model.NewLogModel;
import com.nju.sdpt.service.NewLogService;
import com.nju.sdpt.util.HttpUtil;
import com.nju.sdpt.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class NewLogServiceImpl implements NewLogService {

    private final Logger logger = LoggerFactory.getLogger(NewLogServiceImpl.class);

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    @Autowired
    private DmbMapper dmbMapper;
    @Override
    @Async
    public void addNewModel(NewLogModel newLogModel) {
        if(StringUtil.isBlank(newLogModel.getFydm())){
            DynamicDataSource.router(SDPT_FYDM);
        }else {
            DynamicDataSource.routerByFybh(newLogModel.getFydm());
            DmbEntity dmb = dmbMapper.getDmByLbbhAndDmbh("系统缺省", "法院代码");
            String fydm = dmb.getDmms();
            newLogModel.setFydm(fydm);
        }
        String jsonObject = JSONObject.toJSONString(newLogModel);
        String targetUrl = "http://130.39.85.8:12000/log";
        String result = " ";
        try {
            result = HttpUtil.sendHttpPost(jsonObject, targetUrl);
        } catch (IOException e) {
            logger.info("调用日志平台失败");
            e.printStackTrace();
        }
    }
}
