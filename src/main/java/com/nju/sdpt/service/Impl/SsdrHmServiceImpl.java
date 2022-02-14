package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubSsdrHmEntity;
import com.nju.sdpt.mapper.PubSsdrHmEntityMapper;
import com.nju.sdpt.service.SsdrHmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SsdrHmServiceImpl implements SsdrHmService {
    @Resource
    PubSsdrHmEntityMapper pubSsdrHmEntityMapper;

    @Override
    public int deleteByPrimaryKey(Integer bh) {
        return pubSsdrHmEntityMapper.deleteByPrimaryKey(bh);
    }

    @Override
    public int insert(PubSsdrHmEntity record) {
        return pubSsdrHmEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(PubSsdrHmEntity record) {
        return pubSsdrHmEntityMapper.insertSelective(record);
    }

    @Override
    public PubSsdrHmEntity selectByPrimaryKey(Integer bh) {
        return pubSsdrHmEntityMapper.selectByPrimaryKey(bh);
    }

    @Override
    public int updateByPrimaryKeySelective(PubSsdrHmEntity record) {
        return pubSsdrHmEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PubSsdrHmEntity record) {
        return pubSsdrHmEntityMapper.updateByPrimaryKey(record);
    }
}
