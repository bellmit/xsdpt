package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubDxtzInfoEntity;
import com.nju.sdpt.model.DxtzListModel;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.DxtzLoadListVo;
import com.nju.sdpt.viewobject.ReportQueryVo;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Mapper
@Repository
public interface PubDxtzInfoEntityMapper {
    int deleteByPrimaryKey(Integer dxtzid);

    int insert(PubDxtzInfoEntity record);

    int insertSelective(PubDxtzInfoEntity record);

    PubDxtzInfoEntity selectByPrimaryKey(Integer dxtzid);

    int updateByPrimaryKeySelective(PubDxtzInfoEntity record);

    int updateByPrimaryKey(PubDxtzInfoEntity record);

    @Select(value = "select * from PUB_DXTZ_INFO where dxtzid=#{dxtzid}")
    PubDxtzInfoEntity selectByDxtzid(@Param("dxtzid")Integer dxtzid);


    @Select(value = "select * from PUB_DXTZ_INFO where SENDSTATE = 0")
    List<PubDxtzInfoEntity> selectSendingDxtzList();

    @Select(value = "select * from PUB_DXTZ_INFO where SERIALNUMBER = #{serialNumber}")
    List<PubDxtzInfoEntity> selectListBySerialNumber(@Param("serialNumber") String serialNumber);


    @Select(value = "select dxtz.*,dxmb.MBMC,yysd.SSDRMC FROM PUB_DXTZ_INFO dxtz\n" +
            "  LEFT JOIN PUB_DXMB_INFO dxmb on dxmb.BH = dxtz.TEMPLATEID\n" +
            "  inner join PUB_YYSD_SSDR yysd on dxtz.SSDRBH=yysd.SSDRBH and dxtz.YYSDBH=yysd.YYSDBH\n" +
            "where dxtz.YYSDBH = #{yysdbh}  order by dxtz.CREATETIME")
    List<DxtzListModel> findByYysdbh(@Param("yysdbh") Integer yysdbh);

    @SelectProvider(type = SqlProviderLylq.class,method = "loadList")
    List<DxtzListModel> loadList(@Param("loadListVo") DxtzLoadListVo loadListVo);

    @SelectProvider(type = SqlProviderLylq.class,method = "loadList1")
    List<DxtzListModel> loadList1(@Param("loadListVo") DxtzLoadListVo loadListVo,@Param("start") String start,@Param("end") String end);

    @Select(value = "select * from PUB_DXTZ_INFO dxtz where dxtz.DXTZID = ${dxtzId}")
    PubDxtzInfoEntity selectById(@Param("dxtzId") Integer dxtzId);

