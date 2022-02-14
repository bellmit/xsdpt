package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubSsdrQrxxEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.mapper.PubSsdrQrxxEntityMapper;
import com.nju.sdpt.service.SsdrQrxxService;
import com.nju.sdpt.viewobject.PubSsdrQrxxEntityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SsdrQrxxServiceImpl implements SsdrQrxxService {
    @Autowired
    private PubSsdrQrxxEntityMapper pubSsdrQrxxEntityMapper;


    @Override
    public void save(Integer ssdrbh, Integer ajxh, String fybh) {
        PubSsdrQrxxEntity pubSsdrQrxxEntity = null;
        if(!pubSsdrQrxxEntityMapper.findByAjxhAndFybhAndDsrbh(ajxh,fybh,ssdrbh).isEmpty()){
            pubSsdrQrxxEntity=pubSsdrQrxxEntityMapper.findByAjxhAndFybhAndDsrbh(ajxh,fybh,ssdrbh).get(0);
        }
        if(pubSsdrQrxxEntity==null){
            PubSsdrQrxxEntity pubSsdrQrxxEntity1 = new PubSsdrQrxxEntity();
            pubSsdrQrxxEntity1.setAjxh(ajxh);
            pubSsdrQrxxEntity1.setFybh(fybh);
            pubSsdrQrxxEntity1.setDsrbh(ssdrbh);
            pubSsdrQrxxEntityMapper.insert(pubSsdrQrxxEntity1);


        }
    }

    @Override
    public PubSsdrQrxxEntity findByAjxhAndFybhAndDsrbh(Integer ajxh, String fybh, Integer ssdrbh) {
        if(pubSsdrQrxxEntityMapper.findByAjxhAndFybhAndDsrbh(ajxh,fybh,ssdrbh).isEmpty()){
            return null;
        }
        return pubSsdrQrxxEntityMapper.findByAjxhAndFybhAndDsrbh(ajxh,fybh,ssdrbh).get(0);
    }

    @Override
    public void qsqrsUpdate(PubSsdrQrxxEntityVO pubSsdrQrxxEntityVO) {
        if(pubSsdrQrxxEntityMapper.findByAjxhAndFybhAndDsrbh(pubSsdrQrxxEntityVO.getAjxh(),pubSsdrQrxxEntityVO.getFybh(),pubSsdrQrxxEntityVO.getDsrbh()).isEmpty()){
            save(pubSsdrQrxxEntityVO.getDsrbh(),pubSsdrQrxxEntityVO.getAjxh(),pubSsdrQrxxEntityVO.getFybh());
        }
        pubSsdrQrxxEntityMapper.qsqrsUpdate(pubSsdrQrxxEntityVO.getAjxh(),pubSsdrQrxxEntityVO.getFybh(),pubSsdrQrxxEntityVO.getDsrbh(),pubSsdrQrxxEntityVO.getSfqssddzqrs());
    }

    @Override
    public void tydzsdUpdate(PubSsdrQrxxEntityVO pubSsdrQrxxEntityVO) {
        if(pubSsdrQrxxEntityMapper.findByAjxhAndFybhAndDsrbh(pubSsdrQrxxEntityVO.getAjxh(),pubSsdrQrxxEntityVO.getFybh(),pubSsdrQrxxEntityVO.getDsrbh()).isEmpty()){
            save(pubSsdrQrxxEntityVO.getDsrbh(),pubSsdrQrxxEntityVO.getAjxh(),pubSsdrQrxxEntityVO.getFybh());
        }
        pubSsdrQrxxEntityMapper.tydzsdUpdate(pubSsdrQrxxEntityVO.getAjxh(),pubSsdrQrxxEntityVO.getFybh(),pubSsdrQrxxEntityVO.getDsrbh(),pubSsdrQrxxEntityVO.getSftydzsd());
    }
}
