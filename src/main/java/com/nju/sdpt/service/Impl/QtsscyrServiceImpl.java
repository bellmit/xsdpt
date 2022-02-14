package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubQtsscyrEntity;
import com.nju.sdpt.entity.PubYysdSsdrQtsscyrEntity;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.mapper.PubQtsscyrEntityMapper;
import com.nju.sdpt.mapper.PubYysdSsdrQtsscyrEntityMapper;
import com.nju.sdpt.service.DmService;
import com.nju.sdpt.service.QtsscyrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QtsscyrServiceImpl implements QtsscyrService {
    @Autowired
    PubQtsscyrEntityMapper pubQtsscyrEntityMapper;//法综数据库的mapper
    @Autowired
    DmbMapper dmbMapper;
    @Autowired
    DmService dmService;
    @Autowired
    PubYysdSsdrQtsscyrEntityMapper pubYysdSsdrQtsscyrEntityMapper;//送达平台数据库的mapper

    @Override
    public List<PubQtsscyrEntity> getQtsscyr(Integer ajxh, Integer dsrbh) {
        List<PubQtsscyrEntity> result = new ArrayList<>();
        List<PubQtsscyrEntity> qtsscyrEntities = pubQtsscyrEntityMapper.getQtsscyr(ajxh,dsrbh);
        ArrayList<String> cyrlx = new ArrayList<String>(Arrays.asList("法定代理人","委托代理人","指定代理人","诉讼代表人","法定代表人"));//只推送这五种相关诉讼人
        for(PubQtsscyrEntity pubQtsscyrEntity:qtsscyrEntities){
            if(pubQtsscyrEntity.getQtsscyrlx()==null){
                continue;
            }
            String lxmc = dmService.getDmByLbbhAndDmbh("USR012-98",pubQtsscyrEntity.getQtsscyrlx()).getDmms();
            if(cyrlx.contains(lxmc)){
                pubQtsscyrEntity.setQtsscyrlx(lxmc);
                result.add(pubQtsscyrEntity);
            }
        }
        return result;
    }

    @Override
    public List<PubYysdSsdrQtsscyrEntity> getpubYysdSsdrQtsscyrEntitiesByYysdbh(Integer yysdbh) {
        return pubYysdSsdrQtsscyrEntityMapper.getByYysdbh(yysdbh);
    }
}
