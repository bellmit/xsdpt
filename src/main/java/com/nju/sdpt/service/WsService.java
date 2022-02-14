package com.nju.sdpt.service;

import com.nju.sdpt.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WsService {

    PubBgscQzEntityWithBLOBs getBgscQzByPrimary(Integer ajxh, Integer wslybh, Integer qzbh);

    PubWsJbEntity getWsJbBykey(Integer ajxh, Integer wslybh);

    byte[] fetchStampFile(PubBgscQzEntityWithBLOBs bgscQzEntity) throws Exception;

    PubWsStampLogEntity fetchStampFile(Integer fileType, String stampId) throws Exception;

    PubWsStampLogEntity selectByAjxhAndWsjbbh(Integer ajxh, Integer wsjbbh);

    //异步调用文书下载
    void yysdWsQz(PubYysdJbEntity pubYysdJbEntity) throws Exception;

    void yysdWsQzCa(PubYysdJbEntity pubYysdJbEntity) throws Exception;

    boolean saveOrInsertYysdWsBatch(List<PubYysdWsEntity> entities);

    PubYysdWsEntity[] selectByYysdbh(Integer yysdbh);

    void reSaveWs(Integer yysdbh,Integer bh,byte[] bytes);

    List<String> selectWslbByYysdbhAndSsdrbh(Integer yysdbh,Integer ssdrbh);
}
