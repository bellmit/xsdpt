package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.UserContextModel;
import com.nju.sdpt.model.YhqxModel;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.XtyhService;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 基类控制层 用于存放一些共用属性或方法
 */
@Component
public class BaseController {
    @Resource
    private GdRyxxService gdRyxxService;
    @Resource
    private XtyhService xtyhService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    @Resource
    protected HttpServletRequest request;



    protected String getLoginYhm(){
       return gdRyxxService.getYhdm(request);
    }


    /**
     * 根据权限获取当前登陆工单人员的可查看的法院编号
     * @return
     */
    protected List<String> getLoginYhmFybhSet(){
        List<String> fybhList = new ArrayList<>();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity byYhdm = gdRyxxService.findByYhdm(getLoginYhm());
        fybhList.add("-1");
        if(null != byYhdm){
            String yhjs = byYhdm.getYhjs();
            if(Objects.equals("admin",yhjs)){
                fybhList.add(byYhdm.getFybh());
            }
            if(Objects.equals("-1",byYhdm.getFybh())){ //处理全市法院权限
                fybhList.clear();
                for(FYEnum fyEnum:FYEnum.values()){
                    fybhList.add(fyEnum.getFybh());
                }
            }
        }
        return fybhList;
    }

    protected ReportQueryVo loadReportQueryVoOffg(ReportQueryVo reportQueryVo){

        List<String> fybhList = reportQueryVo.getFybhSet();
        if(CollectionUtils.isEmpty(fybhList)){
            fybhList = new ArrayList<>();
            fybhList.add("-1");
        }
        HttpSession session = request.getSession();
        String report_fybh = (String) session.getAttribute("report_fybh");
        if(StringUtil.isNotBlank(report_fybh)){
            fybhList.add(report_fybh);
            reportQueryVo.setSdptRy(false);
        }else {
            UserContextModel userContextModel = (UserContextModel)session.getAttribute("userContext");
            if(null != userContextModel && Objects.equals(userContextModel.getYhjs(),"fg")) {
                YhqxModel yhqxByYhm = xtyhService.getYhqxByYhm(userContextModel.getYhdm(), userContextModel.getFybh());
                Integer authority = yhqxByYhm.getAuthority(); //权限等级
                fybhList.add(yhqxByYhm.getFybh());
                reportQueryVo.setAuthority(authority);
                reportQueryVo.setFybhSet(fybhList);
                reportQueryVo.setBmbh(yhqxByYhm.getBmbh());
                reportQueryVo.setFgmc(yhqxByYhm.getYhmc());
                reportQueryVo.setSdptRy(false);
            }
        }

        return reportQueryVo;
    }
}
