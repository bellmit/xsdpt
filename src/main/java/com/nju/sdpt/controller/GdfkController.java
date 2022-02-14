package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.model.DistributeYysdModel;
import com.nju.sdpt.model.UserContextModel;
import com.nju.sdpt.model.YysdModel;
import com.nju.sdpt.service.*;
import com.nju.sdpt.viewobject.MwdxSendVo;
import com.nju.sdpt.viewobject.Result;
import com.nju.sdpt.viewobject.RwlzVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GdfkController {

    @Resource
    YysdService yysdService;

    @Resource
    GdRyxxService gdRyxxService;

    @Resource
    LogService logService;

    @Resource
    DxtzService dxtzService;

    @Resource
    GdfkService gdfkService;

    @Resource
    RwlzService rwlzService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;


    /**
     * 获取已分配的任务列表
     * @param  start 开始时间 end 结束时间
     * @return
     */
    @RequestMapping(value = "/getUnFeedbackRecord.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getUnFeedbackRecord(HttpServletRequest request, @RequestParam String start, @RequestParam String end) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        String yhdm = gdRyxxService.getYhdm(request);
        String fybh = gdRyxxService.findByYhdm(yhdm).getFybh();
        return gdfkService.getUnFeedbackRecord(start, end, fybh);
    }

    /**
     * 获取未分配的任务列表
     * @param start 开始时间 end 结束时间
     * @return
     */
    @RequestMapping(value = "/getFeedbackRecord.do", method = RequestMethod.POST)
    @ResponseBody
    public List<YysdModel> getFeedbackRecord(HttpServletRequest request, @RequestParam String start, @RequestParam String end) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        String yhdm = gdRyxxService.getYhdm(request);
        String fybh = gdRyxxService.findByYhdm(yhdm).getFybh();
        return gdfkService.getFeedbackRecord(start, end, fybh);
    }

    /**
     * 工单最终确认反馈
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveFeedback.do", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedback(HttpServletRequest request, Integer[] selectedYysdbh) {
        // 暂时在测试库，以后会在指定的法院
        DynamicDataSource.router(SDPT_FYDM);
        UserContextModel userContext = (UserContextModel) request.getSession()
                .getAttribute("userContext");
        for (Integer yysdbh :selectedYysdbh) {
            PubYysdJbEntity jb = yysdService.selectByPrimaryKey(yysdbh);
            String sdjg = jb.getSdjg().equals("Y")?"Y":"N";
            yysdService.uploadSdjgMain(yysdbh, sdjg);
            PubYysdRyxxEntity pubYysdRyxxEntity = gdRyxxService.findByYhdm(jb.getGdryxm());
            StringBuilder msg = new StringBuilder();
            msg.append("【天津法院】法官,您好!").append("：（工单号:")
                    .append(yysdbh).append("，案号:").append(jb.getAh()).append("，的送达任务已完成,送达结果:")
            .append(jb.getSdjg().equals("Y")?"成功":"失败")
            .append(",请登录系统查看送达结果及详情。如果有问题请联系送达专员(")
            .append(pubYysdRyxxEntity.getYhmc()).append(" 电话:").append(pubYysdRyxxEntity.getLxdh());
            MwdxSendVo mwdxSendVo = new MwdxSendVo();
            if(jb.getSjhm()!=null){
                mwdxSendVo.setSendphone(jb.getSjhm());
                mwdxSendVo.setMsgcontent(msg.toString());
                mwdxSendVo.setSendtype(2);
                mwdxSendVo.setYysdbh(yysdbh);
                dxtzService.sendPlaintext(mwdxSendVo, null);
            }
            logService.addLog(jb.getAjxh(),jb.getFybh(),null,50,"确认反馈",userContext.getYhmc(),jb.getYysdbh());
        }
        return Result.succeed;
    }

}
