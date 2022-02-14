package com.nju.sdpt.service.Impl;


import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.mapper.AjJbMapper;
import com.nju.sdpt.model.AjjbxxModel;
import com.nju.sdpt.model.CaseInfoAjxx;
import com.nju.sdpt.model.CaseInfoData;
import com.nju.sdpt.model.CaseInfoResponse;
import com.nju.sdpt.service.AjjbxxService;
import com.nju.sdpt.service.CaseInfoService;
import com.nju.sdpt.service.DsrjbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseInfoServiceImpl implements CaseInfoService {


    @Autowired
    AjJbMapper ajJbMapper;


    @Override
    public CaseInfoResponse getCaseInfo(Integer ajxh, Integer ajlb, String fybh) {

        CaseInfoResponse caseInfoResponse = new CaseInfoResponse();
        caseInfoResponse.setMessage("调用接口成功");
        caseInfoResponse.setCode("1");
        caseInfoResponse.setSuccess("true");
        CaseInfoData caseInfoData = new CaseInfoData();

        // 案件信息
        AjJbEntity ajjbDO = ajJbMapper.findById((int) ajxh);
        List<CaseInfoAjxx> ajxx = caseInfoData.getAjxx();
        CaseInfoAjxx caseInfoAjxx = new CaseInfoAjxx();
        caseInfoAjxx.setCAh(ajjbDO.getAh());
        caseInfoAjxx.setCAjmc(ajjbDO.getAjmc());
        caseInfoAjxx.setCBh(String.valueOf(ajjbDO.getAjxh()));
        caseInfoAjxx.setNAjjzjd("");
        caseInfoAjxx.setNAjlb("");
        caseInfoAjxx.setNCbspt(ajjbDO.getBaspt());
        caseInfoAjxx.setNJbfy(fybh);
        caseInfoAjxx.setNSpcx("");
        caseInfoAjxx.setCKtdd("");
        caseInfoAjxx.setDtKtsj("");

        ajxx.add(caseInfoAjxx);
        caseInfoData.setAjxx(ajxx);

        // 文书信息

        // 当事人信息


        caseInfoResponse.setData(caseInfoData);
        return caseInfoResponse;
    }
}
