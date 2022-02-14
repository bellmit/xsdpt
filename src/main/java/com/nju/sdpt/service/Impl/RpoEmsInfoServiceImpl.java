package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.entity.RpoEmsInfoEntity;
import com.nju.sdpt.mapper.PubYysdSsdrEntityMapper;
import com.nju.sdpt.mapper.RpoEmsInfoEntityMapper;
import com.nju.sdpt.model.PubYysdSsdrModel;
import com.nju.sdpt.service.RpoEmsInfoService;
import com.nju.sdpt.util.NumberUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RpoEmsInfoServiceImpl implements RpoEmsInfoService {

    @Resource
    RpoEmsInfoEntityMapper emsInfoEntityMapper;
    @Resource
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;

    @Override
    public int deleteByPrimaryKey(Integer bh) {
        return emsInfoEntityMapper.deleteByPrimaryKey(bh);
    }

    @Override
    public int insert(RpoEmsInfoEntity record) {
        return emsInfoEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(RpoEmsInfoEntity record) {
        return emsInfoEntityMapper.insertSelective(record);
    }

    @Override
    public RpoEmsInfoEntity selectByPrimaryKey(Integer bh) {
        return emsInfoEntityMapper.selectByPrimaryKey(bh);
    }

    @Override
    public int updateByPrimaryKeySelective(RpoEmsInfoEntity record) {
        return emsInfoEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RpoEmsInfoEntity record) {
        return emsInfoEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public void uploadSdjg(PubKdtdEntity kdtdEntity) {
        RpoEmsInfoEntity rpoEmsInfoEntity = emsInfoEntityMapper.selectByKdidAndYysdbh(kdtdEntity.getKdid(),kdtdEntity.getYysdbh());
        if(rpoEmsInfoEntity==null){
                RpoEmsInfoEntity emsInfoEntity = new RpoEmsInfoEntity();
                emsInfoEntity.setDsrbh(NumberUtil.isIntNotNullAndGtZero(kdtdEntity.getDsrbh()) ? kdtdEntity.getDsrbh() : 0);
                emsInfoEntity.setScrq(kdtdEntity.getScrq());
                emsInfoEntity.setYysdbh(NumberUtil.isIntNotNullAndGtZero(kdtdEntity.getYysdbh()) ? kdtdEntity.getYysdbh() : -kdtdEntity.getKdid());
                emsInfoEntity.setKdid(kdtdEntity.getKdid());
                emsInfoEntity.setSdybh(kdtdEntity.getSdybh());
                emsInfoEntity.setSdjg(kdtdEntity.getSdjg());
                emsInfoEntity.setSubmittime(new Date());
                emsInfoEntity.setSddz(kdtdEntity.getSjrdz());
                PubYysdSsdrModel pubYysdSsdrModel = null;
                if(null != kdtdEntity.getYysdbh() && null != kdtdEntity.getDsrbh()) {
                    pubYysdSsdrModel = pubYysdSsdrEntityMapper.findByPrimaryKey(kdtdEntity.getYysdbh(), kdtdEntity.getDsrbh());
                }
                if(null != pubYysdSsdrModel && null != pubYysdSsdrModel.getWsnum()) {
                    emsInfoEntity.setWsnum(pubYysdSsdrModel.getWsnum());
                }
                emsInfoEntityMapper.insertSelective(emsInfoEntity);
        }else {
            rpoEmsInfoEntity.setSdjg(kdtdEntity.getSdjg());
            rpoEmsInfoEntity.setSubmittime(new Date());
            rpoEmsInfoEntity.setSddz(kdtdEntity.getSjrdz());
            PubYysdSsdrModel pubYysdSsdrModel = null;
            if(null != kdtdEntity.getYysdbh() && null != kdtdEntity.getDsrbh()) {
                pubYysdSsdrModel = pubYysdSsdrEntityMapper.findByPrimaryKey(kdtdEntity.getYysdbh(), kdtdEntity.getDsrbh());
            }
            if(null != pubYysdSsdrModel && null != pubYysdSsdrModel.getWsnum()) {
                rpoEmsInfoEntity.setWsnum(pubYysdSsdrModel.getWsnum());
            }
            emsInfoEntityMapper.updateByPrimaryKeySelective(rpoEmsInfoEntity);
        }
    }
}
