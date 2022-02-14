package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubYysdSsdrdzEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface PubYysdSsdrdzEntityMapper {
    int deleteByPrimaryKey(Integer bh);

    int insert(PubYysdSsdrdzEntity record);

    int insertSelective(PubYysdSsdrdzEntity record);

    PubYysdSsdrdzEntity selectByPrimaryKey(Integer bh);

    int updateByPrimaryKeySelective(PubYysdSsdrdzEntity record);

    int updateByPrimaryKey(PubYysdSsdrdzEntity record);


//    @Select("SELECT COUNT(*) FROM PUB_YYSD_SSDRDZ WHERE SDP_NAME = #{sdpName} AND ID_CARD = #{idCard} AND DZ = #{dz}")
//    int countBySdpNameAndIdCardAndDz(@Param("sdpName") String sdpName, @Param("idCard") String idCard, @Param("dz") String dz);

    @Select("SELECT * FROM PUB_YYSD_SSDRDZ WHERE YYSDBH = ${yysdbh} AND SSDRBH = ${ssdrbh}")
    List<PubYysdSsdrdzEntity> selectByyysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select("SELECT DZ FROM PUB_YYSD_SSDRDZ WHERE YYSDBH = ${yysdbh} AND SSDRBH = ${ssdrbh} GROUP BY DZ")
    List<String> selectDzByyysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select(value =
            "     SELECT '送达中心确认' IS_CONFIRMED," +
                    "        DZ" +
                    " FROM PUB_YYSD_SSDRDZ" +
                    " WHERE EXISTS(SELECT 1" +
                    "               FROM PUB_WEB_CALL_INFO" +
                    "               WHERE PUB_YYSD_SSDRDZ.SSDRBH = SSDRBH AND PUB_YYSD_SSDRDZ.YYSDBH = YYSDBH AND CONFIRMADDRESS = PUB_YYSD_SSDRDZ.DZ)" +
                    " AND YYSDBH = #{yysdbh} AND SSDRBH = #{ssdrbh}" +
                    " UNION" +
                    " SELECT NULL IS_CONFIRMED," +
                    "        DZ" +
                    " FROM PUB_YYSD_SSDRDZ" +
                    " WHERE NOT EXISTS(SELECT 1" +
                    "               FROM PUB_WEB_CALL_INFO" +
                    "               WHERE PUB_YYSD_SSDRDZ.SSDRBH = SSDRBH AND PUB_YYSD_SSDRDZ.YYSDBH = YYSDBH AND CONFIRMADDRESS = PUB_YYSD_SSDRDZ.DZ)" +
                    " AND YYSDBH = #{yysdbh} AND SSDRBH = #{ssdrbh} and (DZLY not in ('HIS_CASE','HIS_YYSD') or DZLY is null)"
    )
    List<HashMap<String, String>> selectDzMapListByYysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

//    @Select("SELECT DZ,DZLY FROM PUB_YYSD_SSDRDZ WHERE YYSDBH = #{yysdbh} AND SSDRBH = #{ssdrbh} ORDER BY DZLY")
//    List<Map<String, String>> selectDzByyysdbhAndSsdrbhAndLable(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select("SELECT BH FROM PUB_YYSD_SSDRDZ")
    List<PubYysdSsdrdzEntity> getAddressBh();

    @SelectProvider(type = SqlProvider.class, method = "selectHisYYsdData")
    List<String> selectHisYYsdData(@Param("ssdrmc") String ssdrmc, @Param("sfzhm") String sfzhm, @Param("yysdbh") Integer yysdbh);

    @Select("(select dz.DZ from PUB_YYSD_JB jb,PUB_YYSD_SSDRDZ dz " +
            "where jb.YYSDBH = dz.YYSDBH and jb.AJXH = #{ajxh} and jb.FYBH = #{fybh} and dz.SSDRBH = #{dsrbh} and dz.DZLY = 'FGEDIT') " +
            "union (select distinct dz.DZ from PUB_YYSD_JB jb,PUB_YYSD_SSDRDZ dz,PUB_CAXX ca " +
            "where jb.YYSDBH = dz.YYSDBH and ca.AJXH = #{ajxh} and jb.FYBH = #{fybh} and dz.SSDRBH = #{dsrbh} and dz.DZLY = 'FGEDIT' " +
            "and jb.AJXH = -1 and ca.YYSDBH = jb.YYSDBH) ")
    List<String> getFgeditDsrdz(@Param("ajxh") Integer ajxh,@Param("fybh")  String fybh,@Param("dsrbh")  Integer dsrbh);

    class SqlProvider {
        public String selectHisYYsdData(@Param("ssdrmc") String ssdrmc, @Param("sfzhm") String sfzhm, @Param("yysdbh") Integer yysdbh) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT DISTINCT dz.DZ FROM PUB_YYSD_SSDRDZ dz ");
            sql.append(" LEFT JOIN PUB_YYSD_SSDR ssdr ON ssdr.YYSDBH = dz.YYSDBH AND ssdr.SSDRBH = dz.SSDRBH ");
            sql.append(" where (dz.DZLY IS NULL or dz.DZLY = 'FGEDIT') and (dz.label = null  or dz.label != 'disable') and ssdr.YYSDBH is not null ");//过滤历史数据
            sql.append(" and dz.CREATETIME > now() - interval '1 years'"); //查询创建实际那与当前时间差不大于1年的
            sql.append(" and ssdr.SSDRMC = '" + ssdrmc + "' AND ssdr.SFZHM = '" + sfzhm + "' and ssdr.YYSDBH != ").append(yysdbh);
            return sql.toString();
        }
    }
}