package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.XxxglEntity;
import com.nju.sdpt.mapper.XxxglEntityMapper;
import com.nju.sdpt.service.XxxService;
import com.nju.sdpt.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by XXT on 2019/5/24.
 */

/**
 * 信息项服务的实现
 *
 * @author byron
 */
@Service
@Slf4j
public class XxxServiceImpl implements XxxService {


    @Autowired
    private XxxglEntityMapper xxxglEntityMapper;

    /**
     * 根据表名和列名活动类别编号
     *
     * @param szb
     * @param szl
     * @param fybh
     */
    @Override
    public String getLbbh2016ByTableAndColumn(String szb, String szl, long fybh) {
        XxxglEntity xxx = xxxglEntityMapper.getXxxByTableAndColumn(szb, szl, fybh);
        return xxx.getSjxx2016();
    }

    @Override
    public String getLbbhByTableAndColumn(String szb, String szl, long fybh) {
        XxxglEntity xxx = xxxglEntityMapper.getXxxByTableAndColumn(szb, szl, fybh);
        return xxx.getSjxx();
    }

}
