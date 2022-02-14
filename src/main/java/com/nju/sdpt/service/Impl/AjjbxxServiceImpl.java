package com.nju.sdpt.service.Impl;

/**
 * Created by XXT on 2019/5/24.
 */

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.mapper.AjJbMapper;
import com.nju.sdpt.mapper.SpryMapper;
import com.nju.sdpt.model.AjjbxxModel;
import com.nju.sdpt.model.TsyysdAjxx;
import com.nju.sdpt.service.AjjbxxService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 民事一审立案信息服务的实现
 *
 */
@Service
public class AjjbxxServiceImpl implements AjjbxxService {

    @Autowired
    private AjJbMapper ajJbMapper;

    @Autowired
    private SpryMapper spryMapper;


    /**
     * 获取某个法官的承办案件列表
     * @return
     */
    @Override
    public List<AjjbxxModel> getCbajlb(String fgmc) {

        List<AjJbEntity> cbajlb = ajJbMapper.getCbajlb(fgmc);
        ArrayList<AjjbxxModel> ajjbxxModels = new ArrayList<>();
        for( AjJbEntity ajJbDO : cbajlb){
            AjjbxxModel ajjbxxModel = new AjjbxxModel(ajJbDO);
            ajjbxxModel.setCbr(fgmc);

            ajjbxxModels.add(ajjbxxModel);
        }
        return ajjbxxModels;
    }

    /**
     * 根据案件序号获得案件基本信息模型的列表
     *
     * @param ajxh 案件序号
     * @return AjjbxxModel 案件基本信息模型的列表
     */
    public AjjbxxModel getAjjbxxByAjxh(Integer ajxh) {
        AjJbEntity ajjbDO = ajJbMapper.findById((int) ajxh);
        if (ajjbDO == null) {
            return null;
        }
        AjjbxxModel ajjbxxModel = new AjjbxxModel(ajjbDO);

        return ajjbxxModel;
    }

    /**
     * 特殊送达案件信息获取
     * @param ajxh
     * @return
     */
    public List<TsyysdAjxx> getTsyysdAjxx(Integer ajxh){
        return ajJbMapper.findTsyysdAjxx(ajxh);
    }


    /**
     * 模糊搜索案件
     * @param ah
     * @return
     */
    @Override
    public List<AjjbxxModel> getAjjbInLike(String ah){
        String rule;
        // 处理模糊搜索的情况
        if(ah.contains(",")){
            rule="";
            String[] content=ah.split(",");
            for(String c:content){
                rule+="%"+c+"%";
            }
        }else{
            rule="%"+ah+"%";
        }
        // 模糊搜索
        List<AjJbEntity> ajJbEntities=ajJbMapper.findInLike(rule);
        if(ajJbEntities==null){
            return null;
        }
        List<AjjbxxModel> ajjbxxModels=new ArrayList<>();
        for(AjJbEntity ajJbEntity:ajJbEntities){
            ajjbxxModels.add(new AjjbxxModel(ajJbEntity));
        }
        return ajjbxxModels;
    }




    @Override
    public String getCbr(Integer ajxh) {
        return spryMapper.getCbrByajxh(ajxh);

    }

    @Override
    public List<AjJbEntity> getAjjbByAjxhs(String ajxhs) {
        return ajJbMapper.findByAjxhs(ajxhs);
    }

    @Override
    public Integer checkSfsc(Integer ajxh) {
        return ajJbMapper.checkSfsc(ajxh);
    }

}
