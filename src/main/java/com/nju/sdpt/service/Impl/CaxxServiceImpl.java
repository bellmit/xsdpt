package com.nju.sdpt.service.Impl;


import com.nju.sdpt.entity.PubCaxxEntity;
import com.nju.sdpt.mapper.PubCaxxEntityMapper;
import com.nju.sdpt.service.CaxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaxxServiceImpl implements CaxxService {
    @Autowired
    PubCaxxEntityMapper pubCaxxEntityMapper;
    @Override
    public List<PubCaxxEntity> getCaxxByYysdbh(Integer yysdbh) {
        return pubCaxxEntityMapper.selectByYysdbh(yysdbh);
    }
}
