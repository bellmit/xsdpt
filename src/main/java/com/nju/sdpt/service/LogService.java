package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubLogEntity;

import java.util.List;


public interface LogService {
    void addLog(int ajxh,String fybh,String targetname,int type,String content,String creater,Integer yysdbh);
    void insertLog(PubLogEntity pubLogEntity);
    List<PubLogEntity>  getLogByAjxhAndFybh(int ajxh, String fybh);

    List<PubLogEntity> getLogByYysdbh(int yysdbh);
}
