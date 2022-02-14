package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubYysdTjxxEntity;
import com.nju.sdpt.model.StatisticsFgModel;
import com.nju.sdpt.model.TjxxModel;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface StatisticsMapper {


    @Select(value = "select * from PUB_YYSD_TJXX where LX=#{lx}")
    PubYysdTjxxEntity getBylx(@Param("lx") String lx);

    @Select(value = "select count(*) from (select count(*) from PUB_YYSD_JB WHERE  YYSJ < #{newTime} and AJXH >0 and SDJG!='X' group by AJXH,FYBH) as a")
    int getNewNoCaCount(@Param("newTime") Date date);


    @Update(value = "update PUB_DXTZ_INFO set SENDSTATE = 1 WHERE SENDSTATE=0 AND FWZT =1")
    void updateSENDSTATE();

    @Update(value = "update PUB_YYSD_TJXX set TIME = #{time} ,TJCOUNT = #{tjcount} where LX = #{lx}")
    void updateCount(@Param("lx") String lx,@Param("time") Date time,@Param("tjcount") double value);

    @Select(value = "select count(*) from PUB_YYSD_SSDR where YYSDBH NOT IN (select YYSDBH from PUB_YYSD_JB where SDJG='X')")
    int findLjwtrc();

    @Select(value = "select count(*) from PUB_YYSD_SSDR where SDJG=1")
    int getLjsdrc();

    @Select(value = "select FYBH as name,count(*) as value from PUB_YYSD_JB where  YYSJ >= #{date} and (SDJG!='X' or SDJG is null) group by FYBH")
    List<TjxxModel> findYysdrws(@Param("date") Date date);

    @Select(value = "select FYBH as name,COUNT(*) as value from PUB_YYSD_JB  where  SDJG is NULL and NOT ( DHSD is NULL AND DZSD is NULL AND EMSSD is NULL AND GGSD is NULL AND ZJSD is NULL AND LYLQ is NULL AND WTSD is NULL AND ZHJSD is NULL AND DXSD is NULL)  group by FYBH")
    List<TjxxModel> findSdzrws(@Param("date")Date date);

    @Select(value = "select FYBH as name,count(*) as value from PUB_YYSD_JB  where  SDJG is not NULL and SDJG != 'X' and SDSJ >= #{date} GROUP BY FYBH")
    LinkedList<TjxxModel> findWcsdrws(@Param("date")Date date);

    @Select(value = "select SJWD as name,TJCOUNT as value from PUB_YYSD_TJXX where LX = '特定送达方式占比'")
    List<TjxxModel> findSdfsfb();

    @Select(value = "select count(*) from PUB_WEB_CALL_INFO where sdjg = 1")
    int getDhsdCount();

    @Select(value = "select count(*) from PUB_DXTZ_INFO where sdjg = 1")
    int getDxsdCount();

    @Select(value = "select count(*) from PUB_LYLQ_INFO where sdjg = 1")
    int getLylqCount();

    @Select(value = "select count(*) from PUB_ZJSD_INFO where zjsdjg = 1")
    int getZjsdCount();

    @Select(value = "select count(*) from PUB_KDTD where JJRXM = '送达中心' and SDJG = '成功'")
    int getEmsCount();

    @Update(value = "update PUB_YYSD_TJXX set TJCOUNT = #{tjcount},TIME = #{time} where LX = #{lx} and SJWD = #{sjwd}")
    void updateCountByLxAndSjwd(@Param("lx") String lx, @Param("sjwd")String sjwd,@Param("time") Date date,@Param("tjcount") double tjcount);

    @Select(value = "select count(*) from (select distinct YYSDBH,SSDRBH FROM PUB_REPAIR_INFO where CREATETIME>=#{date} and  CREATETIME<#{next}) AS a")
    Integer findSlrs(@Param("date") Date date,@Param("next")  Date nextMonths);

    @Select(value = "select count(*) from (select distinct YYSDBH,SSDRBH FROM PUB_REPAIR_INFO where CREATETIME>=#{date} and  CREATETIME<#{next} and REPAIRSTATUS='RPBS003') AS a")
    Integer findCdrs(@Param("date") Date date,@Param("next")  Date nextMonths);

    @Select(value = "select FYBH as name,count(*) as value from PUB_YYSD_JB where SDJG = 'Y' and SDSJ > #{date} group by FYBH")
    List<TjxxModel> sdcgsList(@Param("date") Date date);

    @Select(value = "select FYBH as name,count(*) as value from PUB_YYSD_JB where (SDJG = 'Y' or SDJG = 'N')  and SDSJ > #{date} group by FYBH")
    List<TjxxModel> sdsList(@Param("date") Date date);

    @Select(value = "SELECT count(0)\n" +
            "  FROM\n" +
            "  (\n" +
            "    SELECT\n" +
            "      AJXH,\n" +
            "      FYBH\n" +
            "    FROM\n" +
            "      (\n" +
            "        SELECT\n" +
            "          (CASE WHEN AJXH < 0 THEN caxx_AJXH ELSE AJXH END) AJXH,\n" +
            "          FYBH\n" +
            "        FROM (\n" +
            "               SELECT\n" +
            "                 jb.AJXH,\n" +
            "                 jb.FYBH,\n" +
            "                 caxx.AJXH                          caxx_AJXH\n" +
            "               FROM PUB_YYSD_JB as jb\n" +
            "                 LEFT JOIN PUB_CAXX caxx ON jb.YYSDBH = caxx.YYSDBH\n" +
            "               WHERE jb.SDJG != 'X' or SDJG = null \n" +
            "             ) a\n" +
            "      ) tmp  GROUP BY AJXH, FYBH\n" +
            "  )tmpMain")
    int findLjsdajs();

    @Select(value = "SELECT count(0)\n" +
            "  FROM\n" +
            "  (\n" +
            "    SELECT\n" +
            "      AJXH,\n" +
            "      FYBH\n" +
            "    FROM\n" +
            "      (\n" +
            "        SELECT\n" +
            "          (CASE WHEN AJXH < 0 THEN caxx_AJXH ELSE AJXH END) AJXH,\n" +
            "          FYBH\n" +
            "        FROM (\n" +
            "               SELECT\n" +
            "                 jb.AJXH,\n" +
            "                 jb.FYBH,\n" +
            "                 caxx.AJXH                          caxx_AJXH\n" +
            "               FROM PUB_YYSD_JB as jb\n" +
            "                 LEFT JOIN PUB_CAXX caxx ON jb.YYSDBH = caxx.YYSDBH\n" +
            "               WHERE jb.SDJG=#{sdjg} \n" +
            "             ) a\n" +
            "      ) tmp  GROUP BY AJXH, FYBH\n" +
            "  )tmpMain")
    int findLjsdAjsBySdjg(@Param("sdjg") String sdjg);

    @Select(value = "SELECT count(0) \n" +
            "FROM (\n" +
            "         SELECT  *\n" +
            "         FROM (\n" +
            "                  SELECT (CASE WHEN AJXH < 0 THEN caxx_AJXH ELSE AJXH END) AJXH,\n" +
            "                         FYBH\n" +
            "                  FROM (\n" +
            "                           SELECT jb.AJXH,\n" +
            "                                  jb.FYBH,\n" +
            "                           caxx.AJXH       caxx_AJXH\n" +
            "                           FROM PUB_YYSD_JB as jb\n" +
            "                                    LEFT JOIN PUB_CAXX caxx ON jb.YYSDBH = caxx.YYSDBH\n" +
            "                           WHERE jb.SDJG = 'N'\n" +
            "                       ) a\n" +
            "               where not exists(    select * from (\n" +
            "                                   SELECT (CASE WHEN AJXH2 < 0 THEN caxx_AJXH2 ELSE AJXH2 END) AJXH,\n" +
            "                                          FYBH2 FYBH\n" +
            "                                   FROM (\n" +
            "                                            SELECT jb2.AJXH                           AJXH2,\n" +
            "                                                   jb2.FYBH                           FYBH2,\n" +
            "                                                   caxx2.AJXH                         caxx_AJXH2\n" +
            "                                            FROM PUB_YYSD_JB as jb2\n" +
            "                                                     LEFT JOIN PUB_CAXX caxx2 ON jb2.YYSDBH = caxx2.YYSDBH\n" +
            "                                            WHERE jb2.SDJG = 'Y'\n" +
            "                                        ) b\n" +
            "                     )bb where bb.AJXH=a.AJXH and bb.FYBH=a.FYBH \n" +
            "                   )\n" +
            "              ) tmp\n" +
            "\n" +
            "         GROUP BY AJXH, FYBH\n" +
            "     ) tmpMain")
    int findLjsdAjsSdsb();

    Integer getGdl(@Param("start") Timestamp start, @Param("end")Timestamp end, @Param("fybh")String fybh);

    Integer getCgGdl(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getSbGdl(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getLjSa(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getCgAj(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getSbAj(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getLjSsdr(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getCgSsdr(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getSbSsdr(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Double getAjpjSdzq(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Double getDzpjSdzq(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Double getYjpjSdzq(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Double getLypjSdzq(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Double getZjpjSdzq(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getDxxf(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Double getDxljfwl(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getDhwhbdcs(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getJts(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getYjjs(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getWssl(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getXfs(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    Integer getXfcgs(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    List<StatisticsFgModel> getStatisticsFgModel(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    @Select(value = "SELECT count(*)\n" +
            "FROM pub_yysd_jb WHERE yysj>#{start} and yysj<#{end} and fybh=#{fybh}")
    Integer getTsggsl(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    @Select(value = "SELECT count(*)\n" +
            "FROM pub_yysd_jb WHERE (sdjg='N' or sdjg='Y') and sdsj>#{start} and sdsj<#{end} and fybh=#{fybh}")
    Integer getWcggsl(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    @Select(value = "SELECT count(*)\n" +
            "FROM pub_zjsd_info WHERE cjsj>#{start} and cjsj<#{end} and fybh=#{fybh}")
    Integer getFqzjsd(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);

    @Select(value = "SELECT count(*)\n" +
            "FROM pub_zjsd_info WHERE smsj>#{start} and smsj<#{end} and smsj is not null and fybh=#{fybh}")
    Integer getWczjsd(@Param("start")Timestamp start,@Param("end")Timestamp end,@Param("fybh")String fybh);
}
