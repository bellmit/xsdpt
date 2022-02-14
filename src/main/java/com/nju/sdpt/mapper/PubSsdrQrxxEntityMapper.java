package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubSsdrQrxxEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubSsdrQrxxEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_SSDR_QRXX
     *
     * @mbggenerated
     */
    int insert(PubSsdrQrxxEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_SSDR_QRXX
     *
     * @mbggenerated
     */
    int insertSelective(PubSsdrQrxxEntity record);

    @Select(value = "select * from PUB_SSDR_QRXX where AJXH=#{ajxh} and FYBH =#{fybh} and DSRBH = #{ssdrbh}")
    List<PubSsdrQrxxEntity>  findByAjxhAndFybhAndDsrbh(@Param("ajxh") Integer ajxh, @Param("fybh") String fybh, @Param("ssdrbh") Integer ssdrbh);

    @Update(value = "update  PUB_SSDR_QRXX set SFQSSDDZQRS = #{sfqssddzqrs} where AJXH=#{ajxh} and FYBH =#{fybh} and DSRBH = #{dsrbh} ")
    void qsqrsUpdate(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh,@Param("dsrbh") Integer dsrbh,@Param("sfqssddzqrs") Integer sfqssddzqrs);

    @Update(value = "update  PUB_SSDR_QRXX set SFTYDZSD = #{sftydzsd} where AJXH=#{ajxh} and FYBH =#{fybh} and DSRBH = #{dsrbh} ")
    void tydzsdUpdate(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh,@Param("dsrbh") Integer dsrbh,@Param("sftydzsd") Integer sftydzsd);
}