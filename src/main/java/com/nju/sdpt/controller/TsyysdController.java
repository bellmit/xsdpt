package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.AjjbxxModel;
import com.nju.sdpt.model.DmbModel;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.model.TsyysdAjxx;
import com.nju.sdpt.service.AjjbxxService;
import com.nju.sdpt.service.DmService;
import com.nju.sdpt.service.YysdService;
import com.nju.sdpt.viewobject.Result;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jiaweiq
 * @date 2021/11/25 10:53
 */
@Controller
@RequestMapping("/tsyysd")
@Slf4j
public class TsyysdController {

    @Autowired
    AjjbxxService ajjbxxService;

    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;

    @Autowired
    PubYysdSsdrdzEntityMapper pubYysdSsdrdzEntityMapper;

    @Autowired
    PubSsdrHmEntityMapper pubSsdrHmEntityMapper;

    @Autowired
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;

    @Autowired
    DsrJbEntityMapper dsrJbMapper;

    @Autowired
    DsrGrEntityMapper dsrGrEntityMapper;

    @Autowired
    DsrDwEntityMapper dsrDwEntityMapper;

    @Autowired
    DsrJgEntityMapper dsrJgEntityMapper;

    @Autowired
    YysdService yysdService;

    @Autowired
    DmService dmService;

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;



