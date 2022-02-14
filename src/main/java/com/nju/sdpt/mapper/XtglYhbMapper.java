package com.nju.sdpt.mapper;


import com.nju.sdpt.entity.XtglYhbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XXT on 2019/5/8.
 */

@Mapper
@Repository
public interface XtglYhbMapper {

    @Select(value = "select * from PUB_XTGL_YHB where YHDM=#{yhdm}")
    XtglYhbEntity findByYhdm(@Param("yhdm") String yhdm);

    @Select(value = "select * from PUB_XTGL_YHB where YHBH=#{yhbh}")
    XtglYhbEntity findByyhbh(@Param("yhbh") long yhbh);

    @Select(value = "select  JS from PUB_XTGL_YHJSGXB GXB LEFT JOIN PUB_XTGL_JSB  JSB ON  GXB.JSBH=JSB.JSBH where GXB.YHBH = #{yhbh}")
    List<String> findJsByYhbh(@Param("yhbh")long yhbh);

    @Select(value = "select * from PUB_XTGL_YHB where YHMC=#{yhmc}")
    XtglYhbEntity findByYhdmc(@Param("yhmc") String yhmc);
}
