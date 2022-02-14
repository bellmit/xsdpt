package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubZgysdInfoEntity;
import com.nju.sdpt.entity.PubZgysdLogEntity;
import com.nju.sdpt.entity.ZgysdLogEntity;
import com.nju.sdpt.mapper.PubZgysdInfoEntityMapper;
import com.nju.sdpt.mapper.PubZgysdLogEntityMapper;
import com.nju.sdpt.service.ZgysdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ZgysdServiceImpl implements ZgysdService {
    @Autowired
    PubZgysdLogEntityMapper pubZgysdLogEntityMapper;
    @Autowired
    PubZgysdInfoEntityMapper pubZgysdInfoEntityMapper;
    @Override
    @Transactional
    public String insert(PubYysdJbEntity pubYysdJbEntity) {

        PubZgysdLogEntity pubZgysdLogEntity = new PubZgysdLogEntity();
        pubZgysdLogEntity.setScsj(new Date());
        pubZgysdLogEntity.setYysdbh(pubYysdJbEntity.getYysdbh());
        pubZgysdLogEntityMapper.insert(pubZgysdLogEntity);
        return pubZgysdLogEntity.getBh()+"";
    }

    @Override
    public PubYysdJbEntity getYysdByAjid(String logbh) {
        return pubZgysdLogEntityMapper.getYysdByAjid(Integer.parseInt(logbh));
    }

    @Override
    @Transactional
    public Integer insert(PubZgysdInfoEntity pubZgysdInfoEntity) {
         pubZgysdInfoEntityMapper.insert(pubZgysdInfoEntity);
        return pubZgysdInfoEntity.getZgysdbh();
    }

    @Override
    public String insertForFz(int fybh, int ajxh) {
        ZgysdLogEntity zgysdLogEntity=new ZgysdLogEntity();
        zgysdLogEntity.setScsj(new Date());
        zgysdLogEntity.setAjxh(ajxh);
        zgysdLogEntity.setFybh(fybh);
        pubZgysdLogEntityMapper.insertForFz(zgysdLogEntity);
        return zgysdLogEntity.getBh()+"";
    }

}
