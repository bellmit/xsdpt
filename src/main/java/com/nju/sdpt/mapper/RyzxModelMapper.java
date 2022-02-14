package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.XyscEntity;
import com.nju.sdpt.entity.lzEntity;
import com.nju.sdpt.model.RyzxModel;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RyzxModelMapper {
    int deleteByPrimaryKey(Integer bh);

    int insert(RyzxModel record);

    int insertSelective(RyzxModel record);

    RyzxModel selectByPrimaryKey(Integer bh);

    int updateByPrimaryKeySelective(RyzxModel record);

    int updateByPrimaryKey(RyzxModel record);

    @Select(value = "select      bh, sdzy, gdfkzl, gdsdcgl, gdsdsbl, sdcsxaj, dhsdcsxaj, yjsdcsxaj, pjsdsc, jswtrwlzhxysj,\n" +
            "    sdzrs, sdcgrs, sdsbrs, tydzsdrs, bddhlac, bddhlar, thzsc, pjthsc, dxfsl, dljdxzl,\n" +
            "    yjcs, lylqcs, tjsj, scope, fybh,yhdm\n" +
            "from pub_ryzx\n" +
            "where scope=1\n" +
            "limit #{limit,jdbcType=INTEGER}\n" +
            "offset #{offset,jdbcType=INTEGER}")
    List<RyzxModel> findInDay(@Param("limit")Integer limit,@Param("offset") Integer offset);

    @Select(value = "select      bh, sdzy, gdfkzl, gdsdcgl, gdsdsbl, sdcsxaj, dhsdcsxaj, yjsdcsxaj, pjsdsc, jswtrwlzhxysj,\n" +
            "    sdzrs, sdcgrs, sdsbrs, tydzsdrs, bddhlac, bddhlar, thzsc, pjthsc, dxfsl, dljdxzl,\n" +
            "    yjcs, lylqcs, tjsj, scope, fybh,yhdm\n" +
            "from pub_ryzx\n" +
            "where scope=2\n" +
            "limit #{limit,jdbcType=INTEGER}\n" +
            "offset #{offset,jdbcType=INTEGER}")
    List<RyzxModel> findInWeek(@Param("limit")Integer limit,@Param("offset") Integer offset);

    @Select(value = "select      bh, sdzy, gdfkzl, gdsdcgl, gdsdsbl, sdcsxaj, dhsdcsxaj, yjsdcsxaj, pjsdsc, jswtrwlzhxysj,\n" +
            "    sdzrs, sdcgrs, sdsbrs, tydzsdrs, bddhlac, bddhlar, thzsc, pjthsc, dxfsl, dljdxzl,\n" +
            "    yjcs, lylqcs, tjsj, scope, fybh,yhdm\n" +
            "from pub_ryzx\n" +
            "where scope=3\n" +
            "limit #{limit,jdbcType=INTEGER}\n" +
            "offset #{offset,jdbcType=INTEGER}")
    List<RyzxModel> findInMonth(@Param("limit")Integer limit,@Param("offset") Integer offset);

    @Select(value = "select      bh, sdzy, gdfkzl, gdsdcgl, gdsdsbl, sdcsxaj, dhsdcsxaj, yjsdcsxaj, pjsdsc, jswtrwlzhxysj,\n" +
            "    sdzrs, sdcgrs, sdsbrs, tydzsdrs, bddhlac, bddhlar, thzsc, pjthsc, dxfsl, dljdxzl,\n" +
            "    yjcs, lylqcs, tjsj, scope, fybh,yhdm\n" +
            "from pub_ryzx\n" +
            "where scope=4\n" +
            "limit #{limit,jdbcType=INTEGER}\n" +
            "offset #{offset,jdbcType=INTEGER}")
    List<RyzxModel> findInAll(@Param("limit")Integer limit,@Param("offset") Integer offset);

    @Select(value = "select      bh, sdzy, gdfkzl, gdsdcgl, gdsdsbl, sdcsxaj, dhsdcsxaj, yjsdcsxaj, pjsdsc, jswtrwlzhxysj,\n" +
            "    sdzrs, sdcgrs, sdsbrs, tydzsdrs, bddhlac, bddhlar, thzsc, pjthsc, dxfsl, dljdxzl,\n" +
            "    yjcs, lylqcs, tjsj, scope, fybh,yhdm\n" +
            "from pub_ryzx\n" +
            "where yhdm =#{Yhdm}\n" +
            "and scope=#{scope}")
    RyzxModel findByYhdmandScope(@Param("Yhdm")String yhdm,@Param("Scope")Integer scope);

    void insertOrUpdate(RyzxModel ryzxModel);

    @Select(value = "select count(yhdm)\n" +
            "from pub_ryzx\n" +
            "where scope=#{Scope} and yhdm is Not Null"+
            "  and CASE\n" +
            "    WHEN #{fybh}=0 THEN 1=1\n" +
            "        ELSE fybh = #{fybh,jdbcType=INTEGER} or fybh = (0 - #{fybh,jdbcType=INTEGER})\n" +
            "            END \n")
    Integer getTotal(@Param("Scope")Integer scope,@Param("fybh")Integer fybh);

    @Select(value = "select yysdbh\n" +
            "from (\n" +
            "         select count(t.yysdbh) num, yysdbh\n" +
            "         from (\n" +
            "                  select fybh, targetname, creater, MIN(creattime) createtime, yysdbh\n" +
            "                  from pub_log\n" +
            "                  where logtype = 9\n" +
            "                  group by fybh, targetname, creater, yysdbh\n" +
            "              ) as t\n" +
            "where datediff(t.createtime,getdate())<20"+
            "         group by yysdbh) as t\n" +
            "where t.num>1")
    List<Integer> getYysdbhBylz();

    @Select(value = "select log.fybh fybh, log.targetname targetname, MIN(log.creattime) createtime, log.yysdbh yysdbh, ryxx.yhdm yhdm\n" +
            "from pub_log log\n" +
            "left join pub_yysd_ryxx ryxx on log.targetname=ryxx.yhmc \n" +
            "where logtype = 9 and yysdbh=#{yysdbh}\n" +
            "group by log.fybh, log.targetname, log.yysdbh,ryxx.yhdm "+
    "order by createtime asc")
    List<lzEntity> getlzEntity(@Param("yysdbh")int yysdbh);

    @Insert(value = "insert into pub_lzsc(yysdbh, yhdm, sc) values (#{yysdbh},#{yhdm},#{sc}) on conflict (yysdbh,yhdm)"+
                    "do update set sc=excluded.sc")
    void insertRwlz(@Param("yysdbh")int yysdbh,@Param("yhdm")String yhdm,@Param("sc")int sc);

    @Select(value = "select  log.targetname targetname, log.creattime createtime, log.yysdbh yysdbh,ryxx.yhdm yhdm\n" +
            "from pub_log log left join pub_yysd_ryxx ryxx on log.targetname=ryxx.yhmc\n" +
            "where logtype = 9 and datediff(creattime,getdate())<20")
    List<lzEntity> getEntity();

    @Select(value = "select MIN(creattime)\n" +
            "from pub_log\n" +
            "where yysdbh=#{yysdbh} and creattime>'${creattime}'")
    String getTime(@Param("yysdbh")int yysdbh,@Param("creattime") String creattime);

    @Insert(value = "insert into pub_xysc(yysdbh, yhdm, sc) values (#{yysdbh},#{yhdm},#{sc}) on conflict (yhdm,yysdbh) "+
            "do update set sc=excluded.sc")
    void insertXysc(@Param("yysdbh")int yysdbh,@Param("yhdm")String yhdm,@Param("sc")int sc);

    @Select(value = "select MAX(log.creattime) createtime,\n" +
            "       log.yysdbh         yysdbh,\n" +
            "       ryxx.yhdm          yhdm,\n" +
            "       (\n" +
            "           CASE\n" +
            "               WHEN jb.sdsj is NULL then getdate()\n" +
            "               ELSE jb.sdsj\n" +
            "               END\n" +
            "           ) sdsj\n" +
            "from pub_log log\n" +
            "         left join pub_yysd_ryxx ryxx on log.targetname = ryxx.yhmc\n" +
            "         left join pub_yysd_jb jb on log.yysdbh = jb.yysdbh\n" +
            "where logtype = 9\n" +
            "and datediff(sdsj,getdate())<20"+
            "group by log.fybh, log.targetname, log.yysdbh, ryxx.yhdm, sdsj\n" +
            "order by createtime asc")
    List<XyscEntity> getXyscEntity();

    @Select(value = "select sum(sc)\n" +
            "from pub_lzsc xysc\n" +
            "left join pub_yysd_jb jb on xysc.yysdbh=jb.yysdbh\n" +
            "where xysc.yhdm=#{yhdm}\n" +
            "and '${end}'>jb.yysj\n"+
            "and jb.yysj>'${start}'\n"+
            "group by xysc.yhdm")
    Integer getSdsc(@Param("yhdm")String yhdm,@Param("start")String start,@Param("end")String end);

    @Select(value = "select sum(sc)\n" +
            "from pub_xysc xysc\n" +
            "left join pub_yysd_jb jb on xysc.yysdbh=jb.yysdbh\n" +
            "where xysc.yhdm=#{yhdm}\n" +
            "and '${end}'>jb.yysj\n"+
            "and jb.yysj>'${start}'\n"+
            "group by xysc.yhdm")
    Integer getXysc(@Param("yhdm")String yhdm,@Param("start")String start,@Param("end")String end);

    @Select(value = "select count(yysdbh)\n" +
            "from pub_yysd_jb\n" +
            "where gdryxm=#{yhdm}\n" +
            "and yysj>'${start}'\n"+
            "and yysj<'${end}'\n"+
            "group by gdryxm;")
    Integer getTotalNum(@Param("yhdm")String yhdm,@Param("start")String start,@Param("end")String end);

    @Select(value = "select      bh, sdzy, gdfkzl, gdsdcgl, gdsdsbl, sdcsxaj, dhsdcsxaj, yjsdcsxaj, pjsdsc, jswtrwlzhxysj,\n" +
            "    sdzrs, sdcgrs, sdsbrs, tydzsdrs, bddhlac, bddhlar, thzsc, pjthsc, dxfsl, dljdxzl,\n" +
            "    yjcs, lylqcs, tjsj, scope, fybh,yhdm,dhjtlacrate\n" +
            "from pub_ryzx\n" +
            "where scope = #{scope,jdbcType=INTEGER}\n" +
            "  and fybh>0 and CASE\n" +
            "    WHEN #{fybh}=0 THEN 1=1\n" +
            "        ELSE fybh = #{fybh,jdbcType=INTEGER} or fybh = (0 - #{fybh,jdbcType=INTEGER})\n" +
            "            END \n" +
            "  limit #{limit}\n" +
            "    offset #{offset}")
    List<RyzxModel> findInLimit(@Param("limit")Integer limit,@Param("offset") Integer offset, @Param("scope")Integer scope,@Param("fybh")Integer fybh);
}