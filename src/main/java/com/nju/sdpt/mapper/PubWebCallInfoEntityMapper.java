package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubWebCallInfoEntity;
import com.nju.sdpt.model.PubWebCallInfoModel;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Mapper
@Repository
public interface PubWebCallInfoEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer webcallid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    int insert(PubWebCallInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    int insertSelective(PubWebCallInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    PubWebCallInfoEntity selectByPrimaryKey(Integer webcallid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PubWebCallInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(PubWebCallInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_WEB_CALL_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PubWebCallInfoEntity record);


    @Select(value = "SELECT * FROM PUB_WEB_CALL_INFO WHERE UUID=#{uuid}")
    PubWebCallInfoEntity findByUuid(@Param("uuid") String uuid);

    @Select(value = "SELECT ssdr.SSDRMC,CALL.*  FROM PUB_WEB_CALL_INFO CALL left join PUB_YYSD_SSDR ssdr on ssdr.YYSDBH = CALL.YYSDBH and ssdr.SSDRBH = CALL.SSDRBH WHERE CALL.YYSDBH = #{yysdbh} ORDER BY CALL.CREATETIME")
    List<PubWebCallInfoModel> findByYysdbh(@Param("yysdbh") Integer yysdbh);


    @SelectProvider(type = SqlProvider.class,method = "updateUuid")
    void updateUuid(@Param("callId") Integer callId,@Param("uuid") String uuid);

    @Select(value = "SELECT * FROM PUB_WEB_CALL_INFO CALL WHERE CALL.UUID IS NOT NULL AND ( CALL.QUERYSTATE IS NULL OR CALL.QUERYSTATE = 0) and CREATETIME > '${startTimeStr}'")
    List<PubWebCallInfoEntity> selectNotQueryCallRecordList(@Param("startTimeStr") String startTimeStr);

    @Select(value = "SELECT W.*,S.SSDRMC  FROM PUB_WEB_CALL_INFO AS W LEFT JOIN  PUB_YYSD_SSDR AS S ON W.YYSDBH=S.YYSDBH AND W.SSDRBH = S.SSDRBH  WHERE AJXH = #{ajxh} and FYBH = #{fybh}")
    List<PubWebCallInfoModel> findByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);

    @Select(value = "select * from PUB_WEB_CALL_INFO where YYSDBH =#{yysdbh} and SSDRBH = #{ssdrbh} order by CREATETIME desc")
    List<PubWebCallInfoEntity> findByYysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select(value = " SELECT call.* from PUB_WEB_CALL_INFO call " +
            " LEFT JOIN PUB_YYSD_SSDR ssdr ON call.YYSDBH = ssdr.YYSDBH AND call.SSDRBH = ssdr.SSDRBH " +
            " WHERE ssdr.SSDRMC = #{ssdrmc} AND ssdr.SFZHM = #{sfzhm} order by call.CREATETIME desc ")
    List<PubWebCallInfoEntity> queryByssdrMcAndSfzhm(@Param("ssdrmc") String ssdrmc,@Param("sfzhm") String sfzhm);

    @SelectProvider(type = PubWebCallInfoEntityMapper.SqlProvider.class,method = "selectListByYhbh")
    List<PubWebCallInfoModel> selectListByYhbh(@Param("yhbh") Integer yhbh);

    @Select(value="SELECT ssdr.SSDRMC,jb.AH,call.* FROM PUB_WEB_CALL_INFO call LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH LEFT JOIN PUB_YYSD_SSDR ssdr ON ssdr.YYSDBH = call.YYSDBH AND ssdr.SSDRBH = call.SSDRBH WHERE call.SDYBH =#{yhbh} AND call.CREATETIME>='${start}' AND call.CREATETIME<='${end}' order by call.CREATETIME DESC")
    List<PubWebCallInfoModel> selectListByYhbh1(@Param("yhbh") Integer yhbh, @Param("start") String start, @Param("end") String end);

    @SelectProvider(type = PubWebCallInfoEntityMapper.SqlProvider.class,method = "ljWebcallNum")
    Integer ljWebcallNum(@Param("queryVo") ReportQueryVo queryVo);

    // ??????yhdm???????????????????????????????????????
    @Select(value = "select sum(calldurationNum)\n" +
            "from (\n" +
            "         select webcall.yysdbh yysdbh, sum(webcall.callduration) calldurationNum, jb.gdryxm gdryxm\n" +
            "         from pub_web_call_info webcall\n" +
            "                  left join pub_yysd_jb jb on webcall.yysdbh = jb.yysdbh\n" +
            "         where jb.gdryxm = #{yhdm}\n" +
            "        and webcall.createtime>'${start}'\n" +
            "        and '${end}'>=webcall.createtime\n" +
            "         group by webcall.yysdbh, jb.gdryxm\n" +
            "     ) as t\n" +
            "group by t.gdryxm")
    Integer CalWebCallzthsc(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end );

    // ???????????????????????????
    @Select(value = "    select count(webcall.webcallid) yysdbhNum\n" +
            "    from pub_web_call_info webcall\n" +
            "         left join pub_yysd_jb jb on webcall.yysdbh = jb.yysdbh\n" +
            "    where jb.gdryxm = #{yhdm}\n" +
            "        and webcall.createtime>'${start}'\n" +
            "        and '${end}'>=webcall.createtime\n" +
            "    group by jb.gdryxm;")
    Integer CalWebCallac(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end );

    // ????????????????????????????????????
    @Select(value = "select count(webcall.webcallid) yysdbhNum\n" +
            "from pub_web_call_info webcall\n" +
            "         left join pub_yysd_jb jb on webcall.yysdbh = jb.yysdbh\n" +
            "where jb.gdryxm = #{yhdm}\n" +
            "  and webcall.querystate = 1\n" +
            " and (webcall.callstate=1 or webcall.callstate=2 or webcall.callstate=6 or webcall.callstate=11 or webcall.callstate=12)\n"+
            "  and webcall.createtime>'${start}'\n" +
            "  and '${end}'>=webcall.createtime\n" +
            "group by jb.gdryxm;")
    Integer CalWebCallyxac(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end );

    // ??????????????????????????????
    @Select(value = "select count(t.targettel)\n" +
            "from (\n" +
            "         select distinct webcall.targettel targettel, jb.gdryxm\n" +
            "         from pub_web_call_info webcall\n" +
            "                  left join pub_yysd_jb jb on webcall.yysdbh = jb.yysdbh\n" +
            "         where jb.gdryxm = #{yhdm}\n" +
            "        and webcall.createtime>'${start}'\n" +
            "        and '${end}'>=webcall.createtime\n" +
            "         group by jb.gdryxm, webcall.targettel\n" +
            "     ) t\n" +
            "group by t.gdryxm")
    Integer CalWebCallar(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // ????????????????????????????????????
    @Select(value = "select count(t.targettel)\n" +
            "from (\n" +
            "         select distinct webcall.targettel targettel, jb.gdryxm, webcall.createtime createtime\n" +
            "         from pub_web_call_info webcall\n" +
            "                  left join pub_yysd_jb jb on webcall.yysdbh = jb.yysdbh\n" +
            "         where jb.gdryxm = #{yhdm}\n" +
            "           and webcall.querystate = 1\n" +
            "        and webcall.createtime>'${start}'\n" +
            "        and '${end}'>=webcall.createtime\n" +
            "         group by jb.gdryxm, webcall.targettel, webcall.createtime\n" +
            "     ) t\n" +
            "group by t.gdryxm")
    Integer CalWebCallyxar(@Param("yhdm") String yhdm,@Param("start") String start,@Param("end") String end);

    // ????????????????????????
    @Select(value = "select count(ssdr.yysdbh)\n" +
            "from pub_yysd_ssdr ssdr\n" +
            "         left join pub_yysd_jb jb on ssdr.yysdbh = jb.yysdbh\n" +
            "where ssdr.sftydzsd = 1 and jb.gdryxm=#{yhdm}\n" +
            "group by jb.gdryxm")
    Integer Tydzsdrs(@Param("yhdm") String yhdm);

    class SqlProvider{
        public String ljWebcallNum(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT count(0) FROM PUB_WEB_CALL_INFO call ");
            sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH ");
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //??????????????????
                sql.append(" where (call.SDYBH = '").append(queryVo.getYhbh()).append("' OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //????????????
                Integer authority = queryVo.getAuthority();
                sql.append(" where ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //????????????
                    sql.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    sql.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                sql.append(" ) ");
            }
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                sql.append(" and  call.CREATETIME >= '").append(queryVo.getStartTime()).append("' and call.CREATETIME < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                sql.append("and (jb.YYSDBH,call.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            System.out.println("??????????????????------------------------"+ "ljwebcallnum");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String updateUuid(@Param("callId") Integer callId,@Param("uuid") String uuid) {
            StringBuilder sql = new StringBuilder();
            sql.append("update PUB_WEB_CALL_INFO set QUERYSTATE = 0,UUID = '").append(uuid).append("' where WEBCALLID =").append(callId);
            return sql.toString();
        }
        public String selectListByYhbh(@Param("yhbh") Integer yhbh){
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ssdr.SSDRMC,jb.AH,call.* FROM PUB_WEB_CALL_INFO call ");
            sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = call.YYSDBH ");
            sql.append(" LEFT JOIN PUB_YYSD_SSDR ssdr ON ssdr.YYSDBH = call.YYSDBH AND ssdr.SSDRBH = call.SSDRBH ");
            sql.append(" WHERE call.SDYBH = "+yhbh+" ");
            sql.append(" order by call.CREATETIME DESC");
            return sql.toString();
        }

    }
}