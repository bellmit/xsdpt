package com.nju.sdpt.service;

/**
 * Created by XXT on 2019/5/24.
 */


import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.model.AjjbxxModel;
import com.nju.sdpt.model.TsyysdAjxx;

import java.util.List;

/**
 *
 * 案件基本信息服务
 * @author cyx
 *
 */
public interface AjjbxxService {


    /**
     * 获取某个法官的承办案件列表
     * @return
     */
    public List<AjjbxxModel> getCbajlb(String fgmc);

    /**
     * 根据案件序号获得案件基本信息模型的列表
     *
     * @param ajxh 案件序号
     * @return AjjbxxModel 案件基本信息模型的列表
     */
    AjjbxxModel getAjjbxxByAjxh(Integer ajxh);


    String getCbr(Integer ajxh);


    List<AjJbEntity> getAjjbByAjxhs(String ajxhs);

    //检查是否是速裁案件
    Integer checkSfsc(Integer ajxh);

    List<AjjbxxModel> getAjjbInLike(String ah);

    List<TsyysdAjxx> getTsyysdAjxx(Integer ajxh);


}
