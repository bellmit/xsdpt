package com.nju.sdpt.service;

import com.nju.sdpt.model.YysdModel;

import java.util.List;

public interface GdfkService {

    List<YysdModel> getUnFeedbackRecord(String start, String end, String fybh);

    List<YysdModel> getFeedbackRecord(String start, String end, String fybh);
}
