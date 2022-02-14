package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.entity.PubYysdRwlzEntity;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.service.LogService;
import com.nju.sdpt.service.RwlzService;
import com.nju.sdpt.viewobject.RwlzVO;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RwlzServiceImpl implements RwlzService {
    @Resource
    PubYysdRwlzEntityMapper pubYysdRwlzEntityMapper;
    @Resource
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Resource
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;
    @Resource
    LogService logService;


    @Override
    public int deleteByPrimaryKey(Integer bh) {
        return pubYysdRwlzEntityMapper.deleteByPrimaryKey(bh);
    }

    @Override
    @Transactional
    public void insert(RwlzVO rwlzVO) {
        for (Integer yysdbh:rwlzVO.getYysdbhList()){
            PubYysdRwlzEntity pubYysdRwlzEntity = new PubYysdRwlzEntity(null,yysdbh,new Date(),rwlzVO.getCreater(),rwlzVO.getTarget(),null);
            String creater = pubYysdRyxxEntityMapper.selectByPrimaryKey(rwlzVO.getCreater()).getYhmc();
            insert1(pubYysdRwlzEntity);
            PubYysdRyxxEntity pubYysdRyxxEntity = pubYysdRyxxEntityMapper.selectByPrimaryKey(rwlzVO.getTarget());
            PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
            if(pubYysdJbEntity.getAjxh()==-2){
                return;
            }
            pubYysdJbEntity.setGdryxm(pubYysdRyxxEntity.getYhdm());
            pubYysdJbEntityMapper.updateGdryxm(pubYysdJbEntity);
            logService.addLog(pubYysdJbEntity.getAjxh(),pubYysdJbEntity.getFybh(),pubYysdRyxxEntity.getYhmc(),9,"",creater,yysdbh);
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert1(PubYysdRwlzEntity pubYysdRwlzEntity) {
        pubYysdRwlzEntityMapper.insert(pubYysdRwlzEntity);
    }

    @Override
    public int insertSelective(PubYysdRwlzEntity record) {
        return pubYysdRwlzEntityMapper.insertSelective(record);
    }

    @Override
    public PubYysdRwlzEntity selectByPrimaryKey(Integer bh) {
        return pubYysdRwlzEntityMapper.selectByPrimaryKey(bh);
    }

    @Override
    public int updateByPrimaryKeySelective(PubYysdRwlzEntity record) {
        return pubYysdRwlzEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PubYysdRwlzEntity record) {
        return pubYysdRwlzEntityMapper.updateByPrimaryKey(record);
    }
}
