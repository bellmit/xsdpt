package com.nju.sdpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.PubYysdRyxxEntityMapper;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.util.FydmUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
/**
 * 预约送达系统
 * @author zzy
 */
@Controller
@Api("预约送达系统")
public class YysdController {
    @Autowired
    YysdService yysdService;
    @Autowired
    DsrjbService dsrjbService;
    @Autowired
    DmService dmService;
    @Autowired
    AjjbxxService ajjbxxService;
    @Autowired
    LaayService laayService;
    @Autowired
    XtyhService xtyhService;
    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Autowired
    LogService logService;
    @Autowired
    SsdrQrxxService ssdrQrxxService;
    @Autowired
    GdRyxxService gdRyxxService;
    @Autowired
    DxtzService dxtzService;
    @Autowired
    HisSsdrDataInfoService hisSsdrDataInfoService;
    @Autowired
    QtsscyrService qtsscyrService;
    @Autowired
    SpryService spryService;
    @Autowired
    WsService wsService;
    @Resource
    SsdrService ssdrService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    @Value("${SDPT.HIS_REPAIR}")
    Boolean SDPT_HIS_REPAIR;

    /**
     * 获取案件文书信息
     * http://localhost:8088/yysd.aj?fybh=51&ajxh=41841&yhm=xuzhl
     * @param request
     * @return
     */
    @RequestMapping(value = "/getWsInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public  Map<Integer,List<WsInfo>>  getWsInfos (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer)session.getAttribute("ajxh");
        String fybh = (String)session.getAttribute("fybh");
        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        Map<Integer,List<WsInfo>> wsInfos = yysdService.getWsInfosByAjxh(ajxh);
        return wsInfos;
    }


    /**
     * 获取案件文书信息
     * http://localhost:8088/yysd.aj?fybh=51&ajxh=41841&yhm=xuzhl
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFydz.aj", method = RequestMethod.POST)
    @ResponseBody
    public String getFydz (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer)session.getAttribute("ajxh");
        String fybh = (String)session.getAttribute("fybh");
        return FYEnum.getFyByFybh(fybh).getFydz();
    }
    /**
     * http://localhost:8089/zxyysd.do?ajbs=005300000172901&rybs=3526510
     * 执行案件预约送达接口
     * @param
     * @param
     * @return
     */
    @RequestMapping(value="/getFgIndex.aj", method = RequestMethod.POST)
    @ResponseBody
    public Result getFgIndex(HttpServletRequest request){
        HttpSession session = request.getSession();
        String yhm=(String) session.getAttribute("yhm");
        String fybh=(String) session.getAttribute("fybh");
        String url = "http://130.1.1.150:8088/jzsdpt.aj?fybh="+fybh+"&yhm="+yhm;
        return new Result(true,null,url);
    }



