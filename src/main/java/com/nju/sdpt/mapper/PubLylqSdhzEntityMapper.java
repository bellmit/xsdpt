package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubLylqSdhzEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.model.SdhzModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PubLylqSdhzEntityMapper {
    int deleteByPrimaryKey(Integer sdhzid);

    int insert(PubLylqSdhzEntity record);

    int insertSelective(PubLylqSdhzEntity record);

    PubLylqSdhzEntity selectByPrimaryKey(Integer sdhzid);

    int updateByPrimaryKeySelective(PubLylqSdhzEntity record);

    int updateByPrimaryKey(PubLylqSdhzEntity record);

    List<PubYysdJbEntity> findByName(String ah);

    @Select(value = "SELECT * FROM PUB_LYLQ_SDHZ  WHERE SDHZID=#{sdhzid}")
    SdhzModel findBySdhzid(@Param("sdhzid") Integer sdhzid);

    @Select(value = "SELECT MAX(SDHZID) FROM PUB_LYLQ_SDHZ")
    Integer getMaxSdhzid();

    @Select(value = "SELECT * FROM PUB_LYLQ_SDHZ  WHERE LYLQID=#{lylqid}")
    List<PubLylqSdhzEntity> findBylylqid(@Param("lylqid") Integer lylqid);

    @Select(value = "SELECT * FROM PUB_LYLQ_SDHZ")
    List<PubLylqSdhzEntity> findAll();
}