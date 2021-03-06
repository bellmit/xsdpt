package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubLylqInfoEntity;
import com.nju.sdpt.model.LylqModel;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.LylqLoadListVo;
import com.nju.sdpt.viewobject.ReportQueryVo;
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
public interface PubLylqInfoEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer lylqid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    int insert(PubLylqInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    int insertSelective(PubLylqInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    PubLylqInfoEntity selectByPrimaryKey(Integer lylqid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PubLylqInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(PubLylqInfoEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_LYLQ_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PubLylqInfoEntity record);

    @SelectProvider(type = SqlProviderLylq.class,method = "loadList")
    List<LylqModel> loadList(@Param("lylqLoadListVo") LylqLoadListVo lylqLoadListVo);

    @Select(value = "SELECT *,ssdr.SSDRMC FROM PUB_LYLQ_INFO ly ,PUB_YYSD_SSDR ssdr WHERE ly.YYSDBH = ssdr.YYSDBH and ly.SSDRBH = ssdr.SSDRBH and ly.YYSDBH = #{yysdbh}  ORDER BY ly.CREATE_TIME")
    List<LylqModel> findByYysdbh(@Param("yysdbh") Integer yysdbh);


    @SelectProvider(type = SqlProviderLylq.class,method = "fgloadList")
    List<LylqModel> fgLoadList(@Param("yhm") String yhm, @Param("fybh") String fybh, @Param("sdjg") int sdjg);

    @Select(value = "SELECT * FROM PUB_LYLQ_INFO  WHERE AJXH=#{ajxh} AND FYBH=#{fybh}")
    List<LylqModel> findByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);

    @Select(value = "select * from PUB_LYLQ_INFO where YYSDBH =#{yysdbh} and SSDRBH = #{ssdrbh} order by UPDATE_TIME desc")
    List<PubLylqInfoEntity> findByYysdbhAndSsdrbh(@Param("yysdbh") Integer yysdbh, @Param("ssdrbh") Integer ssdrbh);

    @Select(value = "SELECT * FROM PUB_LYLQ_INFO  WHERE AJXH=#{ajxh} AND FYBH=#{fybh} AND YYSDBH = NULL")
    List<LylqModel> findFgLylqModelsByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);

    @Select(value = "SELECT L.*,S.SSDRMC ,'????????????' AS FQR FROM PUB_LYLQ_INFO  AS L LEFT JOIN PUB_YYSD_SSDR AS S ON L.YYSDBH = S.YYSDBH AND L.SSDRBH = S.SSDRBH WHERE AJXH=#{ajxh} AND FYBH=#{fybh} AND L.YYSDBH is not NULL")
    List<LylqModel> findGdLylqModelsByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);

    @Select(value = "SELECT dxtz.* from PUB_LYLQ_INFO dxtz " +
            " LEFT JOIN PUB_YYSD_SSDR ssdr ON dxtz.YYSDBH = ssdr.YYSDBH AND dxtz.SSDRBH = ssdr.SSDRBH " +
            " WHERE ssdr.SSDRMC = #{ssdrmc} AND ssdr.SFZHM = #{sfzhm} order by dxtz.CREATE_TIME desc ")
    List<PubLylqInfoEntity> queryByssdrMcAndSfzhm(@Param("ssdrmc") String ssdrmc, @Param("sfzhm") String sfzhm);

    @SelectProvider(type = SqlProviderLylq.class,method = "ljNum")
    Integer ljNum(@Param("queryVo") ReportQueryVo queryVo);

    @Update(value = "UPDATE PUB_LYLQ_INFO SET SDJG = #{sdjg} WHERE LYLQID = #{lylqid}")
    void updateSdjgByLylqId(@Param("lylqid") Integer id, @Param("sdjg") Integer sdjg);

    class SqlProviderLylq{
        public String ljNum(@Param("queryVo") ReportQueryVo queryVo){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT count(0) FROM PUB_LYLQ_INFO lylq");
            sql.append(" LEFT JOIN PUB_YYSD_JB jb ON jb.YYSDBH = lylq.YYSDBH ");
            if(Objects.equals(queryVo.getSdptRy(),true)){
                //??????????????????
                sql.append(" where (lylq.SDYBH = ").append(queryVo.getYhbh()).append(" OR jb.FYBH in ('").append(StringUtil.splitJoint(queryVo.getFybhSet(),"','")).append("') )");
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
                sql.append(" and lylq.CREATE_TIME >= '").append(queryVo.getStartTime()).append("' and lylq.CREATE_TIME < '").append(queryVo.getEndTime()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getTsName())) {
                sql.append(" and jb.bmmc = '").append(queryVo.getTsName()).append("'");
            }
            if(StringUtil.isNotBlank(queryVo.getDsrDw())) {
                sql.append("and (jb.YYSDBH,lylq.SSDRBH) in (select YYSDBH,SSDRBH from pub_yysd_ssdr where ssdrssdw = '").append(queryVo.getDsrDw()).append("')");
            }
            System.out.println("??????????????????------------------------"+ "ljlylqnum");
            System.out.print(sql.toString());
            return sql.toString();
        }
        public String loadList(@Param("lylqLoadListVo") LylqLoadListVo lylqLoadListVo) {
            StringBuilder sql = new StringBuilder();
            //??????
            sql.append("SELECT gd.AH,ssdr.SSDRMC,lylq.* FROM PUB_LYLQ_INFO lylq "
                      +"LEFT JOIN PUB_YYSD_JB gd ON gd.YYSDBH = lylq.YYSDBH"
                      +" LEFT JOIN PUB_YYSD_SSDR ssdr ON ssdr.SSDRBH = lylq.SSDRBH and ssdr.YYSDBH = lylq.YYSDBH");
            //??????
            sql.append(" WHERE lylq.SDYBH = "+lylqLoadListVo.getSdybh()+" ");
            if(null != lylqLoadListVo.getLqstate()){
                if(Objects.equals(lylqLoadListVo.getLqstate(),0)){
                    sql.append(" and (lylq.LQSTATE = 0 or lylq.LQSTATE is NULL) ");
                }else {
                    sql.append(" and lylq.LQSTATE = " + lylqLoadListVo.getLqstate());
                }
            }
            sql.append(" order by lylq.YYLQSJ desc");
            return sql.toString();
        }

        public String fgloadList(@Param("yhm") String yhm, @Param("fybh") String fybh, @Param("sdjg") int sdjg) {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from PUB_LYLQ_INFO where FGBH ='").append(yhm).append("'").append(" and FYBH ='").append(fybh).append("'");
            if(sdjg!=-1){
                sql.append(" and LQSTATE =").append(sdjg);
            }
            sql.append(" order by YYLQSJ desc");
            return sql.toString();
        }
    }
}