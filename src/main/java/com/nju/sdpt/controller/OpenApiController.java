package com.nju.sdpt.controller;

import com.alibaba.fastjson.JSON;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.service.HisSsdrDataInfoService;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.openApi.FzSsdrDataPushVo;
import exception.Result;
import exception.ResultUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 对外接口相关
 */
@RestController
@RequestMapping("/open_api")
@Api(value = "对外接口相关")
public class OpenApiController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(OpenApiController.class);

    @Autowired
    HisSsdrDataInfoService hisSsdrDataInfoService;

    /**
     * 接受法综端当事人基本数据
     * @return
     */
    @RequestMapping(value = "/ssdr_info_push.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> report_lj(@RequestBody FzSsdrDataPushVo pushVo){
        logger.info("[ssdr_info_push.zf]RequestBody =>>>{}", JSON.toJSONString(pushVo));
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        try {
            if(StringUtil.isBlank(pushVo.getFybh())){
                String msg ="法院代码为空,案件序号:"+pushVo.getAjxh()+",当事人名称:"+pushVo.getSsdrmc();
                logger.info(msg);
                return ResultUtils.wrapFail(msg);
            }
            Boolean resultData = hisSsdrDataInfoService.ssdr_info_push(pushVo);
            return ResultUtils.wrapSuccess(resultData);
        }catch (Exception e){
            logger.info("历史数据出错:"+" 法院编号"+pushVo.getFybh()+"案件序号:"+pushVo.getAjxh()+",当事人名称:"+pushVo.getSsdrmc());
            return ResultUtils.wrapFail("又传错了,憨憨");
        }

    }

    /**
     * test
     * @return
     */
    @RequestMapping(value = "/test_his.test",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result<Object>> test_his(@RequestParam Integer yysdbh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        Boolean resultData = hisSsdrDataInfoService.bindSsdrHistoryData(yysdbh);
        return ResultUtils.wrapSuccess(resultData);
    }

}
