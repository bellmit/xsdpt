package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.DsrDwEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DsrDwEntityMapper {

    @Select("select * from DSR_DW where AJXH = #{ajxh} and DSRBH = #{dsrbh}")
    DsrDwEntity selectByPrimaryKey(@Param("ajxh") Integer ajxh, @Param("dsrbh") Integer dsrbh);
}