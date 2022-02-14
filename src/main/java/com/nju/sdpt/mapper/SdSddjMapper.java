package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubSddjJbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SdSddjMapper {

    @Select("select * from PUB_SD_SDDJB where AJXH = #{ajxh}")
    List<PubSddjJbEntity> getPubSddjJbEntityListByAjxh(@Param("ajxh") Integer ajxh);
}
