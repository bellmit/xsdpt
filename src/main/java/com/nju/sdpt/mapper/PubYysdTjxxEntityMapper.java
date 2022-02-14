package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubYysdTjxxEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PubYysdTjxxEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_YYSD_TJXX
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_YYSD_TJXX
     *
     * @mbggenerated
     */
    int insert(PubYysdTjxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_YYSD_TJXX
     *
     * @mbggenerated
     */
    int insertSelective(PubYysdTjxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_YYSD_TJXX
     *
     * @mbggenerated
     */
    PubYysdTjxxEntity selectByPrimaryKey(String uuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_YYSD_TJXX
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PubYysdTjxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_YYSD_TJXX
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PubYysdTjxxEntity record);

    @Select(value = "SELECT  * FROM PUB_YYSD_TJXX WHERE LX = #{lx} AND TIME = #{time} limit")
    PubYysdTjxxEntity selectByLxAndTime(@Param("lx") String lx,@Param("time") String time);
}