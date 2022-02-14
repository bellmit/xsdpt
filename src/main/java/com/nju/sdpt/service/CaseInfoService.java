package com.nju.sdpt.service;

import com.nju.sdpt.model.CaseInfoResponse;

public interface CaseInfoService {

    CaseInfoResponse getCaseInfo(Integer ajxh,Integer ajlb,String fybh);

}
