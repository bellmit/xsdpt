package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubSddjJbEntity;
import com.nju.sdpt.model.PubLcspFjbModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubLcspFjbMapper {
    @Select("select LARQ as date, fj.AJXH ajxh, ID id, DESCRIPTION description from PUB_LCSP_FJB fj left join PUB_AJ_JB jb on fj.AJXH = jb.AJXH where fj.WJLJ=NULL")
    List<PubLcspFjbModel> getPubLcspFjModels();

    @Update("update PUB_LCSP_FJB set WJLJ =#{wjlj} where ID = #{id} ")
    void update(@Param("wjlj") String wjlj, @Param("id") String id);

    @Select("select WJLJ wjlj from PUB_LCSP_FJB where ID = #{id} ")
    String getWjlj(@Param("id") String id);

    @Select("select NR from PUB_LCSP_FJB where ID = #{id}")
    Object getContent(@Param("id") String id);
}
