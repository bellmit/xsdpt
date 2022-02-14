package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author chikuo
 */
@Mapper
@Repository
public interface KdtdMapper {

    @Select(value = "select * from PUB_KDTD where KDID = #{kdid}")
    PubKdtdEntity findByKdid(@Param("kdid") Integer kdid);
    /**
     * 通过案号查找KDTD表信息
     * @param ah 案号
     * @return List<PubKdtdEntity>
     */

    @Select(value = "select * from PUB_KDTD where AH=#{ah}")
    List<PubKdtdEntity> findByAh(@Param("ah") String ah);

    @Select(value = "select PUB_KDTD.AH from PUB_AJ_JB, PUB_KDTD, PUB_SPRY where PUB_KDTD.AH=PUB_AJ_JB.AH and PUB_SPRY.XM=#{xm} and PUB_SPRY.SFCBR='Y' and PUB_SPRY.AJXH = PUB_AJ_JB.AJXH")
    List<String> getTargetJudgeAllAh(@Param("xm") String xm);


    @Select(value = "select * from PUB_KDTD where JJRXM = '送达中心' and SJRXM = #{ssdrmc} and AH in ( " +
            "    select AH from PUB_AJ_JB,DSR_GR " +
            "    where PUB_AJ_JB.AJXH = DSR_GR.AJXH " +
            "    and DSR_GR.XM=#{ssdrmc} and DSR_GR.SFZHM=#{sfzhm} " +
            "    )")
    List<PubKdtdEntity> findGdBySsdrmcAndSfzhm(@Param("ssdrmc") String ssdrmc,@Param("sfzhm") String sfzhm);

    @Update(value = "update PUB_KDTD set KDDH =#{kddh} where KDID = #{kdid}")
    void uploadKddhBykdid(@Param("kdid") Integer kdid, @Param("kddh") String kddh);

    @Select(value = "select * from PUB_KDTD where YYSDBH=#{yysdbh}")
    List<PubKdtdEntity> findGdByYysdbh(@Param("yysdbh") Integer yysdbh);

    @Select(value = "select * from PUB_KDTD where YYSDBH=#{yysdbh} and DYRQ>#{yysj}")
    List<PubKdtdEntity> findGdByYysdbhAndyysj(@Param("yysdbh") Integer yysdbh,@Param("yysj")String yysj);

    @SelectProvider(type = SqlProvider.class,method = "getWjidByFilename")
    List<String> getWjidByFilename(@Param("wjmc") String filename);

    @Select(value = "select WJMC from DZJZ_WD_WJ where WJID = #{wjid}")
    String getWjmcByWjid(@Param("wjid") String wjid);

    @Update(value = "update PUB_KDTD set SFSCMD = #{sfscmd} where KDID = #{kdid}")
    void updateKdmdStatus(@Param("kdid") Integer kdid, @Param("sfscmd") String sfscmd);

    @Update(value = "update PUB_KDTD set SFSCMD = #{sfscmd},WJID = #{wjid},WJMC = #{wjmc} where KDID = #{kdid}")
    void updateKdmdStatusByBh(@Param("kdid") Integer kdid, @Param("wjid") String wjid, @Param("wjmc") String wjmc, @Param("sfscmd") String sfscmd);

    @SelectProvider(type = SqlProvider.class, method = "getEmsInfo")
    List<PubKdtdEntity> getEmsInfo(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "findGdByYysdbhs")
    List<PubKdtdEntity> findGdByYysdbhs(@Param("yysdbhs") String yysdbhs);

    @Update(value = "update PUB_KDTD set KDHZ = #{kdhz} where KDID =#{kdid}")
    void uploadKdhzBykdid(@Param("kdid") Integer kdid, @Param("kdhz") byte[] bytes);

    @Update(value = "update PUB_KDTD set KDHZ = NULL where KDID = #{kdid}")
    boolean deleteKdhzBykdid(@Param("kdid") Integer kdid);

    @Delete(value = "delete from PUB_KDTD where KDID= #{kdid} ")
    boolean deleteKdtdBykdid(@Param("kdid")Integer kdid);

    @Update(value = "update PUB_KDTD set SDJG = #{sdjg} ,SDRQ = #{sdrq} where KDID =#{kdid}")
    boolean uploadSdjgByKdid(@Param("kdid") Integer kdid, @Param("sdjg") String sdjg,@Param("sdrq") Date date);

    @Update(value = "update PUB_KDTD set SDJG = NULL ,SDRQ = NULL where KDID =#{kdid}")
    boolean clearSdjgByKdid(@Param("kdid") Integer kdid);

    @Select(value = "select top 1 from PUB_KDTD where YYSDBH = #{yysdbh} and DSRBH = #{dsrbh} order by KDID desc")
    PubKdtdEntity findByYysdbhAndDsrbh(@Param("yysdbh") Integer yysdbh,@Param("dsrbh") Integer ssdrbh);

    @Select(value = "select * from PUB_KDTD where YYSDBH = #{yysdbh} and DSRBH = #{dsrbh} order by KDID desc")
    List<PubKdtdEntity> selectByYysdbhAndDsrbh(@Param("yysdbh") Integer yysdbh,@Param("dsrbh") Integer ssdrbh);


    @Select(value = "select wjmc from PUB_KDTD where KDID =#{kdid}")
    String getWjmc(@Param("kdid") Integer kdid);

    @Select(value = "select wjid from PUB_KDTD where KDID =#{kdid}")
    String getwjid(@Param("kdid") Integer kdid);

    @Select(value = "select jb.AJXH\n" +
            "from PUB_KDTD kdtd\n" +
            "left join PUB_AJ_JB jb on kdtd.AH=jb.AH\n" +
            "where kdtd.KDID=#{kdid}")
    Integer getAjxhByKdid(@Param("kdid") Integer kdid);

    @Select(value = "select *\n" +
            "from PUB_KDTD\n" +
            "where KDDH=#{kddh}")
    List<PubKdtdEntity> findKdtdByKddh(@Param("kddh")String kddh);

    @Select(value = "select max(KDID) from PUB_KDTD")
    int MaxKdid();

    void insertKdtd(PubKdtdEntity pubKdtdEntity);

    class SqlProvider {
        public String getEmsInfo(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from PUB_KDTD where JJRXM = '送达中心' ");
            if (StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())) {
                sql.append(" and  SCRQ >= '").append(queryVo.getStartTime()).append("' and SCRQ < '").append(queryVo.getEndTime()).append("'");
            }
            return sql.toString();
        }
        public String findGdByYysdbhs(@Param("yysdbhs") String yysdbhs) {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from PUB_KDTD where YYSDBH in (").append(yysdbhs).append(")");
            return sql.toString();
        }
        public String getWjidByFilename(@Param("wjmc") String filename) {
            StringBuilder sql = new StringBuilder();
            sql.append("select WJID from DZJZ_WD_WJ where WJMC like '"+filename+"%' order by CJSJ desc");
            return sql.toString();
        }
    }
}
