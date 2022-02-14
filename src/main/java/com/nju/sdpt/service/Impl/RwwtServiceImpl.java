package com.nju.sdpt.service.Impl;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.RwwtModel;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.RwwtService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class RwwtServiceImpl implements RwwtService {
    @Autowired
    PubGroupEntityMapper pubGroupEntityMapper;
    @Autowired
    PubYysdRyxxEntityMapper ryxxMapper;
    @Autowired
    PubRwwtEntityMapper pubRwwtEntityMapper;
    @Autowired
    PubYysdSsdrEntityMapper ssdrMapper;
    @Autowired
    LogService logService;
    @Autowired
    PubYysdJbEntityMapper jbMapper;
    @Override
    public void addNewRwwt(Integer yysdbh, Integer ssdrbh,String wtfs,Integer wtrbh, Integer clrbh) {
        PubYysdRyxxEntity wtr = ryxxMapper.selectByPrimaryKey(wtrbh);
        PubYysdRyxxEntity clr = ryxxMapper.selectByPrimaryKey(clrbh);
        PubYysdSsdrEntity ssdrEntity = ssdrMapper.selectByPrimaryKey(new PubYysdSsdrEntityKey(ssdrbh,yysdbh));
        PubYysdJbEntity pubYysdJbEntity = jbMapper.selectByPrimaryKey(yysdbh);
        PubRwwtEntity rwwtEntity = new PubRwwtEntity(null,yysdbh,new Date(),ssdrbh,ssdrEntity.getSsdrmc(),wtr.getYhmc(),clr.getYhmc(),wtfs,null,null);
        pubRwwtEntityMapper.insert(rwwtEntity);
        logService.addLog(pubYysdJbEntity.getAjxh(),pubYysdJbEntity.getFybh(),clr.getYhmc(), SdptConstants.LOG_TYPE.getLogTypeByJc("WT"+wtfs).getTypeNum(),"",wtr.getYhmc(),yysdbh);
    }

    @Override
    public boolean deleteRwwt(Integer yysdbh, Integer ssdrbh) {
        //TODO:
        return true;
    }


    public List<PubGroupEntity> getGroupRyxx(String groupName, String fybh) {
        return pubGroupEntityMapper.selectByGroupName(groupName, Integer.parseInt(fybh));
    }

    @Override
    public List<RwwtModel> getRwwtByYysdbh(Integer yysdbh) {
        List<RwwtModel> rwwtModels = new LinkedList<>();
        List<PubRwwtEntity> rwwtEntities = pubGroupEntityMapper.selectByYysdbh(yysdbh);
        for (PubRwwtEntity rwwtEntity:rwwtEntities){
            rwwtModels.add(new RwwtModel(rwwtEntity));
        }
        return rwwtModels;
    }

    @Override
    public List<RwwtModel> getRwwtByClrmcAndCljg(String yhmc,Integer cljg){
        return pubRwwtEntityMapper.getRwwtByClrmcAndCljg(yhmc,cljg);
    }

    @Override
    public void uploadRwwtjg(Integer rwwtid, String cljg) {
       pubRwwtEntityMapper.uploadRwwtjg(rwwtid,cljg);
    }
}
