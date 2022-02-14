package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface PatchMapper {
    @Select(value = "select * from PUB_YYSD_JB where FYBH = '74' and  KTSJ='' ")
    List<PubYysdJbEntity> executeSql();

    @Update(value = "update PUB_YYSD_JB set KTSJ = #{RQ} where YYSDBH =#{yysdbh} ")
    void executeSql2(@Param("yysdbh") int i,@Param("RQ")  Date rq);

    @Update("update PUB_KDTD set SDYBH=#{sdybh} where KDID = #{kdid}")
    void executeSql3(@Param("kdid")int kdid, @Param("sdybh")Integer sdybh);
}
