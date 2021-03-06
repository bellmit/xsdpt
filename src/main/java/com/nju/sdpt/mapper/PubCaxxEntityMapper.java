package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubCaxxEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubCaxxEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_CAXX
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer bh);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_CAXX
     *
     * @mbggenerated
     */
    int insert(PubCaxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_CAXX
     *
     * @mbggenerated
     */
    int insertSelective(PubCaxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_CAXX
     *
     * @mbggenerated
     */
    PubCaxxEntity selectByPrimaryKey(Integer bh);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_CAXX
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PubCaxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_CAXX
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PubCaxxEntity record);


    @Select(value = "select * from PUB_CAXX where YYSDBH =#{yysdbh} order by AJXH")
    List<PubCaxxEntity> selectByYysdbh(@Param("yysdbh") Integer yysdbh);

    @Select(value = "select A.* from PUB_CAXX A , PUB_YYSD_JB B where A.YYSDBH = B.YYSDBH and A.AJXH = #{ajxh} and B.FYBH =#{fybh}")
    List<PubCaxxEntity> selectByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);
}