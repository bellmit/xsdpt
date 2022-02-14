package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.DsrJgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DsrJgEntityMapper {


    @Select("select * from DSR_XP_JG where AJXH = #{ajxh} and DSRBH = #{dsrbh}")
    DsrJgEntity selectByPrimaryKey(@Param("ajxh") Integer ajxh, @Param("dsrbh") Integer dsrbh);


}