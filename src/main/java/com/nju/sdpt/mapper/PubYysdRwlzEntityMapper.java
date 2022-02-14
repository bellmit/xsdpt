package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubYysdRwlzEntity;

public interface PubYysdRwlzEntityMapper {
    int deleteByPrimaryKey(Integer bh);

    int insert(PubYysdRwlzEntity record);

    int insertSelective(PubYysdRwlzEntity record);

    PubYysdRwlzEntity selectByPrimaryKey(Integer bh);

    int updateByPrimaryKeySelective(PubYysdRwlzEntity record);

    int updateByPrimaryKey(PubYysdRwlzEntity record);
}