    /**
     * 获取当事人信息
     * http://localhost:8088/yysd.aj?fybh=51&ajxh=41841&yhm=xuzhl
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDsrxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public  List<DsrjbVO>  getDsrxx(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer)session.getAttribute("ajxh");
        String fybh = (String)session.getAttribute("fybh");
        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        List<DsrJbEntity> dsrJbDOS = dsrjbService.getDsrjblistByAjxh(ajxh);
        List<DsrjbVO> dsrjbVOS = new ArrayList<>();
        //填充案件内当事人信息
        for (DsrJbEntity dsrJbDO : dsrJbDOS) {
            DsrjbVO dsrjbVO = dsrjbService.dsrEntityToDsrVO(dsrJbDO);
            if(dsrjbVO != null) {
                dsrjbVOS.add(dsrjbVO);
            }
         }
        //填充当事人历史送达信息,取出法官自行添加的信息
//        DynamicDataSource.router(SDPT_FYDM);
////        ssdrService.bindHisDsrxx(ajxh,fybh,dsrjbVOS);
//        DynamicDataSource.router(fyEnum.getFydm());
        return dsrjbVOS;
    }


    /**
     * 获取案件基本信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAjjbxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public AjjbxxVO getAjjbxx(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        AjjbxxModel ajjbxxModel = ajjbxxService.getAjjbxxByAjxh(ajxh);
        DmbModel dmbmodel = dmService.getDmByLbbhAndDmbh("FBS0021-97",ajjbxxModel.getAjxz());
        String ajlx= dmbmodel.getDmms();
        String laay = laayService.getLaayByAjxh(ajxh,fybh);
        String ktsj= laayService.getKtsjByAjxh(ajxh,fybh);
        return new AjjbxxVO(ajlx,ajjbxxModel.getAjmc(),ajjbxxModel.getAh(),laay,ajjbxxModel.getAjxh(),fybh,ktsj);
    }

    /**
     * 获取送达人信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSdrxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public SdrxxVO getSdrxx(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) session.getAttribute("ajxh");
        String fybh = (String) session.getAttribute("fybh");
        String yhm = (String) session.getAttribute("yhm");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.routerByFybh(fybh);
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
        String bmmc = dmService.getDmByLbbhAndDmbh("USR206-99",xtyhModel.getYhbm()).getDmms();
        return new SdrxxVO(xtyhModel.getYhmc(),fyEnum.getName(),bmmc,xtyhModel.getTel(),xtyhModel.getPhone());
    }

    /**
     *预约送达提交
     */
    @RequestMapping(value = "/yysdSubmit.aj", method = RequestMethod.POST)
    @ResponseBody
    public Integer yysdSubmit(@RequestBody Map data,HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        Integer ajxh = (Integer) data.get("ajxh");
        String fybh = (String)  data.get("fybh");
        String yhm = (String) session.getAttribute("yhm");
        ObjectMapper objectMapper = new ObjectMapper();
        WsInfo [] wsInfos1 = objectMapper.readValue(String.valueOf(data.get("wsInfos")),WsInfo[].class);
        //取出需要送达的当事人编号

        List<Integer> dsrbhs = new ArrayList<>();
        for (WsInfo wsInfo:wsInfos1){
            if(!dsrbhs.contains(wsInfo.getDsrbh())){
                dsrbhs.add(wsInfo.getDsrbh());
            }
        }
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        PubYysdJbEntity pubYysdJbEntity = new PubYysdJbEntity();
        AjjbxxModel ajjbxxModel =ajjbxxService.getAjjbxxByAjxh(ajxh);
        String slcx = "";//添加审理程序，普通/简易/速裁
        if(StringUtil.equals(ajjbxxModel.getAjxz(),"2")&&StringUtil.equals(ajjbxxModel.getSpcx(),"1")&&StringUtil.equals(ajjbxxModel.getSycx(),"2")){
            slcx = "普通";
        }
        if(StringUtil.equals(ajjbxxModel.getAjxz(),"2")&&StringUtil.equals(ajjbxxModel.getSpcx(),"1")&&StringUtil.equals(ajjbxxModel.getSycx(),"1")){
            slcx = "简易";
        }
        Integer scNum = ajjbxxService.checkSfsc(ajjbxxModel.getAjxh());
        if(scNum > 0 ){
            slcx = "速裁";
        }
        if(fybh.equals("74")) {
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i < wsInfos1.length; ++ i) {
                if(wsInfos1[i].getWslb().trim().equals("起诉状") || wsInfos1[i].getWslb().trim().equals("传票") || wsInfos1[i].getWslb().trim().equals("送达回执")
                        || wsInfos1[i].getWslb().trim().equals("举证通知书") || wsInfos1[i].getWslb().trim().equals("举证通知书") || wsInfos1[i].getWslb().trim().equals("地址确认书")) {
                    set.add(wsInfos1[i].getWslb());
                }
            }
            if(set.size() == 6) {
                pubYysdJbEntity.setWsqs(1);
            }
        }
        pubYysdJbEntity.setSlcx(slcx);
        HashMap<Integer,List<String>> dsrDhMap = dsrjbService.getDsrHmByAjxh(ajxh);
        String laay = laayService.getLaayByAjxh(ajxh,fybh);
        pubYysdJbEntity.setAjxh(ajxh);
        pubYysdJbEntity.setYyrxm((String) data.get("fgmc"));
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
//        pubYysdJbEntity.setDsrbh(dsrbh);
        pubYysdJbEntity.setFybh(fybh);
        pubYysdJbEntity.setBmmc((String) data.get("bmmc"));
        pubYysdJbEntity.setBgdh((String) data.get("bgdh"));
        pubYysdJbEntity.setSjhm((String) data.get("sjhm"));
        pubYysdJbEntity.setKtsj((String) data.get("ktsj"));
        pubYysdJbEntity.setAh(ajjbxxModel.getAh());
        pubYysdJbEntity.setAjmc(ajjbxxModel.getAjmc());
//        pubYysdJbEntity.setAjmc("测试用");
        pubYysdJbEntity.setAjxz(ajjbxxModel.getAjxz());
        pubYysdJbEntity.setLaay(laay);
        pubYysdJbEntity.setYysdbz((String) data.get("yysdbz"));
        pubYysdJbEntity.setBmbh(xtyhModel.getYhbm());
        String cbr = ajjbxxService.getCbr(ajxh);
        if (cbr!=null){
            pubYysdJbEntity.setCbr(cbr);
        }
        ObjectMapper objectMapper1= new ObjectMapper();
        DsrjbVO[] dsrjbVOS = objectMapper1.readValue(String.valueOf(data.get("dsr")),DsrjbVO[].class);
        Map<Integer, List<PubQtsscyrEntity>> qtsscyrMap = new LinkedHashMap<>();
        for (DsrjbVO dsrjbVO:dsrjbVOS){
            List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrService.getQtsscyr(ajxh,dsrjbVO.getDsrbh());
            qtsscyrMap.put(dsrjbVO.getDsrbh(),pubQtsscyrEntities);
        }
        DynamicDataSource.router(SDPT_FYDM);

