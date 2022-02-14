package com.nju.sdpt.mapper;

import com.nju.sdpt.model.*;
import com.nju.sdpt.model.info.YysdInfoModel;
import com.nju.sdpt.service.Impl.YysdInfoServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface YysdInfoMapper {

    @SelectProvider(type = SqlGenerator.class, method = "generateYysdSQL")
    @ResultMap("YysdInfoResultMap")
    List<YysdInfoModel> getYysdInfoByGdryxm(YysdInfoServiceImpl.QueryParams params, YysdQueryType type);

    @Select("select fybh fymc,sum(sal) sal,sum(dsr) sjdsr, sum(cgsd)sdcgajl,sum(cgsddsr)cgsdsjdsr from\n" +
            "(select fybh,count(distinct ah) sal,sum(0) dsr,sum(case when sdjg = 'Y' then 1 else 0 end )cgsd,sum(0)cgsddsr from pub_yysd_jb jb where\n" +
            "yysj >= now() - interval '1 week' and yysj <= now() - interval '60 min'group by fybh\n" +
            "union\n" +
            "select fybh,sum(0) sal,count(*) dsr,sum(0)cgsd,sum(0)cgsddsr from pub_yysd_ssdr sdr left join pub_yysd_jb jb on sdr.yysdbh = jb.yysdbh where\n" +
            "yysj >= now() - interval '1 week' and yysj <= now() - interval '60 min'group by fybh\n" +
            "union\n" +
            "select fybh,sum(0) sal,sum(0) dsr,sum(0)cgsd,count(*)cgsddsr from pub_yysd_ssdr sdr left join pub_yysd_jb jb on sdr.yysdbh = jb.yysdbh where\n" +
            "yysj >= now() - interval '1 week' and yysj <= now() - interval '60 min' and jb.sdjg = 'Y'group by fybh) z group by fybh")
    List<ZsalModel> zsalSjtj();

    @Select("select ry.fybh fymc,yhmc ygxm,sum(1) ltxftjzs, sum(case when operator_type = 'CUCC' and repairstatus='RPBS003' then 1 else 0 end)ltxfcgcs,\n" +
            "                   sum(case when operator_type = 'WCCMCC' and repairstatus='RPBS003' then 1 else 0 end)ydxfcgcs,\n" +
            "                   sum(case when operator_type = 'QXB' and repairstatus='RPBS003' then 1 else 0 end)qyxfcgcs from pub_repair_info r left join pub_ssdr_hm w on r.repairbatchno = w.tel_batch_no left join pub_yysd_ryxx ry on r.sdybh = ry.yhbh\n" +
            "\n" +
            "            where r.createtime >= now() - interval '1 week' and r.createtime <= now() - interval '60 min' group by ry.fybh,yhmc order by ry.fybh")
    List<ZxflModel> zxflSjtj();

    @Select("select dx.fybh fymc, yhmc ygxm,sum(case when operatortype = 'ENTRY' then 1 else 0 end ) mwdxxfzts, sum(case when sendstate = 1 and operatortype = 'ENTRY' then 1 else 0 end ) mwdxxfcgzs,\n" +
            "       sum(case when operatortype != 'ENTRY' then 1 else 0 end )xfdxxfzts, sum(case when sendstate = 1 and operatortype !='ENTRY' then 1 else 0 end )xfdxxfcgzs,sum(case when fwzt is not null then 1 else 0 end )dljdxxfs,\n" +
            "       sum(case when fwzt = 1 then 1 else 0 end )dljdxfws from pub_dxtz_info dx left join pub_yysd_ryxx ry on ry.yhbh = dx.sdybh\n" +
            "where dx.createtime >= now() - interval '1 week' and dx.createtime <= now() - interval '60 min' group by dx.fybh,yhmc order by dx.fybh")
    List<ZdxlModel> zdxlSjtj();

    @Select("select web.fybh fymc,yhmc ygxm,sum(case when operatortype = 'ENTRY' then 1 else 0 end ) mwwhzcs,sum(case when operatortype = 'ENTRY' and callduration > 0 then 1 else 0 end ) mwwhjtzcs,\n" +
            "       sum(case when callduration > 0 then callduration else 0 end) mwwhthzsc,sum(case when operatortype != 'ENTRY' then 1 else 0 end )xfwhzcs,sum(case when operatortype != 'ENTRY' and callduration >0 then 1 else 0 end )xfwhjtzcs,\n" +
            "       sum(case when callduration >0 and operatortype !='ENTRY' then callduration else 0 end ) xfwhthzsc from pub_web_call_info web left join pub_yysd_ryxx ry on ry.yhbh = web.sdybh\n" +
            "where web.fybh is not null and web.createtime >= now() - interval '1 week' and web.createtime <= now() - interval '60 min' group by web.fybh,yhmc order by web.fybh")
    List<ZwhlModel> zwhlSjtj();

    class SqlGenerator {
        public String generateYysdSQL(YysdInfoServiceImpl.QueryParams params, YysdQueryType type) {
            params.addParams("sdzt", type.getName());
            StringBuilder sql = new StringBuilder();
            String selectPart = "SELECT A.YYSDBH,CASE WHEN CDSJ is NULL THEN A.AH ELSE concat(A.AH,'(催单)') END AH,A.AJXH,A.FYBH,A.AJMC,A.YYRXM YYR,A.YYSJ,A.SJHM,A.SDJG,A.KTSJ,A.BMMC,A.SDSJ,A.YYSJ,A.LAAY,A.YYSDBZ,YHMC SDYMC," +
                    "       #{sdzt} SDZT," +
                    "       (SELECT COUNT(RWWTID) FROM PUB_RWWT WHERE A.YYSDBH = YYSDBH) WTRWS," +
                    "       SSDRMC";
            String fromPart = "FROM PUB_YYSD_JB A" +
                    "         LEFT JOIN PUB_YYSD_RYXX B ON YHDM = GDRYXM" +
                    "         LEFT JOIN PUB_YYSD_SSDR C ON A.YYSDBH = C.YYSDBH";
            sql.append(selectPart).append(" ");
            sql.append(fromPart).append(" ");
            sql.append("WHERE A.GDRYXM = '#{yhmc}' AND A.YYSJ >= '#{start}' AND A.YYSJ <= '#{end}' ")
                    .append(type.getWhereClause()).append(" ORDER BY YYSDBH");
            return params.fillSql(sql.toString());
        }
    }


    enum YysdQueryType {
        ALL("ALL", 1, "       CASE" +
                "  WHEN A.SDJG is NULL AND A.DHSD is NULL AND A.DZSD is NULL AND A.EMSSD is NULL" +
                "      AND A.GGSD is NULL AND A.ZJSD is NULL AND A.LYLQ is NULL AND A.WTSD is NULL AND A.ZHJSD is NULL AND" +
                "       A.DXSD is NULL THEN '未送达'" +
                "  WHEN A.FKZT is NULL AND A.SDJG is NULL" +
                "      AND NOT (DHSD is NULL AND DZSD is NULL AND EMSSD is NULL AND GGSD is NULL AND ZJSD is NULL AND" +
                "               LYLQ is NULL AND" +
                "               WTSD is NULL AND ZHJSD is NULL AND DXSD is NULL) THEN '送达中'" +
                "  WHEN A.SDJG is NOT NULL AND A.SDJG != 'X' AND A.SDJG != '' AND A.FKZT is NULL THEN '已送达未确认'" +
                "  WHEN A.SDJG is NOT NULL AND A.SDJG != 'X' AND A.FKZT = 1 THEN '已送达已确认'" +
                "  WHEN A.SDJG = 'X' THEN '已撤回'" +
                "  WHEN A.FKZT = 2 THEN '已退回'" +
                "  WHEN A.SDJG is NULL AND A.DHSD is NULL AND A.DZSD is NULL AND A.EMSSD is NULL" +
                "      AND A.GGSD is NULL AND A.ZJSD is NULL AND A.LYLQ is NULL AND A.WTSD is NULL AND A.ZHJSD is NULL AND" +
                "       A.DXSD is NULL AND extract(day FROM now() -A.YYSJ ) > 7 THEN '超审限'" +
                "  END ", " "),
        WSD("WSD", 2, "'未送达'", " AND A.SDJG is NULL AND A.DHSD is NULL AND A.DZSD is NULL AND A.EMSSD is NULL " +
                " AND A.GGSD is NULL AND A.ZJSD is NULL AND A.LYLQ is NULL AND A.WTSD is NULL AND A.ZHJSD is NULL AND A.DXSD is NULL "),
        SDZ("SDZ", 3, "'送达中'", "  AND A.FKZT is NULL AND A.SDJG is NULL" +
                "  AND NOT (DHSD is NULL AND DZSD is NULL AND EMSSD is NULL AND GGSD is NULL AND ZJSD is NULL AND LYLQ is NULL AND" +
                "           WTSD is NULL AND ZHJSD is NULL AND DXSD is NULL) "),
        WQR("WQR", 4, "'已送达未确认'", " AND A.SDJG is NOT NULL AND A.SDJG!='X' AND A.SDJG != '' AND A.FKZT is NULL "),
        YQR("YQR", 5, "'已送达已确认'", " AND A.SDJG is NOT NULL AND A.SDJG!='X' AND A.FKZT = 1"),
        YCH("YCH", 6, "'已撤回'", " AND A.SDJG = 'X'"),
        YTH("YTH", 7, "'已退回'", " AND A.FKZT = 2"),
        CSX("CSX", 8, "'超审限'", " AND A.SDJG is NULL AND A.DHSD is NULL AND A.DZSD is NULL AND A.EMSSD is NULL " +
                " AND A.GGSD is NULL AND A.ZJSD is NULL AND A.LYLQ is NULL AND A.WTSD is NULL AND A.ZHJSD is NULL AND A.DXSD is NULL AND extract(day FROM now() -A.YYSJ ) >7");

        String type;
        Integer no;
        String name;
        String whereClause;

        YysdQueryType(String type, Integer no, String name, String whereClause) {
            this.type = type;
            this.no = no;
            this.name = name;
            this.whereClause = whereClause;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNo() {
            return no;
        }

        public void setNo(Integer no) {
            this.no = no;
        }

        public String getWhereClause() {
            return whereClause;
        }

        public void setWhereClause(String whereClause) {
            this.whereClause = whereClause;
        }

        public static YysdQueryType getByNo(Integer no) {
            for (YysdQueryType e : YysdQueryType.values()) {
                if (e.no.equals(no)) {
                    return e;
                }
            }
            return null;
        }
    }


}
