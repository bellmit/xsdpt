package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubWsStampLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubWsStampLogMapper {


    @Select(value = "select W.STAMP_GUID AS stampGuid ,W.FILE_GUID AS fileGuid  from PUB_WS_STAMP_LOG AS W where AJXH = #{ajxh} and WSJBBH = #{wsjbbh} and RESULT = 'OK' order by W.CZSJ DESC")
    List<PubWsStampLogEntity> selectByAjxhAndWsjbbh(@Param("ajxh") Integer ajxh, @Param("wsjbbh") Integer wsjbbh);


}