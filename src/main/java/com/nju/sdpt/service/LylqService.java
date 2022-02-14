package com.nju.sdpt.service;


import com.nju.sdpt.entity.PubLylqInfoEntity;
import com.nju.sdpt.entity.PubLylqSdhzEntity;
import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.model.LylqModel;
import com.nju.sdpt.model.WsModel;
import com.nju.sdpt.model.YysdModel;
import com.nju.sdpt.viewobject.LylqLoadListVo;
import com.nju.sdpt.viewobject.LylqUploadSdhzVo;

import java.util.List;

public interface LylqService {
    /**
     * 查询来院记录
     * @param lylqLoadListVo
     * @return
     */
    List<LylqModel> loadList(LylqLoadListVo lylqLoadListVo);
    /**
     * 查询来院记录
     * @param
     * @return
     */
    List<LylqModel> fgLoadList(String yhm, String fybh,int sdjg);

    /**
     * 新增来院记录
     * @param pubLylqInfoEntity
     * @param rytxxEntity
     */
    Integer addLylq(LylqModel pubLylqInfoEntity, PubYysdRyxxEntity rytxxEntity);

    /**
     * 新增来院记录(法官)
     * @param pubLylqInfoEntity
     */
    void addFgLylq(PubLylqInfoEntity pubLylqInfoEntity);
    /**
     * 根据用户名查询未结束的工单
     * @param yhm
     * @return
     */
    List<YysdModel> getYysdListByGdryxm(String yhm);

    List<WsModel> getYysdListByyysdbh(Integer yysdbh,Integer ssdrbh);
    List<YysdModel> getYysdList(Integer yysdbh);



    /**
     * 来院 - 上传送达回执 或者 编辑送达结果
     * @param lylqUploadSdhzVo
     */
    void uploadSdhz(LylqUploadSdhzVo lylqUploadSdhzVo);

    PubLylqInfoEntity getById(Integer lylqId);

    Integer savePubLylqInfoEntity(PubLylqInfoEntity pubLylqInfoEntity);

    /**
     * 通过 lylqId 获取 List<PubLylqSdhzEntity>
     * @param lylqId
     * @return
     */
    List<PubLylqSdhzEntity> getPubLylqSdhzEntityByLylqid(Integer lylqId);

}
