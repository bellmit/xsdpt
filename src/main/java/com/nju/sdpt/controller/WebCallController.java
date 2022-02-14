package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.model.PubWebCallInfoModel;
import com.nju.sdpt.model.RetObj;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.HzzfService;
import com.nju.sdpt.viewobject.SubmitWebCallVo;
import exception.Result;
import exception.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 外呼相关业务 控制层
 */
@Controller
@RequestMapping("/webCall")
public class WebCallController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(OpenApiController.class);

    @Autowired
    private HzzfService hzzfService;
    @Autowired
    GdRyxxService gdRyxxService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    /**
     * 填写电话状态使用
     * @param request
     * @return
     */
    @RequestMapping(value = "/submit_web_call.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj submit_web_call(HttpServletRequest request, @RequestBody SubmitWebCallVo webCallVo){
        //加载数据环境
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
         String   yhm = (String) session.getAttribute("yhm");
        PubYysdRyxxEntity pubYysdRyxxEntity = gdRyxxService.findByYhdm(yhm);
        webCallVo.setSdybh(pubYysdRyxxEntity.getYhbh());
        hzzfService.submitWebCall(webCallVo);
        RetObj obj = new RetObj();
        obj.setCode("1");
        obj.setMessage("succcess");
        obj.setSuccess("true");
        return obj;
    }

    /**
     * 下载通话记录的录音文件
     * @param webCallId 通话记录ID
     * @param request
     * @param response
     */
    @GetMapping(value = "/download_call_radio.zf")
    public void download_call_radio(@RequestParam Integer webCallId,HttpServletRequest request, HttpServletResponse response){
        DynamicDataSource.router(SDPT_FYDM);
        hzzfService.download_call_radio(webCallId,request,response);
    }

    /**
     * 填写电话状态使用
     * @param request
     * @return
     */
    @RequestMapping(value = "/web_call_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> web_call_list(HttpServletRequest request,@RequestParam String start, @RequestParam String end){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        Integer yhbh = gdRyxxService.getYhbhBySession(session);
        List<PubWebCallInfoModel> modelList = hzzfService.web_call_list(yhbh,start,end);
        return ResultUtils.wrapSuccess(modelList);
    }
}
