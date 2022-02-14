package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubYysdSdwsEntity;
import com.nju.sdpt.entity.PubYysdWsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubYysdSdwsEntityMapper {
    int deleteByPrimaryKey(Integer bh);

    int insert(PubYysdSdwsEntity record);

    int insertSelective(PubYysdSdwsEntity record);

    PubYysdSdwsEntity selectByPrimaryKey(Integer bh);

    int updateByPrimaryKeySelective(PubYysdSdwsEntity record);

    int updateByPrimaryKey(PubYysdSdwsEntity record);

    @Select(value = "select * from PUB_YYSD_WS where YYSDBH = #{yysdbh} and SSDRBH = #{ssdrbh}")
    List<PubYysdWsEntity> getDsrwslb(Integer yysdbh, Integer ssdrbh);

    @Select(value = "select * from PUB_YYSD_WS where YYSDBH = #{yysdbh} and BH = #{bh}")
    PubYysdWsEntity getByYysdbhAndBh(Integer yysdbh, Integer bh);


}