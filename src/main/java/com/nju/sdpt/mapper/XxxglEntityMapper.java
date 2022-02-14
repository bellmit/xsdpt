package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.XxxglEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface XxxglEntityMapper {



    @Select(value = "select * from PUB_XTGL_XXXGL where SZB = #{szb} AND SZL = #{szl} AND FYBH = #{fybh}")
    XxxglEntity getXxxByTableAndColumn(@Param("szb") String szb,@Param("szl") String szl,@Param("fybh") long fybh);
}