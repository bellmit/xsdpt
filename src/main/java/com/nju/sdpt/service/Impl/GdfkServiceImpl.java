package com.nju.sdpt.service.Impl;

import com.nju.sdpt.mapper.PubYysdJbEntityMapper;
import com.nju.sdpt.model.YysdModel;
import com.nju.sdpt.service.GdfkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GdfkServiceImpl implements GdfkService {

    @Resource
    PubYysdJbEntityMapper pubYysdJbEntityMapper;

    @Override
    public List<YysdModel> getUnFeedbackRecord(String start, String end, String fybh) {
        return pubYysdJbEntityMapper.getUnFeedbackRecord(start,end,fybh);
    }

    @Override
    public List<YysdModel> getFeedbackRecord(String start, String end, String fybh) {
        return pubYysdJbEntityMapper.getFeedbackRecord(start,end,fybh);
    }

}
