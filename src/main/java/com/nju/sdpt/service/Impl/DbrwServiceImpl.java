package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubDbrwInfoEntity;
import com.nju.sdpt.mapper.PubDbrwInfoEntityMapper;
import com.nju.sdpt.service.DbrwService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DbrwServiceImpl implements DbrwService {
    @Resource
    PubDbrwInfoEntityMapper pubDbrwInfoEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pubDbrwInfoEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PubDbrwInfoEntity record) {
        pubDbrwInfoEntityMapper.insert(record);
        return record.getId();
    }

    @Override
    public int insertSelective(PubDbrwInfoEntity record) {
        pubDbrwInfoEntityMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public PubDbrwInfoEntity selectByPrimaryKey(Integer id) {
        return pubDbrwInfoEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PubDbrwInfoEntity record) {
        return pubDbrwInfoEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PubDbrwInfoEntity record) {
        return pubDbrwInfoEntityMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<PubDbrwInfoEntity> dbrwList(String clzt, Integer yhbh, String xzsdfs) {
        return pubDbrwInfoEntityMapper.dbrwList(clzt,yhbh,xzsdfs);
    }

    @Override
    public Boolean updateLylqById(String xq, Integer id) {
        return pubDbrwInfoEntityMapper.updateLylqById(xq,id);
    }

    @Override
    public Boolean updatestatusById(Integer id) {
        return pubDbrwInfoEntityMapper.updatestatusById(id);
    }
}
