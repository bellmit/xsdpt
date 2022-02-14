package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubBgscQzEntity;
import com.nju.sdpt.entity.PubBgscQzEntityWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface PubBgscQzMapper {


    @Select("select * from PUB_BGSC_QZ T where AJXH = #{ajxh} and DMBH = #{dmbh} and BH = #{bh}")
    PubBgscQzEntityWithBLOBs selectByPrimaryKey(@Param("ajxh") Integer ajxh,@Param("dmbh") String wslybh,@Param("bh") Integer qzbh);


    //找到所有当事人所有类型文书中最新的那条记录
    @Select(value = "select * from PUB_BGSC_QZ T where AJXH = #{ajxh} and DSRBH > -2 and DMBH != '196' and  RESULT='OK' AND BH IN (select max(BH) from PUB_BGSC_QZ Q where T.AJXH=Q.AJXH AND T.DMBH=Q.DMBH GROUP BY Q.AJXH,Q.DMBH,Q.DSRBH)")
    ArrayList<PubBgscQzEntity> getBgscQzByAjxh(@Param("ajxh") Integer ajxh);

    //找到当事人所有缴费通知书记录
    @Select(value = "select * from PUB_BGSC_QZ T where AJXH = #{ajxh} and DSRBH > -2 and  RESULT='OK' AND DMBH='196'")
    ArrayList<PubBgscQzEntity> getJftzBgscQzByAjxh(@Param("ajxh") Integer ajxh);

    @Select(value = "select * from PUB_BGSC_QZ where AJXH = #{ajxh} and FILEID = #{fileid} and BH = (select max(BH) from PUB_BGSC_QZ where AJXH = #{ajxh} and FILEID = #{fileid} )")
    PubBgscQzEntityWithBLOBs getByAjxhAndFileId(@Param("ajxh") Integer ajxh, @Param("fileid") String fileid);

    @Select("select * from PUB_BGSC_QZ T where AJXH IN (${ajxhs}) and DSRBH >-2 and  RESULT='OK' and BH IN (select max(BH) from PUB_BGSC_QZ Q where T.AJXH=Q.AJXH and T.DMBH=Q.DMBH GROUP BY Q.AJXH,Q.DMBH,Q.DSRBH)")
    ArrayList<PubBgscQzEntity> getBgscQzByAjxhs(@Param("ajxhs") String ajxhs);

    @Select("select * from PUB_BGSC_QZ where AJXH = #{ajxh} and DMBH = #{dmbh} and DSRBH = #{dsrbh} order by BH desc")
    List<PubBgscQzEntity> getByAjxhAndDmbhAndDsrbh(@Param("ajxh")Integer ajxh, @Param("dmbh")String wslybh, @Param("dsrbh")Integer dsrbh);

}