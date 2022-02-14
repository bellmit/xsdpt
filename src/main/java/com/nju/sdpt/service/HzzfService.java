package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntityKey;
import com.nju.sdpt.model.PubWebCallInfoModel;
import com.nju.sdpt.model.RetObj;
import com.nju.sdpt.viewobject.SubmitRepairVo;
import com.nju.sdpt.viewobject.SubmitWebCallVo;
import com.nju.sdpt.viewobject.WebCallVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: Diao Lin
 * @Date: 2019/12/3 16:02
 * @Description:
 */
public interface HzzfService {
    /**
     * 工单提交修复
     * @param submitRepairVo
     * @param yhm
     * @return
     */
    RetObj submitRepair(SubmitRepairVo submitRepairVo, String yhm);


    /**
     * 工单提交修复 对外接口
     * * @param submitRepairVo
     * @param yhm
     * @return
     */
    RetObj submitRepair(SubmitRepairVo submitRepairVo, PubYysdSsdrEntity record, String yhm);
    /**
     * 发起外呼
     * @param webCallVo
     */
    RetObj webCall(WebCallVo webCallVo);


    void submitWebCall(SubmitWebCallVo webCallVo);

    /**
     * 下载录音文件
     * @param webCallId
     * @param request
     * @param response
     */
    void download_call_radio(Integer webCallId, HttpServletRequest request, HttpServletResponse response);

    List<PubWebCallInfoModel> web_call_list(Integer yhbh);
    List<PubWebCallInfoModel> web_call_list(Integer yhbh,String start,String end);
}
