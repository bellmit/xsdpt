package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubGroupEntity;
import com.nju.sdpt.entity.PubRwwtEntity;
import com.nju.sdpt.model.RwwtModel;

import java.util.List;

public interface RwwtService {
    /**
     * 添加新的委托任务
     */
     void addNewRwwt(Integer yysdbh,Integer ssdrbh,String wtfs,Integer wtrbh,Integer clrbh);

    List<PubGroupEntity> getGroupRyxx(String groupName,String fybh);

    List<RwwtModel> getRwwtByYysdbh(Integer yysdbh);

    List<RwwtModel> getRwwtByClrmcAndCljg(String yhmc,Integer cljg);

    void uploadRwwtjg(Integer rwwtid, String cljg);

    boolean deleteRwwt(Integer yysdbh, Integer ssdrbh);
}
