package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubDjSdxxEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DjsdxxMapper {

    /**
     * 通过案件序号查询PUB_DJ_SDXX表信息
     * @param ajxh 案件序号
     * @param fybh
     * @return  List<PubDjSdxxEntity>
     */
    @Select(value = "select * from PUB_DJ_SDXX where AJXH=#{ajxh}")
    @Cacheable(value = "djsdxx",key="#ajxh+'_'+#fybh")
    List<PubDjSdxxEntity> getDzsdInfo(@Param("ajxh") Integer ajxh, String fybh);

    @Select(value = "select PUB_DJ_SDXX.AJXH from PUB_AJ_JB, PUB_DJ_SDXX, PUB_SPRY where PUB_DJ_SDXX.AJXH=PUB_AJ_JB.AJXH and PUB_SPRY.XM=#{xm} and PUB_SPRY.SFCBR='Y' and PUB_SPRY.AJXH = PUB_AJ_JB.AJXH")
    List<Integer> getTargetJudgeAllAjxh(@Param("xm") String xm);

}