    @Select(value = "select D.* ,S.SSDRMC AS DSRMC from PUB_DXTZ_INFO AS D LEFT JOIN PUB_YYSD_SSDR AS S ON D.YYSDBH = S.YYSDBH and D.SSDRBH = S.SSDRBH where AJXH = #{ajxh} and FYBH = #{fybh}")
    List<DxtzListModel> findByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);

    @Select(value = "select * from PUB_DXTZ_INFO where YYSDBH =#{yysdbh} and SSDRBH = #{ssdrbh} order by CREATETIME desc")
    List<PubDxtzInfoEntity> findByYysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select(value = "select * from PUB_DXTZ_INFO where YYSDBH =#{yysdbh} and SSDRBH = #{ssdrbh} order by CREATETIME desc")
    List<PubDxtzInfoEntity> findByYysdbhAndSsdrbhWithBLOBs(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select(value = "select dxtz.* from PUB_DXTZ_INFO dxtz " +
            " left join PUB_YYSD_SSDR ssdr ON dxtz.YYSDBH = ssdr.YYSDBH AND dxtz.SSDRBH = ssdr.SSDRBH " +
            " where ssdr.SSDRMC = #{ssdrmc} AND ssdr.SFZHM = #{sfzhm} order by dxtz.CREATETIME desc")
    List<PubDxtzInfoEntity> queryByssdrMcAndSfzhm(@Param("ssdrmc") String ssdrmc,@Param("sfzhm") String sfzhm);
    @SelectProvider(type = SqlProviderLylq.class,method = "ljDxtzNum")
    Integer ljDxtzNum(@Param("queryVo") ReportQueryVo queryVo);

    @SelectProvider(type = SqlProviderLylq.class,method = "ljDxtzSucPeoNum")
    Integer ljDxtzSucPeoNum(@Param("queryVo") ReportQueryVo queryVo);

    @Select(value = "select TASKID from PUB_DXTZ_INFO where FLASHSENDSTATE = 1 and TASKID is not null and CREATETIME > '${startTimeStr}'")
    List<PubDxtzInfoEntity> selectSendingTaskId(@Param("startTimeStr") String startTimeStr);

    @Update("update PUB_DXTZ_INFO set FLASHSENDSTATE = ${flashsendstate} WHERE FLASHSENDSTATE = 1 AND TASKID = #{taskId}")
    void updateFlashsendstateByTaskId(@Param("taskId") String taskId, @Param("flashsendstate") Integer flashsendstate);

    @Update("update PUB_DXTZ_INFO set SDJG = #{sdjg} WHERE DXTZID = #{dxtzid}")
    void updateSdjgByDxtzId(@Param("dxtzid") Integer id, @Param("sdjg") Integer sdjg);


    @Select(value = "select count(1) from PUB_DXTZ_INFO where YYSDBH =#{yysdbh} and SSDRBH = #{ssdrbh} and SENDSTATE = 1 and FWZT = 1")
    Integer selectSdcgNumByYysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    class SqlProviderLylq{
        public String ljDxtzSucPeoNum(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT count(0) from (");
            sql.append(" SELECT DISTINCT dxtz.YYSDBH,dxtz.SSDRBH FROM PUB_DXTZ_INFO dxtz");
            sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH ");
            sql.append(" LEFT JOIN PUB_YYSD_SSDR ssdr ON ssdr.YYSDBH = dxtz.YYSDBH AND ssdr.SSDRBH = dxtz.SSDRBH ");
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                sql.append(" where ( dxtz.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                sql.append(" where ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    sql.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    sql.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                sql.append(" ) ");
            }

            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                sql.append(" and  dxtz.CREATETIME >= '").append(queryVo.getStartTime()).append("' and dxtz.CREATETIME < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                sql.append(" and ssdr.SSDRSSDW = '").append(queryVo.getDsrDw()).append("'");
            }
            sql.append(" and ssdr.SDJG = 1 ");
            sql.append(" ) tmp");
            System.out.println("累计短信通知成功------------------------"+ "ljDxtzSucPeoNum");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String ljDxtzNum(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT count(0) FROM PUB_DXTZ_INFO dxtz");
            sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = dxtz.YYSDBH ");
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //工单人员权限
                sql.append(" where (dxtz.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
            }else {
                //法官权限
                Integer authority = queryVo.getAuthority();
                sql.append(" where ( " +
                        "jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') ");
                if(Objects.equals(3,authority) || Objects.equals(4,authority)){
                    //部门权限
                    sql.append(" and jb.BMBH = '").append(queryVo.getBmbh()).append("'");
                }
                if(Objects.equals(4,authority)){
                    sql.append(" and jb.YYRXM = '").append(queryVo.getFgmc()).append("'");
                }
                sql.append(" ) ");
            }
            if(StringUtil.isNotBlank(queryVo.getStartTime()) && StringUtil.isNotBlank(queryVo.getEndTime())){
                sql.append(" and  dxtz.CREATETIME >= '").append(queryVo.getStartTime()).append("' and dxtz.CREATETIME < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                sql.append("and (jb.YYSDBH,dxtz.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            System.out.println("累计短信通知数------------------------"+ "ljDxtznum");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadList(@Param("loadListVo") DxtzLoadListVo loadListVo) {
            StringBuilder sql = new StringBuilder();
            //主句,zyb.YWID ywid
//            "LEFT JOIN PUB_ZYB_INFO zyb ON dxtz.DXTZID = zyb.YWID " +
            sql.append("select yysd.AH ah,ssdr.SSDRMC dsrmc,dxmb.MBMC,dxtz.*,zyb.YWID from PUB_DXTZ_INFO dxtz " +
                    "LEFT JOIN PUB_YYSD_JB yysd ON dxtz.YYSDBH = yysd.YYSDBH " +
                    "LEFT JOIN PUB_YYSD_SSDR ssdr ON dxtz.YYSDBH = ssdr.YYSDBH and dxtz.SSDRBH = ssdr.SSDRBH " +
                    "LEFT JOIN PUB_DXMB_INFO dxmb on dxmb.BH = dxtz.TEMPLATEID " +
                    "LEFT JOIN (SELECT YWID FROM PUB_ZYB_INFO GROUP BY YWID) zyb ON zyb.YWID = cast(dxtz.DXTZID as varchar(64)) ");
            //条件
            sql.append(" WHERE dxtz.SDYBH = "+loadListVo.getSdybh()+" ");
            if(null != loadListVo.getSendstate()){
                sql.append(" and dxtz.SENDSTATE = ");
                sql.append(loadListVo.getSendstate());
            }
            if(null != loadListVo.getFwzt()){
                sql.append(" and dxtz.FWZT  ");
                if(loadListVo.getFwzt() == -1){ //代表 没有访问状态的记录
                    sql.append(" is null ");
                }else {
                    sql.append(" = "+loadListVo.getFwzt());
                }
            }

            sql.append(" order by dxtz.CREATETIME desc");
            return sql.toString();
        }
        public String loadList1(@Param("loadListVo") DxtzLoadListVo loadListVo,@Param("start") String start,@Param("end") String end) {
            StringBuilder sql = new StringBuilder();

            //主句,zyb.YWID ywid
//            "LEFT JOIN PUB_ZYB_INFO zyb ON dxtz.DXTZID = zyb.YWID " +
            sql.append("select yysd.AH ah,ssdr.SSDRMC dsrmc,dxmb.MBMC,dxtz.*,zyb.YWID from PUB_DXTZ_INFO dxtz " +
                    "LEFT JOIN PUB_YYSD_JB yysd ON dxtz.YYSDBH = yysd.YYSDBH " +
                    "LEFT JOIN PUB_YYSD_SSDR ssdr ON dxtz.YYSDBH = ssdr.YYSDBH and dxtz.SSDRBH = ssdr.SSDRBH " +
                    "LEFT JOIN PUB_DXMB_INFO dxmb on dxmb.BH = dxtz.TEMPLATEID " +
                    "LEFT JOIN (SELECT YWID FROM PUB_ZYB_INFO GROUP BY YWID) zyb ON zyb.YWID = cast(dxtz.DXTZID as varchar(64)) ");
            //条件
            sql.append(" WHERE dxtz.SDYBH = "+loadListVo.getSdybh()+" AND dxtz.CREATETIME >= '"+start+"' AND dxtz.CREATETIME <='"+end+"' ");
            if(null != loadListVo.getSendstate()){
                sql.append(" and dxtz.SENDSTATE = ");
                sql.append(loadListVo.getSendstate());
            }
            if(null != loadListVo.getFwzt()){
                sql.append(" and dxtz.FWZT  ");
                if(loadListVo.getFwzt() == -1){ //代表 没有访问状态的记录
                    sql.append("is null ");
                }else {
                    sql.append(" = "+loadListVo.getFwzt());
                }
            }

            sql.append(" order by dxtz.CREATETIME desc");
            return sql.toString();
        }
    }




    @Update(value = "update PUB_DXTZ_INFO set FWZT = 1,SDJG= 1 where DXTZID = #{dxid}")
    void updateShortUrlStatus(@Param("dxid") Integer dxid);
    @Update(value = "update PUB_YYSD_SSDR set SDJG= 1 where YYSDBH = #{yysdbh} AND SSDRBH = #{ssdrbh}")
    void updateShortUrlStatus1(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);


    @Update(value = "update PUB_DXTZ_INFO set MSGCONTENT = #{masgContent} where DXTZID = #{dxtzid}")
    void updateMsgContent(@Param("dxtzid") Integer dxid , @Param("masgContent") String masgContent);

    @Update(value = "update PUB_MWDX_SEND set FWZT = 1 where CUSID = #{uuid}")
    void updatePlaintextMsgContent(@Param("uuid") String uuid);

    @Update(value = "update PUB_YYSD_SSDR set SDJG= 1 where YYSDBH = #{yysdbh} AND SSDRBH = #{ssdrbh}")
    void updatePlaintextMsgContent1(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

}