package com.nju.sdpt.controller;


import com.alibaba.fastjson.JSON;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.PubYysdSsdrEntityMapper;
import com.nju.sdpt.model.EmssdModel;
import com.nju.sdpt.model.PubYysdSsdrModel;
import com.nju.sdpt.model.ResultObj;
import com.nju.sdpt.model.RetObj;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.NumberUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.InputDataSsdrVo;
import com.nju.sdpt.viewobject.QuerySsdrHisDataVo;
import com.nju.sdpt.viewobject.QuerySsdrListVo;
import exception.BusinessException;
import exception.HttpCode;
import exception.Result;
import exception.ResultUtils;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 受送达人相关业务 控制层
 */
@RestController
@RequestMapping("/ssdr")
public class SsdrController {

    private final Logger logger = LoggerFactory.getLogger(SsdrController.class);
    @Autowired
    SsdrService ssdrService;
    @Autowired
    YysdService yysdService;
    @Autowired
    SdxxService sdxxService;
    @Resource
    SsdrDzService ssdrDzService;
    @Resource
    SsdrHmService ssdrHmService;
    @Resource
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    /**
     * 根据工单编号查询当事人信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/query_ssdr_list.zf",method = RequestMethod.POST)
    public List<PubYysdSsdrModel> querySsdrList(HttpServletRequest request, @RequestBody QuerySsdrListVo querySsdrListVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        //查询具体数据
        List<PubYysdSsdrModel> result = ssdrService.querySsdrList(querySsdrListVo);
        return result;
    }

    /**
     * 根据工单号获取受送达人的信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/query_tssdxx.zf",method = RequestMethod.POST)
    public List<SsdrHmEntity> queryTssdxx(@RequestBody Map data){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        Integer yysdbh=Integer.valueOf(data.get("yysdbh").toString());
        //查询具体数据
        List<SsdrHmEntity> result = pubYysdSsdrEntityMapper.getSsdrHms(yysdbh);
        return result;
    }

    /**
     * 根据工单号添加受送达人的信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/add_ssdr.zf",method = RequestMethod.POST)
    public ResultObj addSsdr(@RequestBody Map data){
        Integer yysdbh=Integer.valueOf(data.get("yysdbh").toString());
        String ssdrmc=data.get("ssdrmc").toString();
        String sfzhm=data.get("sfzhm").toString();
        String hm=data.get("hm").toString();

        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        Integer ssdrbh=pubYysdSsdrEntityMapper.getMaxSsdrbh(yysdbh)+1;

        PubYysdSsdrEntity pubYysdSsdrEntity=new PubYysdSsdrEntity();
        pubYysdSsdrEntity.setYysdbh(yysdbh);
        pubYysdSsdrEntity.setSsdrbh(ssdrbh);
        pubYysdSsdrEntity.setSfzhm(sfzhm);
        pubYysdSsdrEntity.setSsdrmc(ssdrmc);
        pubYysdSsdrEntity.setDaisr(ssdrmc);
        pubYysdSsdrEntity.setSsdrssdw("第三人");
        try {
            ssdrService.insert(pubYysdSsdrEntity);
        }catch (Exception e){
            logger.error(e.getMessage());
            logger.error("添加第三人失败！");
            return new ResultObj("添加失败");
        }

        if(!"".equals(hm)) {
            PubSsdrHmEntity pubSsdrHmEntity = new PubSsdrHmEntity();
            pubSsdrHmEntity.setYysdbh(yysdbh);
            pubSsdrHmEntity.setSsdrbh(ssdrbh);
            pubSsdrHmEntity.setShowTel("".equals(hm) ? null : hm);
            pubSsdrHmEntity.setOperatorTel("".equals(hm) ? null : hm);
            pubSsdrHmEntity.setOperatorType("ENTRY");
            pubSsdrHmEntity.setSdpName(ssdrmc);
            pubSsdrHmEntity.setIdCard(sfzhm);
            pubSsdrHmEntity.setCreateTime(new Date());
            try {
                ssdrService.savePubSsdrHmEntity(pubSsdrHmEntity);
            } catch (Exception e) {
                logger.error(e.getMessage());
                logger.error("添加第三人手机信息失败！");
                return new ResultObj("添加第三人手机信息失败!");
            }
        }
        return new ResultObj();
    }

    /**
     * 工单-录入受送达人数据
     * 联系电话/地址/身份证
     * @param request
     * @param inputDataSsdrVo
     * @return
     *  code： 0 =》失败
     *  code： 200 =》 成功
     */
    @RequestMapping(value = "/input_data.zf",method = RequestMethod.POST)
    @ResponseBody
    public RetObj input_data(HttpServletRequest request, @RequestBody InputDataSsdrVo inputDataSsdrVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");

        //查询具体数据
        RetObj obj = ssdrService.inputData(inputDataSsdrVo,yhm);
        return obj;
    }