        int yysdbh = yysdService.save(pubYysdJbEntity);

        List<WsInfo> wsInfoList = new ArrayList<>(Arrays.asList(wsInfos1));
        if(wsInfoList.size()!=0){
            yysdService.saveWs(pubYysdJbEntity,wsInfoList);
        }


        for(DsrjbVO dsrjbVO:dsrjbVOS){
            if(!dsrbhs.contains(dsrjbVO.getDsrbh())){
                continue;//只送送达内容里有的当事人
            }
            int countWs = 0;
            for(WsInfo ws : wsInfoList) {
                if(ws.getDsrbh().equals(dsrjbVO.getDsrbh()) && ws.getWsly() != 4) {
                    ++ countWs;
                }
            }
            dsrjbVO.setWsnum(countWs);
            PubYysdSsdrEntity pubYysdSsdrEntity= yysdService.saveDsrjbVO(dsrjbVO,yysdbh,dsrDhMap);
            //插入其他诉讼参与人
            List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrMap.get(dsrjbVO.getDsrbh());
            for (PubQtsscyrEntity pubQtsscyrEntity:pubQtsscyrEntities){
                yysdService.saveQtsscyr(pubQtsscyrEntity,yysdbh,pubYysdSsdrEntity.getSsdrbh());
            }

        }
        String logContent = "";
        //短信通知分案员
        StringBuilder msg = new StringBuilder();
        String fymc = FYEnum.getFyByFybh(fybh).getJc();
        msg.append("【").append(fymc).append("】您好！收到（").append(pubYysdJbEntity.getYyrxm()).append("）委托的送达任务：（工单号:")
                .append(yysdbh).append("，案号:").append(pubYysdJbEntity.getAh()).append("，请及时登录系统进行分配，如有问题请及时联系承办法官。");
        MwdxSendVo mwdxSendVo = new MwdxSendVo();
        List<PubYysdRyxxEntity> pubYysdRyxxEntitys=gdRyxxService.findAdminByfybh(fybh);
        if(pubYysdRyxxEntitys!=null){
            for(PubYysdRyxxEntity pubYysdRyxxEntity:pubYysdRyxxEntitys){
                mwdxSendVo.setSendphone(pubYysdRyxxEntity.getLxdh());
                mwdxSendVo.setMsgcontent(msg.toString());
                mwdxSendVo.setSendtype(6);
                Boolean aBoolean = dxtzService.sendPlaintext(mwdxSendVo, null);
            }
        }
        logService.addLog(ajxh,fybh,"",1,logContent,(String) data.get("fgmc"),pubYysdJbEntity.getYysdbh());
        wsService.yysdWsQz(pubYysdJbEntity);