    /**
     * 模糊检索案件
     * @param data
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAjInLike",method = RequestMethod.POST)
    @ResponseBody
    public List<AjjbxxModel> findAjInLike(@RequestBody Map data, HttpServletRequest request){
        String content=data.get("content").toString();
        String yhm=data.get("yhm").toString();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity pubYysdRyxxEntity=pubYysdRyxxEntityMapper.findByYhdm(yhm);
        if(!"-1".equals(pubYysdRyxxEntity.getFybh())){
            DynamicDataSource.router(FYEnum.getFYDMByFybh(pubYysdRyxxEntity.getFybh()));
        }else{
            DynamicDataSource.router(FYEnum.TJGY.getFydm());
        }
        return ajjbxxService.getAjjbInLike(content);
    }

    /**
     * 获取送达专员的信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/getSdyInfo",method = RequestMethod.POST)
    @ResponseBody
    public PubYysdRyxxEntity getSdyInfo(@RequestBody Map data){
        String yhm=data.get("yhm").toString();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity pubYysdRyxxEntity=pubYysdRyxxEntityMapper.findByYhdm(yhm);
        return pubYysdRyxxEntity;
    }

    /**
     * 获取案件信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/getAjInfo",method = RequestMethod.POST)
    @ResponseBody
    public List<TsyysdAjxx> getAjInfo(@RequestBody Map data){
        int ajxh=Integer.valueOf(data.get("ajxh").toString());
//        String fybh=data.get("fybh").toString();
        String yhm=data.get("yhm").toString();
        String fybh=pubYysdRyxxEntityMapper.findByYhdm(yhm).getFybh();
        if("-1".equals(fybh)){
            fybh=FYEnum.TJGY.getFybh();
        }
        DynamicDataSource.router(FYEnum.getFYDMByFybh(fybh));
        List<TsyysdAjxx> ajjbxxModel=ajjbxxService.getTsyysdAjxx(ajxh);
        return ajjbxxModel;
    }

    /**
     * 获取当事人基表的信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/getDsrjb",method = RequestMethod.POST)
    @ResponseBody
    public List<DsrJbEntity> getDsrjb(@RequestBody Map data){
        int ajxh=Integer.valueOf(data.get("ajxh").toString());
        String yhm=data.get("yhm").toString();
        String fybh=pubYysdRyxxEntityMapper.findByYhdm(yhm).getFybh();
        if("-1".equals(fybh)){
            fybh=FYEnum.TJGY.getFybh();
        }
        DynamicDataSource.router(FYEnum.getFYDMByFybh(fybh));
        List<DsrJbEntity> dsrJbEntities = dsrJbMapper.getDsrjblistByAjxh(ajxh);
        return dsrJbEntities;
    }

    /**
     * 提交特殊预约送达单号
     * @param data
     * @return
     */
    @RequestMapping(value = "/submitTsyysd",method = RequestMethod.POST)
    @ResponseBody
    public Result submitTsyysd(@RequestBody Map data){
        int ajxh=Integer.valueOf(data.get("ajxh").toString());
        String yhm=data.get("yhm").toString();
//        int dsrbh=Integer.valueOf(data.get("dsrbh").toString());
        ArrayList<String> dsrlist=(ArrayList<String>)data.get("dsrlist");
        String fybh=pubYysdRyxxEntityMapper.findByYhdm(yhm).getFybh();
        if("-1".equals(fybh)){
            fybh=FYEnum.TJGY.getFybh();
        }
        DynamicDataSource.router(FYEnum.getFYDMByFybh(fybh));
        String ah=data.get("ah").toString();
        String cbfg=data.get("cbfg").toString();
        String yyr=data.get("yyr").toString();
        String szbm=data.get("yybm").toString();
        String fgdh=data.get("fgdh").toString();
        String ajmc=data.get("ajmc").toString();
        String ay=data.get("ay").toString();

        PubYysdJbEntity pubYysdJbEntity=new PubYysdJbEntity();
        pubYysdJbEntity.setAjxh(-2);
        pubYysdJbEntity.setCbr(cbfg);
        pubYysdJbEntity.setYysj(new Date());
        pubYysdJbEntity.setYyrxm(yyr);
        pubYysdJbEntity.setFybh(fybh);
        pubYysdJbEntity.setBmmc(szbm);
        pubYysdJbEntity.setSjhm(fgdh);
        pubYysdJbEntity.setGdryxm(yhm);
        pubYysdJbEntity.setLaay(ay);
        pubYysdJbEntity.setAjmc(ajmc);
        pubYysdJbEntity.setAh(ah);

        DynamicDataSource.router(SDPT_FYDM);
        int yysdbh = yysdService.save(pubYysdJbEntity);


        for(String dsr:dsrlist) {
            DynamicDataSource.router(FYEnum.getFYDMByFybh(fybh));
            int dsrbh= Integer.parseInt(dsr);
            AjjbxxModel ajjbxxModel = ajjbxxService.getAjjbxxByAjxh(ajxh);
            DsrJbEntity dsrJbEntity = dsrJbMapper.getDsrjblistByAjxhAndDsrbh(ajxh, dsrbh);
            DmbModel dmbModel = dmService.getDsrssdwByAjxzAndSpcxAndSpcxdz(dsrJbEntity.getDsrssdw(), ajjbxxModel.getAjxz(), ajjbxxModel.getSpcx(), ajjbxxModel.getSpcxdz(), ajjbxxModel.getLarq());

            try {

                PubYysdSsdrEntity pubYysdSsdrEntity = new PubYysdSsdrEntity();
                pubYysdSsdrEntity.setYysdbh(yysdbh);
                pubYysdSsdrEntity.setSsdrbh(dsrbh);
                pubYysdSsdrEntity.setSsdrmc(dsrJbEntity.getDsrjc());
                pubYysdSsdrEntity.setSsdrssdw(dmbModel.getDmms());
                pubYysdSsdrEntity.setDaisr(dsrJbEntity.getDsrjc());
                pubYysdSsdrEntity.setWsnum(0);
                pubYysdSsdrEntity.setSsdrssdw(dmbModel.getDmms());


                PubYysdSsdrdzEntity pubYysdSsdrdzEntity = new PubYysdSsdrdzEntity();

                pubYysdSsdrdzEntity.setCreatetime(new Date());
                pubYysdSsdrdzEntity.setSdpName(dsrJbEntity.getDsrjc());
                pubYysdSsdrdzEntity.setLx(1);
                pubYysdSsdrdzEntity.setYysdbh(yysdbh);
                pubYysdSsdrdzEntity.setSsdrbh(dsrbh);

                PubSsdrHmEntity pubSsdrHmEntity = new PubSsdrHmEntity();
                pubSsdrHmEntity.setCreateTime(new Date());
                pubSsdrHmEntity.setSdpName(dsrJbEntity.getDsrjc());
                pubSsdrHmEntity.setYysdbh(yysdbh);
                pubSsdrHmEntity.setSsdrbh(dsrbh);
                pubSsdrHmEntity.setOperatorType("ENTRY");

                DynamicDataSource.router(FYEnum.getFYDMByFybh(fybh));
                if ("1".equals(dsrJbEntity.getDsrlb())) {

                    DsrGrEntity dsrGrEntity = dsrGrEntityMapper.selectByPrimaryKey(ajxh, dsrbh);
                    DynamicDataSource.router(SDPT_FYDM);
                    if (dsrGrEntity.getDz() != null && !"".equals(dsrGrEntity.getDz())) {
                        pubYysdSsdrdzEntity.setDz(dsrGrEntity.getDz());
                        pubYysdSsdrdzEntityMapper.insert(pubYysdSsdrdzEntity);
                    }
                    if (dsrGrEntity.getDh() != null && !"".equals(dsrGrEntity.getDh())) {
                        pubSsdrHmEntity.setShowTel(dsrGrEntity.getDh());
                        pubSsdrHmEntity.setOperatorTel(dsrGrEntity.getDh());
                        pubSsdrHmEntityMapper.insert(pubSsdrHmEntity);
                    }
                    pubYysdSsdrEntity.setSfzhm(dsrGrEntity.getSfzhm());
                    pubYysdSsdrEntityMapper.insert(pubYysdSsdrEntity);
                }
                if ("2".equals(dsrJbEntity.getDsrlb())) {

                    DsrDwEntity dsrDwEntity = dsrDwEntityMapper.selectByPrimaryKey(ajxh, dsrbh);
                    DynamicDataSource.router(SDPT_FYDM);
                    if (dsrDwEntity.getDz() != null && !"".equals(dsrDwEntity.getDz())) {
                        pubYysdSsdrdzEntity.setDz(dsrDwEntity.getDz());
                        pubYysdSsdrdzEntityMapper.insert(pubYysdSsdrdzEntity);
                    }
                    if (dsrDwEntity.getDh() != null && !"".equals(dsrDwEntity.getDh())) {
                        pubSsdrHmEntity.setShowTel(dsrDwEntity.getDh());
                        pubSsdrHmEntity.setOperatorTel(dsrDwEntity.getDh());
                        pubSsdrHmEntityMapper.insert(pubSsdrHmEntity);
                    }
                    pubYysdSsdrEntityMapper.insert(pubYysdSsdrEntity);
                }
                if ("3".equals(dsrJbEntity.getDsrlb())) {

                    DsrJgEntity dsrJgEntity = dsrJgEntityMapper.selectByPrimaryKey(ajxh, dsrbh);
                    DynamicDataSource.router(SDPT_FYDM);
                    if (dsrJgEntity.getDz() != null && !"".equals(dsrJgEntity.getDz())) {
                        pubYysdSsdrdzEntity.setDz(dsrJgEntity.getDz());
                        pubYysdSsdrdzEntityMapper.insert(pubYysdSsdrdzEntity);
                    }
                    if (dsrJgEntity.getDh() != null && !"".equals(dsrJgEntity.getDh())) {
                        pubSsdrHmEntity.setShowTel(dsrJgEntity.getDh());
                        pubSsdrHmEntity.setOperatorTel(dsrJgEntity.getDh());
                        pubSsdrHmEntityMapper.insert(pubSsdrHmEntity);
                    }
                    pubYysdSsdrEntityMapper.insert(pubYysdSsdrEntity);
                }
            } catch (Exception e) {
                log.error("出问题了");
                return new Result(false, "预约失败", "预约失败");
            }
        }
        return new Result(true,"预约成功",null);
    }

}
