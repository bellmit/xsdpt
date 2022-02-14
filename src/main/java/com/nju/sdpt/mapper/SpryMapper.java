package com.nju.sdpt.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chikuo
 */
@Mapper
@Repository
public interface SpryMapper {


    @Select(value = "select XM from PUB_SPRY where SFCBR='Y' and AJXH=#{ajxh}")
    String getCbrByajxh(@Param("ajxh") Integer ajxh);

    @Select(value = "select top 1 XM from PUB_SPRY where FG='0' and AJXH=#{ajxh} ")
    String getSjyByAjxh(@Param("ajxh") Integer ajxh);
}