    /**
     * 工单-录入受送达人数据外网
     * 联系电话/地址/身份证
     * @param request
     * @param inputDataSsdrVo
     * @return
     *  code： 0 =》失败
     *  code： 200 =》 成功
     */
    @RequestMapping(value = "/input_data.cx",method = RequestMethod.POST)
    @ResponseBody
    public RetObj input_data_ww(HttpServletRequest request, @RequestBody InputDataSsdrVo inputDataSsdrVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = "yhm";

        //查询具体数据
        RetObj obj = ssdrService.inputData(inputDataSsdrVo,yhm);
        return obj;
    }

    /**
     * 查询当事人历史工单送达数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/query_ssdr_his_data.zf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result<Object>> query_ssdr_his_yysd_data(HttpServletRequest request, @RequestBody QuerySsdrHisDataVo querySsdrHisDataVo){
        logger.info("[query_ssdr_his_data.zf]RequestBody =>>>{}", JSON.toJSONString(querySsdrHisDataVo));
        //加载数据环境
        String ssdrmc = querySsdrHisDataVo.getSsdrmc();
        String sfzhm = querySsdrHisDataVo.getSfzhm();
        Integer yysdbh = querySsdrHisDataVo.getYysdbh();
        if(StringUtil.isBlank(ssdrmc) || StringUtil.isBlank(sfzhm) || !NumberUtil.isIntNotNullAndGtZero(yysdbh)){
            throw new BusinessException(HttpCode.CSERROR);
        }
        Map<String,Object> resultMap = new HashMap<>();

        DynamicDataSource.router(SDPT_FYDM);
        //查询历史外呼记录
        List<PubWebCallInfoEntity> hisWebCall = ssdrService.queryHisWebCall(querySsdrHisDataVo);
        resultMap.put("hisWebCall",hisWebCall);
        //查询历史短信记录
        List<PubDxtzInfoEntity> hisDxtz = ssdrService.queryHisDxtz(querySsdrHisDataVo);
        resultMap.put("hisDxtz",hisDxtz);
//        //查询来院记录
//        List<PubLylqInfoEntity> hisLy = ssdrService.queryHisLy(querySsdrHisDataVo);
//        resultMap.put("hisLy",hisLy);
//        //查询直接记录
//        List<PubZjsdInfoEntity> hisZj = ssdrService.queryHisZj(querySsdrHisDataVo);
//        resultMap.put("hisZj",hisZj);

//        PubYysdJbEntity pubYysdJbEntity = yysdService.selectByPrimaryKey(yysdbh);
//        if(null != pubYysdJbEntity){
//            String fybh = pubYysdJbEntity.getFybh();
//            if(StringUtil.isNotBlank(fybh)){
//                try {
//                    List<EmssdModel> emssdModelList = sdxxService.getEmssdGdInfo(querySsdrHisDataVo.getSsdrmc(),querySsdrHisDataVo.getSfzhm(), pubYysdJbEntity.getFybh());
//                    resultMap.put("hisEms",emssdModelList);
//                }catch (Exception ex){
//                    logger.error("根据姓名身份证获取EMS失败");
//                    ex.printStackTrace();
//                }
//            }
//        }


        //查询具体数据
        return ResultUtils.wrapSuccess(resultMap);
    }

    /**
     * 给地址加标签
     * @param request
     * @return
     */
    @RequestMapping(value = "/markDzDisable.aj",method = RequestMethod.POST)
    public void markDzDisable(HttpServletRequest request, Integer bh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        //查询具体数据
       PubYysdSsdrdzEntity pubYysdSsdrdzEntity = ssdrDzService.selectByPrimaryKey(bh);
       pubYysdSsdrdzEntity.setLabel("disable");
       ssdrDzService.updateByPrimaryKey(pubYysdSsdrdzEntity);
    }

    /**
     * 撤销地址标签
     * @param request
     * @return
     */
    @RequestMapping(value = "/undoMarkDzDisable.aj",method = RequestMethod.POST)
    public void undoMarkDzDisable(HttpServletRequest request, Integer bh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        //查询具体数据
        PubYysdSsdrdzEntity pubYysdSsdrdzEntity = ssdrDzService.selectByPrimaryKey(bh);
        pubYysdSsdrdzEntity.setLabel(null);
        ssdrDzService.updateByPrimaryKey(pubYysdSsdrdzEntity);
    }

    /**
     * 根据工单编号查询当事人信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/markHmDisable.aj",method = RequestMethod.POST)
    public void markHmDisable(HttpServletRequest request, Integer bh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        //查询具体数据
        PubSsdrHmEntity pubSsdrHmEntity = ssdrHmService.selectByPrimaryKey(bh);
        pubSsdrHmEntity.setLabel("disable");
        ssdrHmService.updateByPrimaryKey(pubSsdrHmEntity);
    }


    /**
     * 根据工单编号查询当事人信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/undoMarkHmDisable.aj",method = RequestMethod.POST)
    public void undoMarkHmDisable(HttpServletRequest request, Integer bh){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String)session.getAttribute("yhm");
        //查询具体数据
        PubSsdrHmEntity pubSsdrHmEntity = ssdrHmService.selectByPrimaryKey(bh);
        pubSsdrHmEntity.setLabel(null);
        ssdrHmService.updateByPrimaryKey(pubSsdrHmEntity);
    }




}
