package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubCaxxEntity;
import com.nju.sdpt.entity.PubLogEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.mapper.PubCaxxEntityMapper;
import com.nju.sdpt.mapper.PubLogEntityMapper;
import com.nju.sdpt.mapper.PubYysdJbEntityMapper;
import com.nju.sdpt.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    PubLogEntityMapper pubLogEntityMapper;
    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;
    @Autowired
    PubCaxxEntityMapper pubCaxxEntityMapper;

    @Override
    public void addLog(int ajxh, String fybh,String targetname, int type, String content,String creater,Integer yysdbh) {
        if(ajxh==-1){
            List<PubCaxxEntity> pubCaxxEntityList = pubCaxxEntityMapper.selectByYysdbh(yysdbh);
            for (PubCaxxEntity pubCaxxEntity:pubCaxxEntityList){
                if(pubCaxxEntity.getAjxh()!=-1){//防止死循环
                    addLog(pubCaxxEntity.getAjxh(),fybh,targetname,type,content,creater,yysdbh);
                }
            }
        }
        PubLogEntity pubLogEntity = new PubLogEntity();
        pubLogEntity.setAjxh(ajxh);
        pubLogEntity.setFybh(fybh);
        pubLogEntity.setLogtype(type);
        pubLogEntity.setCreater(creater);
        pubLogEntity.setTargetname(targetname);
        pubLogEntity.setLogcontent(content);
        pubLogEntity.setCreattime(new Date());
        pubLogEntity.setYysdbh(yysdbh);
        synchronized (this){
            int logbh = pubLogEntityMapper.getSizeByAjxhAndFybh(ajxh,fybh)+1;
            pubLogEntity.setLogbh(logbh);
            pubLogEntityMapper.insert(pubLogEntity);
        }
    }

    @Override
    public void insertLog(PubLogEntity pubLogEntity) {
        pubLogEntityMapper.insertSelective(pubLogEntity);
    }

    @Override
    public List<PubLogEntity> getLogByAjxhAndFybh(int ajxh, String fybh) {
        return pubLogEntityMapper.getLogByAjxhAndFybh(ajxh,fybh);
    }

    @Override
    public List<PubLogEntity> getLogByYysdbh(int yysdbh) {
        PubYysdJbEntity pubYysdJbEntity = pubYysdJbEntityMapper.selectByPrimaryKey(yysdbh);
        List<PubLogEntity> pubLogEntities = new ArrayList<>();
        if(pubYysdJbEntity.getAjxh()!=-1){
            pubLogEntities = pubLogEntityMapper.selectByYysdbh(yysdbh);
        }else {
            //串案随便取案件序号查就行
            List<PubCaxxEntity> caxxEntityList = pubCaxxEntityMapper.selectByYysdbh(yysdbh);
            Integer ajxh = caxxEntityList.get(0).getAjxh();
            pubLogEntities = pubLogEntityMapper.selectCaLogByYysdbh(yysdbh,ajxh);
        }
        return pubLogEntities;
    }
}
