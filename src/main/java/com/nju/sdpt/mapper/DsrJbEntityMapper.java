package com.nju.sdpt.mapper;


import com.nju.sdpt.entity.DsrJbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DsrJbEntityMapper {


    @Select(value = "select * from DSR_JB where AJXH = #{ajxh} order by DSRBH")
    List<DsrJbEntity> getDsrjblistByAjxh(@Param( "ajxh") int ajxh);


    @Select(value = "select * from DSR_JB where AJXH = #{ajxh} and DSRBH = #{dsrbh}")
    DsrJbEntity getDsrjblistByAjxhAndDsrbh(@Param( "ajxh") int ajxh,@Param( "dsrbh") int dsrbh);


    @Select("select * from DSR_JB where AJXH in (${ajxhs}) order by AJXH,DSRBH")
    List<DsrJbEntity> getDsrjblistByAjxhs(@Param("ajxhs") String ajxhs);
}