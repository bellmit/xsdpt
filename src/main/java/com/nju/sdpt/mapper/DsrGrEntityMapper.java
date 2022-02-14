package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.DsrGrEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DsrGrEntityMapper {

    @Select("select * from DSR_GR where AJXH = #{ajxh} and DSRBH = #{dsrbh}")
    DsrGrEntity selectByPrimaryKey(@Param("ajxh") Integer ajxh, @Param("dsrbh") Integer dsrbh);
}