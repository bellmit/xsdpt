package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubWsJbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface PubWsJbEntityMapper {


    @Select("select * from PUB_WS_JB where AJXH = #{ajxh} and WSJBBH = #{wsjbbh}")
    PubWsJbEntity selectByPrimaryKey(@Param("ajxh") Integer ajxh, @Param("wsjbbh") Integer wsjbbh);

    @Select(value = "select * from PUB_WS_JB where AJXH = #{ajxh} and WSJBBH in (select WSJBBH from PUB_WS_STAMP_LOG where AJXH = #{ajxh})")
    ArrayList<PubWsJbEntity> getPubWsjbStampedByAjxh(@Param("ajxh") Integer ajxh);


    @Select(value = "select * from PUB_WS_JB where AJXH = #{ajxh}")
    ArrayList<PubWsJbEntity> getByAjxh(@Param("ajxh") Integer ajxh);

    @Select("select * from PUB_WS_JB where AJXH IN ( ${ajxhs} ) and WSJBBH in (select WSJBBH from PUB_WS_STAMP_LOG where AJXH IN ( ${ajxhs} ))")
    ArrayList<PubWsJbEntity> getPubWsjbStampedByAjxhs(@Param("ajxhs") String ajxhs);

}