package com.nju.sdpt.mapper;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.model.SdzqModel;
import com.nju.sdpt.model.TjxxModel;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
@Repository
public interface ReportMapper {

    @SelectProvider(type = SqlProvider.class,method = "loadSlcdData")
    List<Map<String, Integer>> loadSlcdData(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadWebCallData")
    List<Map<String, Integer>> loadWebCallData(@Param("queryVo") ReportQueryVo queryVo,@Param("hmType")  String hmType);

    @SelectProvider(type = SqlProvider.class,method = "loadDxtzData")
    List<Map<String, Integer>> loadDxtzData(@Param("queryVo") ReportQueryVo queryVo,@Param("hmType")  String hmType);

    @SelectProvider(type = SqlProvider.class,method = "loadLylqData")
    List<Map<String, Integer>> loadLylqData(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadZjsdData")
    List<Map<String,Integer>>  loadZjsdData(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadLaayCaseData")
    List<Map<String, Object>> loadLaayCaseData(@Param("queryVo") ReportQueryVo queryVo,@Param("type") String type);

    @SelectProvider(type = SqlProvider.class,method = "loadLaayTotal")
    int loadLaayTotal(@Param("queryVo") ReportQueryVo queryVo);
    @SelectProvider(type = SqlProvider.class,method = "loadLaaySuc")
    int loadLaaySuc(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadYxbrData")
    List<Map<String, Integer>> loadYxbrData(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadSjfbData")
    List<Map<String, Integer>> loadSjfbData(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadEmsData")
    List<Map<String, Integer>> loadEmsData(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadGdzq")
    Double loadGdzq(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadDhsdzq")
    Double loadDhsdzq(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadDxsdzq")
    List<SdzqModel> loadDxsdzq(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadLysdzq")
    List<SdzqModel> loadLysdzq(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadZjsdzq")
    List<SdzqModel> loadZjsdzq(@Param("queryVo")  ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "loadEmssdzq")
    List<SdzqModel> loadEmssdzq(@Param("queryVo")  ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "getSdwsfb")
    List<TjxxModel> getSdwsfb(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "getSdwsfbAll")
    Double getSdwsfbAll(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProvider.class,method = "getBmmcListByfybhList")
    List<String> getBmmcListByfybhList(@Param("fybhList") List<String> fybhList);

    @SelectProvider(type = SqlProvider.class,method = "getSsdrlxListByfybhList")
    List<String> getSsdrlxListByfybhList(@Param("fybhList") List<String> fybhList);

    class SqlProvider {
        public String loadEmsData(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and SCRQ >= '").append(queryVo.getStartTime()).append("' and SCRQ < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,ems.DSRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            StringBuilder perWhere = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                perWhere.append(" jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("' )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                perWhere.append(" and ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    perWhere.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    perWhere.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
            }
            String reportCusType = queryVo.getReportCusType();

            sql.append("SELECT\n" +
                    "  sum(\"emsNum\") as  \"emsNum\",\n" +
                    "  sum(\"emsPeoNum\") as  \"emsPeoNum\",\n" +
                    "  sum(\"emsSucNum\") as \"emsSucNum\",\n" +
                    "  sum(\"emsFailNum\") as \"emsFailNum\",\n" +
                    "  sum(\"emsSnNum\") as \"emsSnNum\",\n" +
                    "  sum(\"emsWsNum\") as \"emsWsNum\"\n" +
                    "FROM\n"+
                    "  (\n" +
                    "    SELECT\n" +
                    "      count(0) as  \"emsNum\",\n" +
                    "      0    as    \"emsPeoNum\",\n"+
                    "      0    as    \"emsSucNum\",\n" +
                    "      0    as    \"emsFailNum\",\n" +
                    "      0    as    \"emsSnNum\",\n" +
                    "      sum(wsnum)    as    \"emsWsNum\"\n" +
                    "      FROM         " +
                    "      RPO_EMS_INFO as ems\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = ems.YYSDBH "+
                    "      WHERE ems.YYSDBH > 0 AND ems.kddh is not null AND "+perWhere+
                    cusTimeWhere +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT\n" +
                    "      0     as   \"emsNum\",\n" +
                    "      count(0)     as    \"emsPeoNum\",\n"+
                    "      0     as     \"emsSucNum\",\n" +
                    "      0     as      \"emsFailNum\",\n" +
                    "      0    as    \"emsSnNum\",\n" +
                    "      0    as    \"emsWsNum\"\n" +
                    "    FROM (\n" +
                    " (SELECT DISTINCT ems.YYSDBH,ems.dsrbh,ems.WSNUM FROM RPO_EMS_INFO ems" +
                    " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = ems.YYSDBH "+
                    "           WHERE ems.YYSDBH > 0 AND ems.kddh is not null  AND "+perWhere +
                    cusTimeWhere +
                    "         )) a\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT\n" +
                    "      0     as   \"emsNum\",\n" +
                    "      0    as    \"emsPeoNum\",\n"+
                    "      count(0) as \"emsSucNum\",\n" +
                    "      0     as   \"emsFailNum\",\n" +
                    "      0    as    \"emsSnNum\",\n" +
                    "      0    as    \"emsWsNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             ems.YYSDBH,\n" +
                    "             ems.dsrbh,\n" +
                    "             ems.wsnum\n" +
                    "           FROM RPO_EMS_INFO as ems\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = ems.YYSDBH "+
                    "           WHERE ems.YYSDBH > 0 AND ems.kddh is not null AND  ems.sdjg = '成功' AND "+perWhere +
                    cusTimeWhere +
                    "         ) a\n" +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT\n" +
                    "\n" +
                    "      0    as    \"emsNum\",\n" +
                    "      0    as    \"emsPeoNum\",\n"+
                    "      0     as   \"emsSucNum\",\n" +
                    "      count(0) as \"emsFailNum\",\n" +
                    "      0    as    \"emsSnNum\",\n" +
                    "      0    as    \"emsWsNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             ems.YYSDBH,\n" +
                    "             ems.dsrbh,\n" +
                    "             ems.wsnum\n" +
                    "           FROM RPO_EMS_INFO as ems\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = ems.YYSDBH "+
                    "           WHERE ems.YYSDBH > 0 AND ems.kddh is not null AND ems.sdjg in('失败','未寄出')  AND  "+perWhere+
                    cusTimeWhere +
                    "         ) a\n" +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT\n" +
                    "\n" +
                    "      0    as    \"emsNum\",\n" +
                    "      0    as    \"emsPeoNum\",\n"+
                    "      0     as   \"emsSucNum\",\n" +
                    "      0 as \"emsFailNum\",\n" +
                    "      count(0) as \"emsSnNum\",\n" +
                    "      0    as    \"emsWsNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             ems.YYSDBH,\n" +
                    "             ems.dsrbh,\n" +
                    "             ems.wsnum\n" +
                    "           FROM RPO_EMS_INFO as ems\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = ems.YYSDBH "+
                    "           WHERE ems.YYSDBH > 0 AND ems.kddh is not null AND position('天津' in ems.sddz) > 0  AND  "+perWhere+
                    cusTimeWhere +
                    "         ) a\n" +
                    "  ) tmp");

            System.out.println("加载ems数据------------------------"+ "loademsdata");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadSjfbData(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            StringBuilder permission = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                permission.append(" (call.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                permission.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    permission.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    permission.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                permission.append(" ) ");
            }

            if (StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())) {
                cusTimeWhere.append(" and CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
            }
            sql.append("SELECT\n" +
                    "   count(CASE WHEN CRET_HMS >= '09:00:00' AND CRET_HMS < '11:00:00' THEN 1 END) as p1,\n" +
                    "   count(CASE WHEN CRET_HMS >= '11:00:00' AND CRET_HMS < '13:00:00' THEN 1 END) as p2,\n" +
                    "   count(CASE WHEN CRET_HMS >= '13:00:00' AND CRET_HMS < '15:00:00' THEN 1 END) as p3,\n" +
                    "   count(CASE WHEN CRET_HMS >= '15:00:00' AND CRET_HMS < '18:00:00' THEN 1 END) as p4\n" +
                    "from\n" +
                    "  (\n" +
                    "SELECT\n" +
                    "  to_char(CREATETIME,'hh24:mm:ss') CRET_HMS \n" +
                    " FROM PUB_WEB_CALL_INFO call\n" +
                    " LEFT JOIN PUB_YYSD_JB jb on jb.YYSDBH = call.YYSDBH " +
                    "where call.CALLSTATE = 1\n" + cusTimeWhere + " and "+ permission+
                    ") a");

            return sql.toString();
        }
        public String loadYxbrData(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            StringBuilder permission = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                permission.append(" and (call.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                permission.append(" and ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    permission.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    permission.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                permission.append(" ) ");
            }
            if (StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())) {
                cusTimeWhere.append("and  CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
            }
            sql.append("SELECT\n" +
                    "  count(CASE WHEN call.SDTYPE = '1' THEN 1 END) as  有效本人转来院,\n" +
                    "  count(CASE WHEN call.SDTYPE = '2' THEN 1 END) as  有效本人转邮寄,\n" +
                    "  count(CASE WHEN call.SDTYPE = '3' THEN 1 END) as 有效本人转电子\n" +
                    " FROM PUB_WEB_CALL_INFO as call\n" +
                    " LEFT JOIN PUB_YYSD_JB jb on jb.YYSDBH = call.YYSDBH " +
                    " where call.CALLSTATE = 1  ").append(cusTimeWhere).append(permission);

            return sql.toString();
        }
        public String loadLaayCaseData(@Param("queryVo") ReportQueryVo queryVo,@Param("type") String type){
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            StringBuilder permission = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                permission.append(" (jb.GDRYXM = '").append(queryVo.getLoginYhm()).append("' OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                permission.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    permission.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    permission.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                permission.append(" ) ");
            }
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" AND YYSJ >= '").append(queryVo.getStartTime()).append("' and YYSJ < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and jb.YYSDBH in (select YYSDBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            StringBuilder cusSdjgWhere = new StringBuilder();
            if(Objects.equals(type,"TOTAL")){
                cusSdjgWhere.append(" AND (jb.SDJG != 'X' or jb.SDJG is null) ");
            }else {
                cusSdjgWhere.append(" AND jb.SDJG = 'Y' ");
            }

            sql.append("SELECT\n" +
                    "  LAAY as name,\n" +
                    "  count(0) as value\n" +
                    "FROM\n" +
                    "  (\n" +
                    "SELECT AJXH,FYBH,LAAY\n" +
                    "FROM (\n" +
                    "SELECT jb.AJXH,jb.FYBH,LAAY FROM PUB_YYSD_JB jb\n" +
                    "where jb.AJXH > 0 and" + permission + cusSdjgWhere + cusTimeWhere +
                    ") a\n" +
                    "GROUP BY AJXH,FYBH,LAAY\n" +
                    ") tmp\n" +
                    "GROUP BY name\n" +
                    "ORDER BY value DESC "
            );
            if(Objects.equals(type,"TOTAL")){
                sql.append(" limit 5");
            }else {
                sql.append(" limit 15");
            }
            System.out.println("加载立案案由------------------------"+ "loadlaaycase");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadLaayTotal(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            StringBuilder permission = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                permission.append(" (jb.GDRYXM = '").append(queryVo.getLoginYhm()).append("' OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                permission.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    permission.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    permission.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                permission.append(" ) ");
            }
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" AND YYSJ >= '").append(queryVo.getStartTime()).append("' and YYSJ < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and jb.YYSDBH in (select YYSDBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            StringBuilder cusSdjgWhere = new StringBuilder();
            cusSdjgWhere.append(" AND (jb.SDJG != 'X' or jb.SDJG is null) ");
            sql.append("SELECT\n" +
                    "  count(0) as value\n" +
                    "FROM\n" +
                    "  (\n" +
                    "SELECT AJXH,FYBH,LAAY\n" +
                    "FROM (\n" +
                    "SELECT jb.AJXH,jb.FYBH,LAAY FROM PUB_YYSD_JB jb\n" +
                    "where jb.AJXH > 0 and" + permission + cusSdjgWhere + cusTimeWhere +
                    ") a\n" +
                    "GROUP BY AJXH,FYBH,LAAY\n" +
                    ") tmp\n"
            );
            System.out.println("加载立案案由------------------------"+ "loadlaayTotal");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadLaaySuc(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            StringBuilder permission = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                permission.append(" (jb.GDRYXM = '").append(queryVo.getLoginYhm()).append("' OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                permission.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    permission.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    permission.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                permission.append(" ) ");
            }
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" AND YYSJ >= '").append(queryVo.getStartTime()).append("' and YYSJ < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and jb.YYSDBH in (select YYSDBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            StringBuilder cusSdjgWhere = new StringBuilder();
            cusSdjgWhere.append(" AND (jb.SDJG = 'Y' ) ");
            sql.append("SELECT\n" +
                    "  count(0) as value\n" +
                    "FROM\n" +
                    "  (\n" +
                    "SELECT AJXH,FYBH,LAAY\n" +
                    "FROM (\n" +
                    "SELECT jb.AJXH,jb.FYBH,LAAY FROM PUB_YYSD_JB jb\n" +
                    "where jb.AJXH > 0 and" + permission + cusSdjgWhere + cusTimeWhere +
                    ") a\n" +
                    "GROUP BY AJXH,FYBH,LAAY\n" +
                    ") tmp\n"
            );
            System.out.println("加载立案案由------------------------"+ "loadlaayTotal");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadLylqData(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and CREATE_TIME >= '").append(queryVo.getStartTime()).append("' and CREATE_TIME < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,lylq.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            String reportCusType = queryVo.getReportCusType();
            StringBuilder perWhere = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                perWhere.append(" (lylq.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                perWhere.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    perWhere.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    perWhere.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                perWhere.append(" ) ");
            }

            sql.append("SELECT\n" +
                    "  sum(\"lylqNum\") as  \"lylqNum\",\n" +
                    "  sum(\"lylqPeoNum\") as  \"lylqPeoNum\",\n" +
                    "  sum(\"lylqSucNum\") as \"lylqSucNum\",\n" +
                    "  sum(\"lylqFailNum\") as \"lylqFailNum\"\n" +
                    "FROM (\n"+
                    "    SELECT\n" +
                    "      count(0) as  \"lylqNum\",\n" +
                    "      0    as    \"lylqPeoNum\",\n"+
                    "      0    as    \"lylqSucNum\",\n" +
                    "      0    as    \"lylqFailNum\"\n" +
                    "      FROM         " +
                    "      PUB_LYLQ_INFO as lylq\n" +
                    "      LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = lylq.YYSDBH"+
                    "      WHERE lylq.YYSDBH > 0 AND "+perWhere+
                    cusTimeWhere +
                    "\n" +
                    "    UNION \n" +
                    "\n" +
                    "    SELECT\n" +
                    "      0      as   \"lylqNum\",\n" +
                    "      count(0)     as    \"lylqPeoNum\",\n"+
                    "      0     as     \"lylqSucNum\",\n" +
                    "      0     as      \"lylqFailNum\"\n" +
                    "    FROM (\n" +
                    " (SELECT DISTINCT lylq.YYSDBH,lylq.SSDRBH FROM PUB_LYLQ_INFO lylq" +
                    " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = lylq.YYSDBH "+
                    "           WHERE lylq.YYSDBH > 0  AND "+perWhere +
                    cusTimeWhere +
                    "         ) )a\n" +
                    "    UNION \n" +
                    "\n" +
                    "    SELECT\n" +
                    "      0     as   \"lylqNum\",\n" +
                    "      0    as    \"lylqPeoNum\",\n"+
                    "      count(0) as \"lylqSucNum\",\n" +
                    "      0     as   \"lylqFailNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             lylq.YYSDBH,\n" +
                    "             lylq.SSDRBH\n" +
                    "           FROM PUB_LYLQ_INFO as lylq\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = lylq.YYSDBH "+
                    "           WHERE lylq.YYSDBH > 0 AND  lylq.sdjg = 1 AND "+perWhere +
                    cusTimeWhere +
                    "          )a\n" +
                    "\n" +
                    "    UNION \n" +
                    "\n" +
                    "    SELECT\n" +
                    "\n" +
                    "      0    as    \"lylqNum\",\n" +
                    "      0    as    \"lylqPeoNum\",\n"+
                    "      0     as   \"lylqSucNum\",\n" +
                    "      count(0) as \"lylqFailNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             lylq.YYSDBH,\n" +
                    "             lylq.SSDRBH\n" +
                    "           FROM PUB_LYLQ_INFO as lylq\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = lylq.YYSDBH "+
                    "           WHERE lylq.YYSDBH > 0 AND lylq.sdjg = 2 AND  "+perWhere +
                    cusTimeWhere +
                    "         ) a\n" +
                    "  ) tmp");

            System.out.println("来院领取数据------------------------"+ "loadlylqdata");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadZjsdData(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and CJSJ >= '").append(queryVo.getStartTime()).append("' and CJSJ < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,zjsd.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            String reportCusType = queryVo.getReportCusType();
            StringBuilder perWhere = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                perWhere.append(" jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("' )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                perWhere.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    perWhere.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    perWhere.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
            }





            sql.append("SELECT\n" +
                    "  sum(\"zjsdNum\") as  \"zjsdNum\",\n" +
                    "  sum(\"zjsdPeoNum\") as  \"zjsdPeoNum\",\n" +
                    "  sum(\"zjsdSucNum\") as \"zjsdSucNum\",\n" +
                    "  sum(\"zjsdFailNum\") as \"zjsdFailNum\"\n" +
                    "FROM (\n"+
                    "    SELECT\n" +
                    "      count(0) as  \"zjsdNum\",\n" +
                    "      0    as    \"zjsdPeoNum\",\n" +
                    "      0    as    \"zjsdSucNum\",\n" +
                    "      0    as    \"zjsdFailNum\"\n" +
                    "           FROM PUB_ZJSD_INFO as zjsd\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = zjsd.YYSDBH "+
                    "           WHERE zjsd.YYSDBH > 0 AND "+perWhere +
                    cusTimeWhere +
                    "    UNION\n" +
                    "    SELECT\n" +
                    "      0    as  \"zjsdNum\",\n" +
                    "      count(0)    as    \"zjsdPeoNum\",\n" +
                    "      0    as    \"zjsdSucNum\",\n" +
                    "      0    as    \"zjsdFailNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             zjsd.YYSDBH,\n" +
                    "             zjsd.SSDRBH\n" +
                    "           FROM PUB_ZJSD_INFO as zjsd\n" +
                    " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = zjsd.YYSDBH "+
                    "           WHERE zjsd.YYSDBH > 0 AND "+perWhere+
                    cusTimeWhere +
                    "          )a\n" +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT\n" +
                    "      0     as   \"zjsdNum\",\n" +
                    "      0    as    \"zjsdPeoNum\",\n" +
                    "      count(0) as \"zjsdSucNum\",\n" +
                    "      0     as   \"zjsdFailNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             zjsd.YYSDBH,\n" +
                    "             zjsd.SSDRBH\n" +
                    "           FROM PUB_ZJSD_INFO as zjsd\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = zjsd.YYSDBH "+
                    "           WHERE zjsd.YYSDBH > 0 AND  zjsd.zjsdjg = 1 AND "+perWhere +
                    cusTimeWhere +
                    "          )a\n" +
                    "\n" +
                    "    UNION\n" +
                    "\n" +
                    "    SELECT\n" +
                    "\n" +
                    "      0    as    \"zjsdNum\",\n" +
                    "      0    as    \"zjsdPeoNum\",\n" +
                    "      0     as   \"zjsdSucNum\",\n" +
                    "      count(0) as \"zjsdFailNum\"\n" +
                    "    FROM (\n" +
                    "           SELECT\n" +
                    "             DISTINCT\n" +
                    "             zjsd.YYSDBH,\n" +
                    "             zjsd.SSDRBH\n" +
                    "           FROM PUB_ZJSD_INFO as zjsd\n" +
                    "           LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = zjsd.YYSDBH "+
                    "           WHERE zjsd.YYSDBH > 0 AND zjsd.zjsdjg = 2 AND  "+perWhere+
                    cusTimeWhere +
                    "        ) a\n" +
                    "  ) tmp");
            System.out.println("直接送达数据------------------------"+ "loadzjsddata");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadDxtzData(@Param("queryVo") ReportQueryVo queryVo,@Param("hmType")  String hmType){
            StringBuilder sql = new StringBuilder();
            String reportCusType = queryVo.getReportCusType();

            StringBuilder cusHmTypeWhere = new StringBuilder();
            if(Objects.equals(hmType,"ENTRY")){
                cusHmTypeWhere.append(" dxtz.OPERATORTYPE IN ('ENTRY') ");
            }else {
                cusHmTypeWhere.append(" dxtz.OPERATORTYPE NOT IN ('ENTRY') ");
            }
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,dxtz.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            StringBuilder perWhere = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                perWhere.append(" (dxtz.SDYBH = '").append(queryVo.getYhbh()).append("' OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                perWhere.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    perWhere.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    perWhere.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                perWhere.append(" ) ");
            }

            if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.COUNT)){
                sql.append("SELECT\n" +
                        "  count(0) as \"dxtzNum\" ,\n" +
                        "  count(CASE WHEN dxtz.FWZT IS NULL THEN 1 END) as \"cwzDxtzNum\" ,\n" +
                        "  count(CASE WHEN dxtz.FWZT IS not NULL THEN 1 END) as \"dljDxtzNum\" ,\n" +
                        "  count(CASE WHEN dxtz.FWZT = 1 THEN 1 END) as \"dljDxtzFwNum\" , \n" +
                        "  sum(wsnum) as \"dxtzWsfs\"  \n" +
                        "FROM PUB_DXTZ_INFO as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "WHERE dxtz.YYSDBH > 0 ");
                sql.append(" and ").append(perWhere);
                sql.append(" and ").append(cusHmTypeWhere).append(cusTimeWhere);
            }
            else if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.PEO)){
                sql.append("SELECT\n" +
                        "  sum(\"dxtzNum\") as  \"dxtzNum\",\n" +
                        "  sum(\"cwzDxtzNum\") as \"cwzDxtzNum\",\n" +
                        "  sum(\"dljDxtzNum\") as \"dljDxtzNum\",\n" +
                        "  sum(\"dljDxtzFwNum\") as \"dljDxtzFwNum\",\n" +
                        "  sum(\"dxtzSucNum\") as \"dxtzSucNum\",\n" +
                        "  sum(\"dxtzFailNum\") as \"dxtzFailNum\",\n" +
                        "  sum(\"dxtzWsfs\") as \"dxtzWsfs\"  \n" +
                        "FROM\n" +
                        "  (\n" +
                        "    SELECT\n" +
                        "      count(0) as  \"dxtzNum\",\n" +
                        "      0    as    \"cwzDxtzNum\",\n" +
                        "      0    as    \"dljDxtzNum\",\n" +
                        "      0     as   \"dljDxtzFwNum\",\n" +
                        "      0    as    \"dxtzSucNum\",\n" +
                        "      0     as   \"dxtzFailNum\",\n" +
                        "  sum(wsnum) as \"dxtzWsfs\"  \n" +
                        "    FROM (\n" +
                        "           SELECT\n" +
                        "             DISTINCT\n" +
                        "             dxtz.YYSDBH,\n" +
                        "             dxtz.SSDRBH,\n" +
                        "             dxtz.WSNUM\n" +
                        "           FROM PUB_DXTZ_INFO as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "           WHERE dxtz.YYSDBH > 0 AND "+perWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "         ) a\n" +
                        "\n" +
                        "    UNION\n" +
                        "\n" +
                        "    SELECT\n" +
                        "      0     as   \"dxtzNum\",\n" +
                        "      count(0) as \"cwzDxtzNum\",\n" +
                        "      0     as   \"dljDxtzNum\",\n" +
                        "      0     as   \"dljDxtzFwNum\",\n" +
                        "      0    as    \"dxtzSucNum\",\n" +
                        "      0     as   \"dxtzFailNum\",\n" +
                        "      0     as   \"dxtzWsfs\"\n" +
                        "    FROM (\n" +
                        "           SELECT\n" +
                        "             DISTINCT\n" +
                        "             dxtz.YYSDBH,\n" +
                        "             dxtz.SSDRBH,\n" +
                        "             dxtz.WSNUM\n" +
                        "           FROM PUB_DXTZ_INFO  as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "           WHERE dxtz.YYSDBH > 0 AND dxtz.FWZT IS NULL AND "+perWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "         ) a\n" +
                        "\n" +
                        "    UNION\n" +
                        "\n" +
                        "    SELECT\n" +
                        "\n" +
                        "      0    as    \"dxtzNum\",\n" +
                        "      0     as   \"cwzDxtzNum\",\n" +
                        "      count(0)  as \"dljDxtzNum\",\n" +
                        "      0    as    \"dljDxtzFwNum\",\n" +
                        "      0    as    \"dxtzSucNum\",\n" +
                        "      0     as   \"dxtzFailNum\",\n" +
                        "      0     as   \"dxtzWsfs\"\n" +
                        "    FROM (\n" +
                        "           SELECT\n" +
                        "             DISTINCT\n" +
                        "             dxtz.YYSDBH,\n" +
                        "             dxtz.SSDRBH,\n" +
                        "             dxtz.WSNUM\n" +
                        "           FROM PUB_DXTZ_INFO  as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "           WHERE dxtz.YYSDBH > 0 AND dxtz.FWZT IS NOT NULL AND  "+perWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "         ) a\n" +
                        "    UNION\n" +
                        "    SELECT\n" +
                        "      0    as    \"dxtzNum\",\n" +
                        "      0     as   \"cwzDxtzNum\",\n" +
                        "      0     as   \"dljDxtzNum\",\n" +
                        "      count(0) as \"dljDxtzFwNum\",\n" +
                        "      0    as    \"dxtzSucNum\",\n" +
                        "      0     as   \"dxtzFailNum\",\n" +
                        "      0     as   \"dxtzWsfs\"\n" +
                        "    FROM (\n" +
                        "           SELECT\n" +
                        "             DISTINCT\n" +
                        "             dxtz.YYSDBH,\n" +
                        "             dxtz.SSDRBH,\n" +
                        "             dxtz.WSNUM\n" +
                        "           FROM PUB_DXTZ_INFO  as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "           WHERE dxtz.YYSDBH > 0 AND dxtz.FWZT = 1 AND "+perWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "         ) a\n" +
                        "    UNION\n" +
                        "    SELECT\n" +
                        "      0    as    \"dxtzNum\",\n" +
                        "      0     as   \"cwzDxtzNum\",\n" +
                        "      0     as   \"dljDxtzNum\",\n" +
                        "      0 as \"dljDxtzFwNum\",\n" +
                        "      count(0)   as    \"dxtzSucNum\",\n" +
                        "      0     as   \"dxtzFailNum\",\n" +
                        "      0     as   \"dxtzWsfs\"\n" +
                        "    FROM (\n" +
                        "           SELECT\n" +
                        "             DISTINCT\n" +
                        "             dxtz.YYSDBH,\n" +
                        "             dxtz.SSDRBH,\n" +
                        "             dxtz.WSNUM\n" +
                        "           FROM PUB_DXTZ_INFO  as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "           WHERE dxtz.YYSDBH > 0 AND dxtz.SDJG = 1 AND "+perWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "         ) a\n" +
                        "    UNION\n" +
                        "    SELECT\n" +
                        "      0    as    \"dxtzNum\",\n" +
                        "      0     as   \"cwzDxtzNum\",\n" +
                        "      0     as   \"dljDxtzNum\",\n" +
                        "      0     as \"dljDxtzFwNum\",\n" +
                        "      0     as    \"dxtzSucNum\",\n" +
                        "     count(0)     as   \"dxtzFailNum\",\n" +
                        "      0     as   \"dxtzWsfs\"\n" +
                        "    FROM (\n" +
                        "           SELECT\n" +
                        "             DISTINCT\n" +
                        "             dxtz.YYSDBH,\n" +
                        "             dxtz.SSDRBH,\n" +
                        "             dxtz.WSNUM\n" +
                        "           FROM PUB_DXTZ_INFO  as dxtz\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH "+
                        "           WHERE dxtz.YYSDBH > 0 AND dxtz.SDJG = 2 AND "+perWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "         ) a\n" +
                        "  ) tmp");
            }
            System.out.println("加载短信数据------------------------"+ "loaddxtzdata");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadWebCallData(@Param("queryVo") ReportQueryVo queryVo,@Param("hmType")  String hmType) {
            StringBuilder sql = new StringBuilder();
            String reportCusType = queryVo.getReportCusType();

            StringBuilder cusHmTypeWhere = new StringBuilder();
            if(Objects.equals(hmType,"ENTRY")){
                cusHmTypeWhere.append(" call.OPERATORTYPE IN ('ENTRY') ");
            }else {
                cusHmTypeWhere.append(" call.OPERATORTYPE NOT IN ('ENTRY') ");
            }
            StringBuilder preWhere = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                preWhere.append(" (call.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                preWhere.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    preWhere.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    preWhere.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                preWhere.append(" ) ");
            }
            if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.COUNT)){
                sql.append("SELECT\n" +
                        "  count(0) as \"webCallNum\",\n" +
                        "  count(CASE WHEN call.CALLDURATION > 0 THEN 1 END) as \"webCallJtNum\",\n" +
                        "  count(CASE WHEN call.CALLDURATION > 0 and call.SDTYPE = '1' THEN 1 END) as \"jtZLylqNum\",\n" +
                        "  count(CASE WHEN call.CALLDURATION > 0 and call.SDTYPE = '2' THEN 1 END) as \"jtZEmsNum\",\n" +
                        "  count(CASE WHEN call.CALLDURATION > 0 and call.SDTYPE = '3' THEN 1 END) as \"jtZDxtzNum\",\n" +
                        "  count(CASE WHEN call.CALLDURATION > 0 and call.CALLSTATE = 1 THEN 1 END) as \"crbrNum\"\n" +
                        "FROM PUB_WEB_CALL_INFO as call ");
                sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH ");

                sql.append(" where ").append(preWhere);
                sql.append(" and "+ cusHmTypeWhere);
                if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                    sql.append(" and  CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
                }

                if(StringUtil.isNotBlank(queryVo.getTsName())) {
                    sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                    sql.append("and (jb.YYSDBH,call.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
                }

            }
            else if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.PEO)){
                StringBuilder cusTimeWhere = new StringBuilder();
                if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                    cusTimeWhere.append(" and CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getTsName())) {
                    cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                    cusTimeWhere.append("and (jb.YYSDBH,call.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
                }

                sql.append("SELECT\n" +
                        "  sum(\"webCallNum\") as \"webCallNum\",\n" +
                        "  sum(\"webCallJtNum\") as \"webCallJtNum\",\n" +
                        "  sum(\"jtZLylqNum\") as \"jtZLylqNum\",\n" +
                        "  sum(\"jtZEmsNum\") as \"jtZEmsNum\",\n" +
                        "  sum(\"jtZDxtzNum\") as \"jtZDxtzNum\",\n" +
                        "  sum(\"crbrNum\") as \"crbrNum\",\n" +
                        "  sum(\"webCallSucNum\") as \"webCallSucNum\",\n" +
                        "  sum(\"webCallFailNum\") as \"webCallFailNum\"\n" +
                        "FROM ( ");
                //数据集1
                sql.append("SELECT\n" +
                        "    count(0) as \"webCallNum\",\n" +
                        "    0     as    \"webCallJtNum\",\n" +
                        "    0     as   \"jtZLylqNum\",\n" +
                        "    0     as   \"jtZEmsNum\",\n" +
                        "    0     as   \"jtZDxtzNum\",\n" +
                        "    0      as  \"crbrNum\",\n" +
                        "    0      as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE "+ preWhere +" AND "+ cusHmTypeWhere +
                                cusTimeWhere +
                        "      ) a");

                sql.append(" UNION ");

                //数据集2
                sql.append("SELECT\n" +
                        "    0   as    \"webCallNum\",\n" +
                        "    count(0)  as \"webCallJtNum\",\n" +
                        "    0   as     \"jtZLylqNum\",\n" +
                        "    0   as   \"jtZEmsNum\",\n" +
                        "    0   as     \"tZDxtzNum\",\n" +
                        "    0   as     \"crbrNum\",\n" +
                        "    0      as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.CALLDURATION > 0 AND "+ preWhere +" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");
                sql.append(" UNION ");

                //数据集3
                sql.append(" SELECT\n" +
                        "    0   as     \"webCallNum\",\n" +
                        "    0   as     \"webCallJtNum\",\n" +
                        "    count(0) as \"jtZLylqNum\",\n" +
                        "    0   as     \"jtZEmsNum\",\n" +
                        "    0   as     \"jtZDxtzNum\",\n" +
                        "    0   as    \"crbrNum\",\n" +
                        "    0      as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.CALLDURATION > 0 AND call.SDTYPE = '1' AND "+preWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");
                sql.append(" UNION ");

                //数据集4
                sql.append("SELECT\n" +
                        "    0    as    \"webCallNum\",\n" +
                        "    0    as    \"webCallJtNum\",\n" +
                        "    0    as   \"jtZLylqNum\",\n" +
                        "    count(0) as \"jtZEmsNum\",\n" +
                        "    0    as    \"jtZDxtzNum\",\n" +
                        "    0    as    \"crbrNum\",\n" +
                        "    0      as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.CALLDURATION > 0 AND call.SDTYPE = '2' AND "+preWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");
                sql.append(" UNION ");

                //数据集5
                sql.append(" SELECT\n" +
                        "    0   as     \"webCallNum\",\n" +
                        "    0   as     \"webCallJtNum\",\n" +
                        "    0   as     \"jtZLylqNum\",\n" +
                        "    0   as     \"jtZEmsNum\",\n" +
                        "    count(0)  as \"jtZDxtzNum\",\n" +
                        "    0   as     \"crbrNum\",\n" +
                        "    0      as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as  call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.CALLDURATION > 0 AND call.SDTYPE = '3' AND "+preWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");
                sql.append(" UNION ");

                //数据集6
                sql.append(" SELECT\n" +
                        "    0  as      \"webCallNum\",\n" +
                        "    0  as      \"webCallJtNum\",\n" +
                        "    0  as      \"jtZLylqNum\",\n" +
                        "    0  as      \"jtZEmsNum\",\n" +
                        "    0  as      \"jtZDxtzNum\",\n" +
                        "    count(0) \"crbrNum\",\n" +
                        "    0      as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.CALLDURATION > 0 AND call.CALLSTATE = 1 AND "+preWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");

                sql.append(" UNION ");

                //数据集7
                sql.append(" SELECT\n" +
                        "    0  as      \"webCallNum\",\n" +
                        "    0  as      \"webCallJtNum\",\n" +
                        "    0  as      \"jtZLylqNum\",\n" +
                        "    0  as      \"jtZEmsNum\",\n" +
                        "    0  as      \"jtZDxtzNum\",\n" +
                        "    0  as      \"crbrNum\",\n" +
                        "    count(0)     as  \"webCallSucNum\",\n" +
                        "    0      as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.SDJG = 1 AND "+preWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");

                sql.append(" UNION ");

                //数据集7
                sql.append(" SELECT\n" +
                        "    0  as      \"webCallNum\",\n" +
                        "    0  as      \"webCallJtNum\",\n" +
                        "    0  as      \"jtZLylqNum\",\n" +
                        "    0  as      \"jtZEmsNum\",\n" +
                        "    0  as      \"jtZDxtzNum\",\n" +
                        "    0  as      \"crbrNum\",\n" +
                        "    0    as  \"webCallSucNum\",\n" +
                        "    count(0)     as  \"webCallFailNum\"\n" +
                        "  FROM (SELECT\n" +
                        "          DISTINCT\n" +
                        "          call.YYSDBH,\n" +
                        "          call.SSDRBH\n" +
                        "        FROM PUB_WEB_CALL_INFO as call\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH "+
                        "        WHERE call.SDJG = 2 AND "+preWhere+" AND "+ cusHmTypeWhere +
                        cusTimeWhere +
                        "       ) a");

                sql.append(") tmp");
            }
            System.out.println("加载webcall------------------------"+ "loadwebcall");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadSlcdData(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            String reportCusType = queryVo.getReportCusType();
            StringBuilder perWhere = new StringBuilder();
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                perWhere.append(" (reapir.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                perWhere.append(" ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    perWhere.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    perWhere.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                perWhere.append(" ) ");
            }
            if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.COUNT)){
                sql.append("SELECT\n" +
                        "  count(0) as \"repairNum\",\n" +
                        "  count(CASE WHEN REPAIRSTATUS = 'RPBS003' THEN 1 END) as \"repairSucNum\"\n" +
                        "FROM PUB_REPAIR_INFO as reapir ");
                sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = reapir.YYSDBH ");

                sql.append(" where ").append(perWhere);
                if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                    sql.append(" and  CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getTsName())) {
                    sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                    sql.append("and (jb.YYSDBH,reapir.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
                }
            }else if(Objects.equals(reportCusType, SdptConstants.REPORT_CUS_TYPE.PEO)){
                sql.append("SELECT sum(\"repairNum\") as \"repairNum\",sum(\"repairSucNum\") as \"repairSucNum\"\n" +
                        "FROM (\n" );
                sql.append(
                        "SELECT\n" +
                        "  count(0) as \"repairNum\" ,\n" +
                        "  0 as \"repairSucNum\"\n" +
                        "FROM (\n" +
                        "  SELECT DISTINCT\n" +
                        "    reapir.YYSDBH,\n" +
                        "    reapir.SSDRBH\n" +
                        "  FROM PUB_REPAIR_INFO as reapir\n");
                sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = reapir.YYSDBH ");
                sql.append(" where ").append(perWhere);
                if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                    sql.append(" and  CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
                }
                sql.append(") a");
                sql.append(" UNION ");
                sql.append(" SELECT\n" +
                        "  0 as \"repairNum\" ,\n" +
                        "  count(0) as \"repairSucNum\"\n" +
                        "FROM (\n" +
                        "  SELECT DISTINCT\n" +
                        "    reapir.YYSDBH,\n" +
                        "    reapir.SSDRBH\n" +
                        "  FROM PUB_REPAIR_INFO reapir\n" +
                        " LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = reapir.YYSDBH "+
                        "  WHERE REPAIRSTATUS = 'RPBS003'\n" );
                sql.append(" and ").append(perWhere);
                if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                    sql.append(" and CREATETIME >= '").append(queryVo.getStartTime()).append("' and CREATETIME < '").append(queryVo.getEndTime()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getTsName())) {
                    sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
                }
                if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                    sql.append("and (jb.YYSDBH,reapir.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
                }
                sql.append(") b\n" +
                        ")tmp");
            }
            System.out.println("修复------------------------"+ "loadslcddata");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadGdzq(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            StringBuilder perWhere = new StringBuilder();
            perWhere.append("  " +
                    "and fybh in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and sdsj >= '").append(queryVo.getStartTime()).append("' and sdsj <= '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and jb.YYSDBH in (select YYSDBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            sql.append("SELECT round(sum(sdzq)::numeric/sum(1)::numeric,2)  from ( \n" +
                    "         SELECT yysdbh,\n" +
                    "                datediff(YYSJ , SDSJ )+1 as sdzq\n" +
                    "         FROM pub_yysd_jb jb where ( sdjg = 'Y' or sdjg = 'N' ) ")
                    .append(perWhere).append(cusTimeWhere).append(") as tmp");
            System.out.println("案件平均送达周期------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadDhsdzq(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and createtime >= '").append(queryVo.getStartTime()).append("' and createtime <= '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,i.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            StringBuilder perWhere = new StringBuilder();
            perWhere.append("  " +
                    "and jb.fybh in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
            sql.append("select sum(callduration)/count(1) from pub_web_call_info i left join pub_yysd_jb jb on jb.yysdbh = i.yysdbh where callduration > 0 ").append(perWhere).append(cusTimeWhere);
            System.out.println("电话送达数------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadDxsdzq(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            sql.append("select min(createtime) as  createtime, min(submittime) as submittime from pub_dxtz_info i left join pub_yysd_jb jb on jb.yysdbh = i.yysdbh where fwzt is not null ")
                    .append(dealFybhStr(queryVo)).append(dealSubmittimeStr(queryVo)).append(" group by jb.yysdbh,ssdrbh having min(submittime) is not null");
            System.out.println("短信送达周期------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadLysdzq(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and submittime >= '").append(queryVo.getStartTime()).append("' and submittime <= '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,i.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            sql.append("select min(create_time) as  createtime, min(submittime) as submittime from pub_lylq_info i left join pub_yysd_jb jb on jb.yysdbh = i.yysdbh where i.sdjg is not null ")
                    .append(dealFybhStr(queryVo)).append(dealSubmittimeStr(queryVo)).append(" group by i.yysdbh,ssdrbh having min(submittime) is not null");
            System.out.println("来院送达周期------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadZjsdzq(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            sql.append("select min(smsj) as  createtime, min(submittime) as submittime from pub_zjsd_info i left join pub_yysd_jb jb on jb.yysdbh = i.yysdbh where i.qszt is not null ")
                    .append(dealFybhStr(queryVo)).append(dealSubmittimeStr(queryVo)).append(" group by i.yysdbh,ssdrbh having min(submittime) is not null");
            System.out.println("直接送达周期------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String loadEmssdzq(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            sql.append("select min(scrq) as  createtime, min(submittime) as submittime from rpo_ems_info i left join pub_yysd_jb jb on i.yysdbh = jb.yysdbh   where (i.sdjg is not null and i.sdjg != '未寄出')")
                    .append(dealFybhStr(queryVo)).append(dealSubmittimeStrForEms(queryVo)).append(" group by i.yysdbh,dsrbh having min(submittime) is not null");
            System.out.println("ems送达周期------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String getSdwsfb(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and jb.yysj >= '").append(queryVo.getStartTime()).append("' and jb.yysj <= '").append(queryVo.getEndTime()).append("'");
            }
            sql.append("select wslb as  name, count(0) as value from pub_yysd_ws ws left join pub_yysd_jb jb on ws.yysdbh = jb.yysdbh   where 1=1")
                    .append(dealFybhStr(queryVo)).append(cusTimeWhere).append("group by wslb order by count(0) desc limit 5");
            System.out.println("送达文书分布------------------------"+ "getsdwsfb");
            System.out.print(sql.toString());
            return sql.toString();
        }

        public String getSdwsfbAll(@Param("queryVo") ReportQueryVo queryVo) {
            StringBuilder sql = new StringBuilder();
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and jb.yysj >= '").append(queryVo.getStartTime()).append("' and jb.yysj <= '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append(" and jb.YYSDBH in (select YYSDBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            sql.append("select  count(0)  from pub_yysd_ws ws left join pub_yysd_jb jb on ws.yysdbh = jb.yysdbh   where 1=1")
                    .append(dealFybhStr(queryVo)).append(cusTimeWhere);
            System.out.println("送达文书分布all------------------------");
            System.out.print(sql.toString());
            return sql.toString();
        }

        private StringBuilder dealFybhStr(ReportQueryVo queryVo){
            StringBuilder perWhere = new StringBuilder();
            perWhere.append("  " +
                    "and jb.fybh in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
            return perWhere;
        }

        private StringBuilder dealSubmittimeStr(ReportQueryVo queryVo){
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and submittime >= '").append(queryVo.getStartTime()).append("' and submittime <= '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,i.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            return cusTimeWhere;
        }
        private StringBuilder dealSubmittimeStrForEms(ReportQueryVo queryVo){
            StringBuilder cusTimeWhere = new StringBuilder();
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                cusTimeWhere.append(" and submittime >= '").append(queryVo.getStartTime()).append("' and submittime <= '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                cusTimeWhere.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                cusTimeWhere.append("and (jb.YYSDBH,i.DSRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            return cusTimeWhere;
        }

        public String getBmmcListByfybhList(List<String> fybhList){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select distinct BMMC from PUB_YYSD_JB jb");
            stringBuilder.append("  " +
                    "where jb.fybh in ('").append(StringUtil.splitJoint(fybhList,"','")).append("') ");
            return stringBuilder.toString();
        }

        public String getSsdrlxListByfybhList(List<String> fybhList){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select distinct ssdrssdw from PUB_YYSD_SSDR ssdr left join PUB_YYSD_JB jb on ssdr.YYSDBH = jb.YYSDBH");
            stringBuilder.append("  " +
                    "where jb.fybh in ('").append(StringUtil.splitJoint(fybhList,"','")).append("') ");
            return stringBuilder.toString();
        }
    }
}
