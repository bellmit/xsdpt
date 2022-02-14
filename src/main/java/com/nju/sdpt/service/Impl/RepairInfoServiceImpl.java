package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubRepairInfoEntity;
import com.nju.sdpt.mapper.PubRepairInfoEntityMapper;
import com.nju.sdpt.model.RepairHandleOldDataModel;
import com.nju.sdpt.model.RepairInfoModel;
import com.nju.sdpt.service.RepairInfoService;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.RepairQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairInfoServiceImpl implements RepairInfoService {
    private final Logger logger = LoggerFactory.getLogger(RepairInfoServiceImpl.class);
    @Autowired
    PubRepairInfoEntityMapper repairInfoMapper;

    @Override
    public List<RepairInfoModel> loadList(RepairQueryVo queryVo) {
        if(null == queryVo){
            queryVo = new RepairQueryVo();
        }
        String gdryxm = queryVo.getGdryxm();
        if(StringUtil.isBlank(gdryxm)){
            queryVo.setGdryxm("null");
        }
        List<RepairInfoModel> resultList = repairInfoMapper.loadList(queryVo);
        if(null == resultList){
            resultList = new ArrayList<>();
        }
        return resultList;
    }

    @Override
    public void handle_old_data() {
        logger.info("开始同步历史修复记录");
        List<RepairHandleOldDataModel> resultList = repairInfoMapper.handle_old_data();
        if(null != resultList){
            logger.info("共查询到{}条修复数据需要同步",resultList.size());

            PubRepairInfoEntity infoEntity = null;
            for (RepairHandleOldDataModel model : resultList) {
                infoEntity = new PubRepairInfoEntity();
                infoEntity.setAh(model.getAh());
                infoEntity.setCreatetime(model.getGdyysj());
                infoEntity.setRepairstatus(model.getRepairstatus());
                infoEntity.setSsdrbh(model.getSsdrbh());
                infoEntity.setSsdrmc(model.getSsdrmc());
                infoEntity.setYysdbh(model.getYysdbh());
                infoEntity.setRepairbatchno(model.getRepairbatchno());
                repairInfoMapper.insertSelective(infoEntity);
            }
        }

        logger.info("同步完成");
    }
}
