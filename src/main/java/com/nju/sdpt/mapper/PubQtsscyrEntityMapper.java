package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubQtsscyrEntity;
import com.nju.sdpt.entity.PubQtsscyrKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubQtsscyrEntityMapper {

    @Select(value = "select RY.* from PUB_QTSSCYR RY ,DSR_QTSSCYR_GX GX  where RY.AJXH = #{ajxh} AND GX.DSRBH = #{dsrbh} AND RY.QTSSCYRBH = GX.QTSSCYRBH AND RY.AJXH = GX.AJXH ")
    List<PubQtsscyrEntity> getQtsscyr(@Param( "ajxh") int ajxh, @Param( "dsrbh") int dsrbh);
}