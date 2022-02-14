package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubGgxxEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author chikuo
 */
@Mapper
@Repository
public interface GgxxMapper {

    /**
     * 通过案件序号查询公告消息
     * @param ajxh 案件序号
     * @return List<PubGgxxEntity>
     */
    @Select(value = "select * from PUB_GGXX where AJXH=#{ajxh} and FYBH=#{fybh}")
    List<PubGgxxEntity> findByAjxh(@Param("ajxh") Integer ajxh, @Param("fybh") String fybh);


    @Select(value = "select AJXH from PUB_GGXX where DJR=#{djr} and FYBH=#{fybh}")
    List<Integer> findByDjr(@Param("djr") String djr, @Param("fybh") String fybh);

}
