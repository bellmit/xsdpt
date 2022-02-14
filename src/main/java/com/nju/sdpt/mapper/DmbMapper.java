package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.DmbEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by XXT on 2019/5/8.
 */
@Mapper
@Repository
public interface DmbMapper {

    /**
     * 根据类别编号和代码编号获取代码表model
     * @param dmbh
     * @param lbbh
     * @return
     */

    @Select(value = "select * from PUB_DMB where DMBH = #{dmbh} AND LBBH = #{lbbh}")
    DmbEntity getDmByLbbhAndDmbh(@Param("lbbh") String lbbh, @Param("dmbh") String dmbh);




    /**
     * 获取系统常用工具表中的url地址
     * @param name
     * @return
     */
    @Select(value = "select LJ from PUB_XTGL_CYGJB where GJMC = #{name}")
    String findXtcygjbUrlByGjmc(@Param("name") String name);

}
