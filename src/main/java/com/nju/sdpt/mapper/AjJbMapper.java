package com.nju.sdpt.mapper;


import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.model.TsyysdAjxx;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AjJbMapper {


    @Select(value = "select PUB_AJ_JB.* from PUB_AJ_JB,PUB_SPRY where PUB_AJ_JB.AJXH = PUB_SPRY.AJXH and PUB_SPRY.XM=#{fgmc} and PUB_SPRY.SFCBR='Y' ")
    List<AjJbEntity> getCbajlb(@Param("fgmc") String fgmc);


    /**
     * 根据案件序号查找模型
     *
     * @param ajxh
     * @return
     */
    @Select(value = "select * from PUB_AJ_JB where AJXH=#{ajxh}")
    AjJbEntity findById(@Param("ajxh") int ajxh);


    @Select("select * from PUB_AJ_JB where AJXH in(${ajxhs}) order by AJXH")
    List<AjJbEntity> findByAjxhs(@Param("ajxhs") String ajxhs);


    @Select("select count(1) from MJZ_JB where AJXH=#{ajxh} and SFSC='Y'")
    Integer checkSfsc(@Param("ajxh") Integer ajxh);


    @Select("select * from PUB_AJ_JB where AH like #{ah}")
    List<AjJbEntity> findInLike(@Param("ah")String ah);

    @Select("select jb.AH,jb.AJMC,ay.LAAY,spry.FG,spry.SFCBR,spry.XM,yhb.YHMC,yhb.YHBH,dmb.DMMS,yhb.PHONE\n" +
            "from PUB_AJ_JB jb,PUB_LA_AY ay,PUB_SPRY spry,PUB_XTGL_YHB yhb,PUB_DMB dmb\n" +
            "where jb.AJXH=#{ajxh} and\n" +
            "      jb.AJXH=ay.AJXH and\n" +
            "      jb.AJXH=spry.AJXH and\n" +
            "      spry.XM=yhb.YHMC and\n" +
            "      dmb.LBBH='USR206-99' and\n" +
            "      dmb.DMBH=yhb.YHBM")
    List<TsyysdAjxx> findTsyysdAjxx(@Param("ajxh")int ajxh);

    @Select("select AJXH from PUB_AJ_JB where AH=#{ah}")
    int getAjxhByAh(@Param("ah")String ah);
}
