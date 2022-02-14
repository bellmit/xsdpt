package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubYysdCsxxxEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiaweiq
 * @date 2021/7/26 14:25
 */
@Mapper
@Repository
public interface PubYysdCsxxxMapper {


    void insertOrUpdate(@Param(value = "PubYysdCsxxxEntities") List<PubYysdCsxxxEntity> PubYysdCsxxxEntities);

    @Select(value = "select count(t.*)\n" +
            "from (\n" +
            "         select t.yysdbh                     yysdbh,\n" +
            "                t.yysj                       yysj,\n" +
            "                t.yysj::timestamp + '2 day'  dhcsxddl,\n" +
            "                t.yysj::timestamp + '10 day' yjcsxddl,\n" +
            "                t.yysj::timestamp + '14 day' csxddl,\n" +
            "                t.yhdm                       yhdm,\n" +
            "                t.jssj                       jssj,\n" +
            "                t.sdjg                       sdjg,\n" +
            "                t.sfdhsd                     sfdhsd,\n" +
            "                t.webcallcreatetime          webcallcreatetime,\n" +
            "                t.sfyjsd                     sfyjsd,\n" +
            "                t.emssubmittime              emssubmittime,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN t.sfdhsd = 'Y' and datediff(yysj, webcallcreatetime) > 2 THEN 'Y'\n" +
            "                        ELSE 'N'\n" +
            "                        END\n" +
            "                    ) as                     sfdhcsx,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN t.sfyjsd = 'Y' and datediff(yysj, emssubmittime) > 10 THEN 'Y'\n" +
            "                        ELSE 'N'\n" +
            "                        END\n" +
            "                    ) as                     sfyjcsx,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN datediff(yysj, jssj) > 14 THEN 'Y'\n" +
            "                        ELSE 'N'\n" +
            "                        END\n" +
            "                    ) as                     sfcsx\n" +
            "         from (\n" +
            "                  select jb.yysdbh              as yysdbh,\n" +
            "                         jb.yysj                as yysj,\n" +
            "                         jb.gdryxm              as yhdm,\n" +
            "                         (\n" +
            "                             CASE\n" +
            "                                 WHEN jb.sdsj IS NOT NULL THEN jb.sdsj\n" +
            "                                 ELSE getdate()\n" +
            "                                 END\n" +
            "                             )                  as jssj,\n" +
            "                         jb.sdjg                as sdjg,\n" +
            "                         (\n" +
            "                             CASE\n" +
            "                                 WHEN webcallinfo.sfdhsd IS NOT NULL THEN webcallinfo.sfdhsd\n" +
            "                                 ELSE 'N'\n" +
            "                                 END\n" +
            "                             )                  as sfdhsd,\n" +
            "                         webcallinfo.createtime as webcallcreatetime,\n" +
            "                         (\n" +
            "                             CASE\n" +
            "                                 WHEN ems.scrq IS NOT NULL THEN 'Y'\n" +
            "                                 ELSE 'N'\n" +
            "                                 END\n" +
            "                             )                  as sfyjsd,\n" +
            "                         (\n" +
            "                             CASE\n" +
            "                                 WHEN ems.scrq IS NOT NULL and ems.submittime IS NOT NULL THEN ems.submittime\n" +
            "                                 WHEN ems.scrq IS NOT NULL and ems.submittime IS NULL THEN getdate()\n" +
            "                                 END\n" +
            "                             )                  as emssubmittime\n" +
            "                  from pub_yysd_jb jb\n" +
            "                           left join (\n" +
            "                      select yysdbh, MIN(createtime) createtime, 'Y' as sfdhsd\n" +
            "                      from pub_web_call_info\n" +
            "                      group by yysdbh\n" +
            "                  ) webcallinfo on jb.yysdbh = webcallinfo.yysdbh\n" +
            "                           left join rpo_ems_info ems on jb.yysdbh = ems.yysdbh\n" +
            "                  where jb.sdjg != 'X'\n" +
            "              ) as t\n" +
            "     ) as t")
    Integer findNum();

