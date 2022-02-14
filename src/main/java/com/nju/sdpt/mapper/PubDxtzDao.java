package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubDxtz;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (PubDxtz)表数据库访问层
 *
 * @author makejava
 * @since 2021-10-26 09:12:06
 */
@Mapper
@Repository
public interface PubDxtzDao {

    /**
     * 通过ID查询单条数据
     *
     * @param dxtzid 主键
     * @return 实例对象
     */
    PubDxtz queryById(Integer dxtzid);

    /**
     * 查询指定行数据
     *
     * @param pubDxtz 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<PubDxtz> queryAllByLimit(PubDxtz pubDxtz, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param pubDxtz 查询条件
     * @return 总行数
     */
    long count(PubDxtz pubDxtz);

    /**
     * 新增数据
     *
     * @param pubDxtz 实例对象
     * @return 影响行数
     */
    int insert(PubDxtz pubDxtz);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PubDxtz> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PubDxtz> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PubDxtz> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PubDxtz> entities);

    /**
     * 修改数据
     *
     * @param pubDxtz 实例对象
     * @return 影响行数
     */
    int update(PubDxtz pubDxtz);

    /**
     * 通过主键删除数据
     *
     * @param dxtzid 主键
     * @return 影响行数
     */
    int deleteById(Integer dxtzid);

}

