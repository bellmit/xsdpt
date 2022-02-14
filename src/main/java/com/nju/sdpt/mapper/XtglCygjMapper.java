package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.XtglCygjbEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by XXT on 2019/5/15.
 */
@Mapper
@Repository
public interface XtglCygjMapper {

    /**
     * 根据工具名称获取常用工具
     * @param gjmc
     * @return
     */
    @Select(value = "select * from PUB_XTGL_CYGJB where GJMC = #{gjmc}")
    XtglCygjbEntity getCygjByGjmc(@Param("gjmc") String gjmc);

}
