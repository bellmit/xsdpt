package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.viewobject.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 待办任务 控制层
 */
@Controller
@RequestMapping("/dbrw")
public class DbrwController {
    @Resource
    DbrwService dbrwService;
    @Resource
    GdRyxxService gdRyxxService;
    @Resource
    LogService logService;
    @Resource
    LylqService lylqService;
    @Resource
    SsdrService ssdrService;
    @Resource
    YysdService yysdService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;


    @RequestMapping(value = "/dbrwList" , method = RequestMethod.POST)
    @ResponseBody
    public List<PubDbrwInfoEntity> getDbrwList(HttpServletRequest request,String clzt ,String rymc,String xzsdfs){
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity byYhmc = gdRyxxService.findByYhdm(rymc);
        Integer yhbh = byYhmc.getYhbh();
        List<PubDbrwInfoEntity> pubDbrwInfoEntities = dbrwService.dbrwList(clzt,yhbh,xzsdfs);
        return pubDbrwInfoEntities;
    }
    @RequestMapping(value = "/updateLylqById" ,method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateLylqById(@RequestBody String xq,Integer id){
        DynamicDataSource.router(SDPT_FYDM);
        dbrwService.updateLylqById(xq, id);
        return true;
    }
    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateById(HttpServletRequest request,@RequestBody PubDbrwInfoEntity pubDbrwInfoEntity){
        DynamicDataSource.router(SDPT_FYDM);
        pubDbrwInfoEntity.setClzt("2");
        dbrwService.updateByPrimaryKeySelective(pubDbrwInfoEntity);
        return true;
    }
    @RequestMapping(value = "/addLylq",method = RequestMethod.POST)
    @ResponseBody
    public Result insertLylq(HttpServletRequest request, @RequestBody PubLylqInfoEntity pubLylqInfoEntity){
        HttpSession session = request.getSession();
        DynamicDataSource.router(SDPT_FYDM);
        PubYysdRyxxEntity  pubYysdRyxxEntity = gdRyxxService.findByYhdm(gdRyxxService.getYhdm(request));
        String yhmc = pubYysdRyxxEntity.getYhmc();
//        String xq = pubLylqInfoEntity.getLqsj()+","+pubLylqInfoEntity.getLylqaddress();
//        pubDbrwInfoMapper.updateLylqById(xq,pubLylqInfoEntity.getId());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date = format.parse(pubLylqInfoEntity.getLqsj());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //查询工单
        PubYysdJbEntity jbEntity = yysdService.selectByPrimaryKey(pubLylqInfoEntity.getYysdbh());
        if(null != jbEntity){
            pubLylqInfoEntity.setFybh(jbEntity.getFybh());
            pubLylqInfoEntity.setAjxh(jbEntity.getAjxh());
        }
        pubLylqInfoEntity.setSdybh(pubYysdRyxxEntity.getYhbh());
        pubLylqInfoEntity.setYylqsj(date);
        pubLylqInfoEntity.setCreateTime(new Date());
        pubLylqInfoEntity.setUpdateTime(new Date());
        pubLylqInfoEntity.setLqstate(0);
        pubLylqInfoEntity.setSdjg(0);
        Integer lylqId = lylqService.savePubLylqInfoEntity(pubLylqInfoEntity);
        PubYysdSsdrEntity ssdrEntity = ssdrService.findByYysdbhAndSsdrbh(pubLylqInfoEntity.getYysdbh(),pubLylqInfoEntity.getSsdrbh());
        logService.addLog(pubLylqInfoEntity.getAjxh(),pubLylqInfoEntity.getFybh(),ssdrEntity.getSsdrmc(),25,"",yhmc,pubLylqInfoEntity.getYysdbh());
        return new Result(true,"",lylqId);
    }

}