    @Select("select t.yysdbh                     yysdbh,\n" +
            "       t.yysj                       yysj,\n" +
            "       t.yysj::timestamp + '2 day'  dhcsxddl,\n" +
            "       t.yysj::timestamp + '10 day' yjcsxddl,\n" +
            "       t.yysj::timestamp + '14 day' csxddl,\n" +
            "       t.yhdm                       yhdm,\n" +
            "       t.jssj                       jssj,\n" +
            "       t.sdjg                       sdjg,\n" +
            "       t.sfdhsd                     sfdhsd,\n" +
            "       t.webcallcreatetime          webcallcreatetime,\n" +
            "       t.sfyjsd                     sfyjsd,\n" +
            "       t.emssubmittime              emssubmittime,\n" +
            "       (\n" +
            "           CASE\n" +
            "               WHEN t.sfdhsd = 'Y' and datediff(yysj, webcallcreatetime) > 2 THEN 'Y'\n" +
            "               ELSE 'N'\n" +
            "               END\n" +
            "           ) as                     sfdhcsx,\n" +
            "       (\n" +
            "           CASE\n" +
            "               WHEN t.sfyjsd = 'Y' and datediff(yysj, emssubmittime) > 10 THEN 'Y'\n" +
            "               ELSE 'N'\n" +
            "               END\n" +
            "           ) as                     sfyjcsx,\n" +
            "       (\n" +
            "           CASE\n" +
            "               WHEN datediff(yysj, jssj) > 14 THEN 'Y'\n" +
            "               ELSE 'N'\n" +
            "               END\n" +
            "           ) as                     sfcsx\n" +
            "from (\n" +
            "         select jb.yysdbh              as yysdbh,\n" +
            "                jb.yysj                as yysj,\n" +
            "                jb.gdryxm              as yhdm,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN jb.sdsj IS NOT NULL THEN jb.sdsj\n" +
            "                        ELSE getdate()\n" +
            "                        END\n" +
            "                    )                  as jssj,\n" +
            "                jb.sdjg                as sdjg,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN webcallinfo.sfdhsd IS NOT NULL THEN webcallinfo.sfdhsd\n" +
            "                        ELSE 'N'\n" +
            "                        END\n" +
            "                    )                  as sfdhsd,\n" +
            "                webcallinfo.createtime as webcallcreatetime,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN ems.scrq IS NOT NULL THEN 'Y'\n" +
            "                        ELSE 'N'\n" +
            "                        END\n" +
            "                    )                  as sfyjsd,\n" +
            "                (\n" +
            "                    CASE\n" +
            "                        WHEN ems.scrq IS NOT NULL and ems.submittime IS NOT NULL THEN ems.submittime\n" +
            "                        WHEN ems.scrq IS NOT NULL and ems.submittime IS NULL THEN getdate()\n" +
            "                        END\n" +
            "                    )                  as emssubmittime\n" +
            "         from pub_yysd_jb jb\n" +
            "                  left join (\n" +
            "             select yysdbh, MIN(createtime) createtime, 'Y' as sfdhsd\n" +
            "             from pub_web_call_info\n" +
            "             group by yysdbh\n" +
            "         ) webcallinfo on jb.yysdbh = webcallinfo.yysdbh\n" +
            "                  left join rpo_ems_info ems on jb.yysdbh = ems.yysdbh\n" +
            "         where jb.sdjg != 'X'\n" +
            "     ) as t\n" +
            "order by t.yysdbh\n" +
            "limit #{limit}\n" +
            "    offset #{offset}")
    List<PubYysdCsxxxEntity> findInLimit(@Param("limit")Integer limit,@Param("offset")Integer offset);

    // 统计超审限数量
    @Select(value = "select count(csxxx.yysdbh)\n" +
            "from pub_yysd_csxxx csxxx\n" +
            "left join pub_yysd_jb jb on csxxx.yysdbh=jb.yysdbh\n" +
            "where csxxx.sfcsx='Y'\n" +
            "and jb.gdryxm=#{yhdm}\n" +
            "and csxxx.csxddl>'${start}'"+
            "and csxxx.csxddl<'${end}'")
    Integer StatisticsCSX(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // 统计电话超审限数量
    @Select(value = "select count(csxxx.yysdbh)\n" +
            "from pub_yysd_csxxx csxxx\n" +
            "left join pub_yysd_jb jb on csxxx.yysdbh=jb.yysdbh\n" +
            "where csxxx.sfdhcsx='Y'\n" +
            "and jb.gdryxm=#{yhdm}\n" +
            "and csxxx.dhcsxddl>'${start}'"+
            "and csxxx.dhcsxddl<'${end}'")
    Integer StatisticsDHCSX(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // 统计邮寄超审限数量
    @Select(value = "select count(csxxx.yysdbh)\n" +
            "from pub_yysd_csxxx csxxx\n" +
            "left join pub_yysd_jb jb on csxxx.yysdbh=jb.yysdbh\n" +
            "where csxxx.sfyjcsx='Y'\n" +
            "and jb.gdryxm=#{yhdm}\n" +
            "and csxxx.yjcsxddl>'${start}'"+
            "and csxxx.yjcsxddl<'${end}'")
    Integer StatisticsYJCSX(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // 获取短信的发送的总量
    @Select(value = "select count(dxtz.dxtzid)\n" +
            "from pub_dxtz_info dxtz\n" +
            "left join pub_yysd_jb jb on dxtz.yysdbh=jb.yysdbh\n" +
            "where dxtz.createtime>'${start}'"+
            "and dxtz.createtime<'${end}'"+
            "and jb.gdryxm=#{yhdm}"+
            "group by jb.gdryxm")
    Integer getDxl(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // 获取带有链接短信的发送的总量
    @Select(value = "select count(dxtz.dxtzid)\n" +
            "from pub_dxtz_info dxtz\n" +
            "left join pub_yysd_jb jb on dxtz.yysdbh=jb.yysdbh\n" +
            "where dxtz.fwzt is not null\n" +
            "and dxtz.createtime>'${start}'"+
            "and dxtz.createtime<'${end}'"+
            "and jb.gdryxm=#{yhdm}"+
            "group by jb.gdryxm")
    Integer getYljDxl(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // 获取来院领取的人数
    @Select(value = "select count(lylq.yysdbh)\n" +
            "from pub_lylq_info lylq\n" +
            "left join pub_yysd_jb jb on lylq.yysdbh=jb.yysdbh\n" +
            "where jb.gdryxm=#{yhdm}\n" +
            "and lylq.create_time>'${start}'"+
            "and lylq.create_time<'${end}'"+
            "group by jb.gdryxm")
    Integer getLylqCX(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    @Select(value = "select sum(num)\n" +
            "from (\n" +
            "         select count(bh) num, jb.gdryxm, ems.kddh\n" +
            "         from rpo_ems_info ems\n" +
            "                  left join pub_yysd_jb jb on ems.yysdbh = jb.yysdbh\n" +
            "         where ems.kddh is NOT NULL\n" +
            "           and ems.kddh != ' '\n" +
            "and ems.scrq>'${start}'"+
            "and ems.scrq<'${end}'"+
            "         group by jb.gdryxm, ems.kddh\n" +
            "     ) as t\n" +
            "where t.gdryxm=#{yhdm}")
    Integer getYjcs(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);
}