        //历史数据匹配保存
        try {
            if(SDPT_HIS_REPAIR){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Boolean resultData = hisSsdrDataInfoService.bindSsdrHistoryData(yysdbh);
                    }
                });
                thread.start();//异步处理

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return yysdbh;
    }


    /**
     *预约送达串案提交
     */
    @RequestMapping(value = "/yysdCaSubmit.aj", method = RequestMethod.POST)
    @ResponseBody
    public String yysdCaSubmit(@RequestBody Map data,HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String ajxhs = (String) session.getAttribute("ajxhs");
        String fybh = (String)  data.get("fybh");
        String logContent = "批量预约";
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        ObjectMapper objectMapper = new ObjectMapper();
        WsInfo [] wsInfosSum = objectMapper.readValue(String.valueOf(data.get("wsInfos")),WsInfo[].class);
        //取出需要送达的当事人编号
        List<Integer> dsrbhs = new ArrayList<>();
        for (WsInfo wsInfo:wsInfosSum){
            if(!dsrbhs.contains(wsInfo.getDsrbh())){
                dsrbhs.add(wsInfo.getDsrbh());
            }
        }
        String yhm = (String) session.getAttribute("yhm");
        XtyhModel xtyhModel = xtyhService.getXtyhByYhm(yhm);
        List<AjJbEntity> ajJbEntities = ajjbxxService.getAjjbByAjxhs(ajxhs);
        List<DsrJbEntity> oldDsrjbList = dsrjbService.getDsrjblistByAjxhs(ajxhs);//串案排序前的当事人编号
        List<Integer> yysdbhs = new ArrayList<>();
        ObjectMapper objectMapper1= new ObjectMapper();
        CaDsrjbVO[] dsrjbVOS = objectMapper1.readValue(String.valueOf(data.get("dsr")),CaDsrjbVO[].class);
        List<CaDsrjbVO> dsrjbVOList = new ArrayList<>(Arrays.asList(dsrjbVOS));
        //将预约的当事人拆分为公共当事人和单独的当事人
        List<CaDsrjbVO> caDsrjbVOS=new ArrayList<>();
        List<CaDsrjbVO> gaDsrjbVOS=new ArrayList<>();
        for (CaDsrjbVO dsrjbVO:dsrjbVOList){
           if(dsrbhs.contains(dsrjbVO.getDsrbh())){
               if(dsrjbVO.getAjxhs().length>1){
                   caDsrjbVOS.add(dsrjbVO);
               }else {
                   gaDsrjbVOS.add(dsrjbVO);
               }
           }
        }
        //取出当事人电话
        HashMap<Integer,List<String>> caDsrDhMap = dsrjbService.getDsrHmByAjxh(ajJbEntities.get(0).getAjxh());
        //每一个公共当事人生成一个工单,单独当事人每个案件生成一个工单
        for (CaDsrjbVO caDsrjbVO:caDsrjbVOS){
            DynamicDataSource.router(fyEnum.getFydm());

            List<AjJbEntity> ajJbList = new ArrayList<>();
            List<Integer> voajxhs = new ArrayList<>(Arrays.asList(caDsrjbVO.getAjxhs()));
            for (AjJbEntity ajJbEntity:ajJbEntities){
                if(voajxhs.contains(ajJbEntity.getAjxh())){
                    ajJbList.add(ajJbEntity);
                }
            }
            List<WsInfo> cadsrWsInfo = new ArrayList<>();
            for (WsInfo wsInfo:wsInfosSum){
                if(wsInfo.getDsrbh().equals(caDsrjbVO.getDsrbh())){
                    cadsrWsInfo.add(wsInfo);
                }
            }
            PubYysdJbEntity pubYysdJbEntity = new PubYysdJbEntity();
            pubYysdJbEntity.setAjxh(-1);//案件序号为-1的代表是串案,要去串案表里找他的案件序号集合
            pubYysdJbEntity.setAh(caDsrjbVO.getSzah());
            pubYysdJbEntity.setYysdbz((String) data.get("yysdbz"));
            String ajmc = "串案:";
            for (AjJbEntity ajJbEntity:ajJbList){
                ajmc+=ajJbEntity.getAjmc()+";";
            }
            ajmc = ajmc.substring(0,ajmc.length()-1);
            pubYysdJbEntity.setAjmc(ajmc);
            pubYysdJbEntity.setBmmc((String) data.get("bmmc"));
            pubYysdJbEntity.setBgdh((String) data.get("bgdh"));
            pubYysdJbEntity.setSjhm((String) data.get("sjhm"));
            pubYysdJbEntity.setYyrxm((String) data.get("fgmc"));
            pubYysdJbEntity.setFybh(fybh);

            int oldDsrbh = -1;
            for (DsrJbEntity dsrJbEntity:oldDsrjbList){
                if(StringUtil.equals(dsrJbEntity.getDsrjc(),caDsrjbVO.getDsrjc())&&dsrJbEntity.getAjxh().equals(ajJbList.get(0).getAjxh())){
                    oldDsrbh = dsrJbEntity.getDsrbh();
                    break;
                }
            }

            List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrService.getQtsscyr(ajJbList.get(0).getAjxh(),oldDsrbh);

            DynamicDataSource.router(SDPT_FYDM);
            if(pubYysdJbEntity.getKtsj()==null){
                pubYysdJbEntity.setKtsj("");
            }
            int yysdbh = yysdService.save(pubYysdJbEntity);
            for (AjJbEntity ajJbEntity:ajJbList){
                for (DsrJbEntity dsrJbEntity:oldDsrjbList){
                    if(dsrJbEntity.getAjxh().equals(ajJbEntity.getAjxh())&&StringUtil.equals(dsrJbEntity.getDsrjc(),caDsrjbVO.getDsrjc())){
                        PubCaxxEntity pubCaxxEntity = new PubCaxxEntity(null,yysdbh,ajJbEntity.getAjxh(),dsrJbEntity.getDsrbh());
                        yysdService.saveCaxx(pubCaxxEntity);
                    }
                }
            }
            if(cadsrWsInfo.size()!=0){
                yysdService.saveWs(pubYysdJbEntity,cadsrWsInfo);
                int countWs = 0;
                for(WsInfo ws : cadsrWsInfo) {
                    if(ws.getDsrbh().equals(caDsrjbVO.getDsrbh()) && ws.getWsly() != 4) {
                        ++ countWs;
                    }
                }
                caDsrjbVO.setWsnum(countWs);
            }
            yysdbhs.add(yysdbh);
            for (AjJbEntity ajJbEntity:ajJbList){
                logService.addLog(ajJbEntity.getAjxh(),fybh,"",1,logContent,(String) data.get("fgmc"),yysdbh);
            }
            PubYysdSsdrEntity pubYysdSsdrEntity=yysdService.saveDsrjbVO(caDsrjbVO,yysdbh, caDsrDhMap);
            for (PubQtsscyrEntity qtsscyrEntity:pubQtsscyrEntities){
                yysdService.saveQtsscyr(qtsscyrEntity,yysdbh,pubYysdSsdrEntity.getSsdrbh());
            }
            wsService.yysdWsQzCa(pubYysdJbEntity);
        }
        //将个案文书取出来
        Map<Integer,List<WsInfo>> dsrbhWsInfoMap = new LinkedHashMap<>();
        for (CaDsrjbVO gaDsrjbVO:gaDsrjbVOS){
            List<WsInfo> wsInfoList = new ArrayList<>();
            dsrbhWsInfoMap.put(gaDsrjbVO.getDsrbh(),wsInfoList);
        }
        for (WsInfo wsInfo:wsInfosSum){
            for (CaDsrjbVO gaDsrjbVO:gaDsrjbVOS){
                if(wsInfo.getDsrbh().equals(gaDsrjbVO.getDsrbh())){
                    List<WsInfo> wsInfoList = dsrbhWsInfoMap.get(wsInfo.getDsrbh());
                    wsInfoList.add(wsInfo);
                    dsrbhWsInfoMap.put(gaDsrjbVO.getDsrbh(),wsInfoList);
                    break;
                }
            }
        }
        Map<Integer,List<CaDsrjbVO>> ajxhDsrjbMap = new LinkedHashMap<>();
        //当事人按照案件分类
        for (CaDsrjbVO gaDsrjbVO:gaDsrjbVOS){
            List<CaDsrjbVO> mapValue = ajxhDsrjbMap.get(gaDsrjbVO.getAjxh());
            if(mapValue==null){
                List<CaDsrjbVO> newValue = new ArrayList<>();
                newValue.add(gaDsrjbVO);
                ajxhDsrjbMap.put(gaDsrjbVO.getAjxh(),newValue);
            }else {
                mapValue.add(gaDsrjbVO);
                ajxhDsrjbMap.put(gaDsrjbVO.getAjxh(),mapValue);
            }
        }
        for (Map.Entry<Integer,List<CaDsrjbVO>> gaDsrjbVOList:ajxhDsrjbMap.entrySet()){
            Integer ajxh = gaDsrjbVOList.getKey();
            AjJbEntity ajJbEntity = new AjJbEntity();
            for (AjJbEntity temp:ajJbEntities){
                if (temp.getAjxh().equals(ajxh)){
                    ajJbEntity = temp;
                    break;
                }
            }
            List<CaDsrjbVO> dsrs = gaDsrjbVOList.getValue();
            //还原真正的当事人编号
            Map<Integer, List<PubQtsscyrEntity>> qtsscyrMap = new LinkedHashMap<>();
            List<WsInfo> gaWsInfos = new ArrayList<>();
            DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
            String slcx = "";//添加审理程序，普通/简易/速裁
            if(StringUtil.equals(ajJbEntity.getAjxz(),"2")&&StringUtil.equals(ajJbEntity.getSpcx(),"1")&&StringUtil.equals(ajJbEntity.getSycx(),"2")){
                slcx = "普通";
            }
            if(StringUtil.equals(ajJbEntity.getAjxz(),"2")&&StringUtil.equals(ajJbEntity.getSpcx(),"1")&&StringUtil.equals(ajJbEntity.getSycx(),"1")){
                slcx = "简易";
            }
            Integer scNum = ajjbxxService.checkSfsc(ajxh);
            if(scNum > 0 ){
                slcx = "速裁";
            }
            for (CaDsrjbVO caDsrjbVO:dsrs){
                List<WsInfo> wsInfoList = dsrbhWsInfoMap.get(caDsrjbVO.getDsrbh());
                caDsrjbVO.setDsrbh(oldDsrjbList.get(caDsrjbVO.getDsrbh()-1).getDsrbh());
                for (WsInfo wsInfo:wsInfoList){
                    wsInfo.setDsrbh(caDsrjbVO.getDsrbh());
                    gaWsInfos.add(wsInfo);
                }
                List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrService.getQtsscyr(ajxh,caDsrjbVO.getDsrbh());
                qtsscyrMap.put(caDsrjbVO.getDsrbh(),pubQtsscyrEntities);
            }
            PubYysdJbEntity pubYysdJbEntity = new PubYysdJbEntity(ajxh,ajJbEntity.getAh(),ajJbEntity.getAjmc(),ajJbEntity.getAjxz(),laayService.getLaayByAjxh(ajxh,fybh),ajjbxxService.getCbr(ajxh),(String) data.get("fgmc"),fybh,(String) data.get("bmmc"),(String) data.get("bgdh"),(String) data.get("sjhm"),(String) data.get("yysdbz"),laayService.getKtsjByAjxh(ajxh,fybh),slcx);

            pubYysdJbEntity.setBmbh(xtyhModel.getYhbm());
            HashMap<Integer,List<String>> gaDsrDhMap = dsrjbService.getDsrHmByAjxh(ajxh);
            DynamicDataSource.router(SDPT_FYDM);
            int yysdbh = yysdService.save(pubYysdJbEntity);
            yysdService.saveWs(pubYysdJbEntity,gaWsInfos);
            yysdbhs.add(yysdbh);
            logService.addLog(ajJbEntity.getAjxh(),fybh,"",1,logContent,(String) data.get("fgmc"),yysdbh);
            for (CaDsrjbVO caDsrjbVO:dsrs){
                    int countWs = 0;
                    for(WsInfo ws : gaWsInfos) {
                        if(ws.getDsrbh().equals(caDsrjbVO.getDsrbh()) && ws.getWsly() != 4) {
                            ++ countWs;
                        }
                    }
                    caDsrjbVO.setWsnum(countWs);
                PubYysdSsdrEntity pubYysdSsdrEntity=yysdService.saveDsrjbVO(caDsrjbVO,yysdbh, gaDsrDhMap);
                List<PubQtsscyrEntity> pubQtsscyrEntities = qtsscyrMap.get(caDsrjbVO.getDsrbh());
                for (PubQtsscyrEntity qtsscyrEntity:pubQtsscyrEntities){
                    yysdService.saveQtsscyr(qtsscyrEntity,yysdbh,pubYysdSsdrEntity.getSsdrbh());
                }
            }
            wsService.yysdWsQz(pubYysdJbEntity);
        }


        //短信通知分案员
        StringBuilder msg = new StringBuilder();
        StringBuilder yysdbhStrBui = new StringBuilder() ;
        for (Integer yysdbh:yysdbhs){
            yysdbhStrBui.append(yysdbh).append("、");
        }
        String yysdbhStr = yysdbhStrBui.toString();
        yysdbhStr = yysdbhStr.substring(0,yysdbhStr.length()-1);
        String fymc = FYEnum.getFyByFybh(fybh).getJc();
        msg.append("【").append(fymc).append("】您好！收到（").append((String) data.get("fgmc")).append("）委托的批量送达任务：（工单号:")
                .append(yysdbhStr).append("，请及时登录系统进行分配，如有问题请及时联系承办法官。");
        MwdxSendVo mwdxSendVo = new MwdxSendVo();
        List<PubYysdRyxxEntity> pubYysdRyxxEntitys=gdRyxxService.findAdminByfybh(fybh);
        if(pubYysdRyxxEntitys!=null){
            for(PubYysdRyxxEntity pubYysdRyxxEntity:pubYysdRyxxEntitys){
                mwdxSendVo.setSendphone(pubYysdRyxxEntity.getLxdh());
                mwdxSendVo.setMsgcontent(msg.toString());
                mwdxSendVo.setSendtype(6);
               Boolean aBoolean = dxtzService.sendPlaintext(mwdxSendVo, null);
            }
        }
        return yysdbhStr;
    }

    /**
     * 获取串案基本信息
     * @param request
     * http://localhost:8088/yysdCa.aj?fybh=51&ajxhs=41841,12345&yhm=xuzhl
     * @return
     */
    @RequestMapping(value = "/getCaAjjbxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public List<AjjbxxVO> getCaAjjbxx(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String ajxhs = (String) session.getAttribute("ajxhs");
        String fybh = (String) session.getAttribute("fybh");
        FYEnum fyEnum = FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        List<AjJbEntity> ajJbEntities = ajjbxxService.getAjjbByAjxhs(ajxhs);
        List<AjjbxxVO> ajjbxxVOS = new ArrayList<>();
        for (AjJbEntity ajJbEntity:ajJbEntities){
            String ajlx = "";
            ajlx = dmService.getDmByLbbhAndDmbh("FBS0021-97",ajJbEntity.getAjxz()).getDmms();
            String laay = laayService.getLaayByAjxh(ajJbEntity.getAjxh(),fybh);
            String ktsj= laayService.getKtsjByAjxh(ajJbEntity.getAjxh(),fybh);
            ajjbxxVOS.add( new AjjbxxVO(ajlx,ajJbEntity.getAjmc(),ajJbEntity.getAh(),laay,ajJbEntity.getAjxh(),fybh,ktsj));
        }
        return ajjbxxVOS;
    }

    /**
     * 获取当事人信息
     * http://localhost:8088/yysdCa.aj?fybh=51&ajxhs=752041,752042,752043,752044,752045&yhm=xuzhl
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCaDsrxx.aj", method = RequestMethod.POST)
    @ResponseBody
    public  List<CaDsrjbVO>  getCaDsrxx(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String ajxhs = (String) session.getAttribute("ajxhs");
        String fybh = (String)session.getAttribute("fybh");
        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        List<CaDsrjbVO> dsrjbVOS = dsrjbService.getDsrVOByAjxhs(ajxhs);
        return dsrjbVOS;
    }
    /**
     * 获取串案案件文书信息
     * http://localhost:8089/yysdCa.aj?fybh=51&ajxhs=800120,800121&yhm=xuzhl
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCaWsInfos.do", method = RequestMethod.POST)
    @ResponseBody
    public  Map<Integer,List<WsInfo>> getCaWsInfos (HttpServletRequest request) {
        HttpSession session = request.getSession();
        String ajxhs = (String) session.getAttribute("ajxhs");
        String fybh = (String)session.getAttribute("fybh");
        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
        DynamicDataSource.router(fyEnum.getFydm());
        Map<Integer,List<WsInfo>> wsInfos = yysdService.getCaWsInfosByAjxh(ajxhs);
        return wsInfos;
    }


    @RequestMapping(value = "/getDsrwslb.aj", method = RequestMethod.POST)
    @ResponseBody
    public  List<DsrwslbModel> getDsrwslb (HttpServletRequest request, Integer yysdbh, Integer ssdrbh) {
//        HttpSession session = request.getSession();
//        String fybh = (String)session.getAttribute("fybh");
//        FYEnum fyEnum= FYEnum.getFyByFybh(fybh);
//        DynamicDataSource.router(fyEnum.getFydm());


        List<DsrwslbModel> result = new ArrayList<>();

        PubYysdSsdrEntity ssdrEntity = ssdrService.findByYysdbhAndSsdrbh(yysdbh, ssdrbh);

        List<PubYysdWsEntity> dsrwslbs = yysdService.getDsrwslb(yysdbh, ssdrbh);
        if(dsrwslbs != null && !dsrwslbs.isEmpty()){
            for(PubYysdWsEntity pubYysdWsEntity : dsrwslbs){
                DsrwslbModel dsrwslbModel = new DsrwslbModel();
                dsrwslbModel.setYysdbhBh(pubYysdWsEntity.getYysdbh()+"_"+pubYysdWsEntity.getBh());
                dsrwslbModel.setSsdrmcWslb(ssdrEntity.getSsdrmc()+"_"+pubYysdWsEntity.getWslb());
                result.add(dsrwslbModel);
            }
        }
        return result;
    }


    @RequestMapping(value = "/saveDsrsdwslb.aj", method = RequestMethod.POST)
    @ResponseBody
    public String saveDsrsdwslb(HttpServletRequest request, @RequestBody List<String> dsrwslbArray){

        for(String str : dsrwslbArray){
            String[] yysdbhBh = str.split("_");
            PubYysdWsEntity pubYysdWsEntity = yysdService.getByYysdbhAndBh(Integer.parseInt(yysdbhBh[0]), Integer.parseInt(yysdbhBh[1]));
            PubYysdSdwsEntity pubYysdSdwsEntity = new PubYysdSdwsEntity();

        }

        return "success";
    }
}
