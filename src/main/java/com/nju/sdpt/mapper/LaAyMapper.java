package com.nju.sdpt.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by XXT on 2019/5/24.
 */
@Mapper
@Repository
public interface LaAyMapper {


    @Select(value = "select LAAY from PUB_LA_AY where AJXH = #{ajxh}")
    List<String> getLaayByAjxh(@Param("ajxh") Integer ajxh);

    //获取开庭时间
    @Select(value = "select top 1 RQ from FTNQ_FTSYSQDJ f where SQ_TYPE='已批准' and AJXH=#{ajxh} and SEND_DATE>=all(select SEND_DATE from FTNQ_FTSYSQDJ where SQ_TYPE='已批准' and AJXH=f.AJXH) " )
    Date getKtsjByAjxh(@Param("ajxh") Integer ajxh,@Param("date") Date date);

}
