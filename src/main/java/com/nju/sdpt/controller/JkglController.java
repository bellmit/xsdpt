package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.AjjbxxService;
import com.nju.sdpt.service.CaseInfoService;
import com.nju.sdpt.service.DsrjbService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api("其他接口管理")
@RestController
public class JkglController {

    @Autowired
    AjjbxxService ajjbxxService;

    @Autowired
    DsrjbService dsrjbService;

    @Autowired
    CaseInfoService caseInfoService;

    @PostMapping("/caseInfo.do")
    public CaseInfoResponse getCaseInfo(@RequestBody CaseInfoRequestParam caseInfoRequestParam) {



        // todo 填充caseInfoData数据
        Integer ajxh = Integer.parseInt(caseInfoRequestParam.getAjbh());
        Integer ajlb = Integer.parseInt(caseInfoRequestParam.getAjlb());
        String fybh = caseInfoRequestParam.getAjlb();

        return caseInfoService.getCaseInfo(ajxh, ajlb, fybh);

    }

    @PostMapping("/dwjk/api/znsdgjcb/sendMessageCallBack")
    public RetObj sendMessageCallBack(@RequestBody SdhzDataList sdhzDataList, HttpServletRequest request) {
        // todo 对接收的sdhzDataList进行操作


        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;

    }

}
