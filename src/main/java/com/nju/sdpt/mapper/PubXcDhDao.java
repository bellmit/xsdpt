package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubXcDh;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (PubXcDh)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-02 19:03:02
 */
@Mapper
@Repository
public interface PubXcDhDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PubXcDh queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param pubXcDh 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<PubXcDh> queryAllByLimit(PubXcDh pubXcDh, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param pubXcDh 查询条件
     * @return 总行数
     */
    long count(PubXcDh pubXcDh);

    /**
     * 新增数据
     *
     * @param pubXcDh 实例对象
     * @return 影响行数
     */
    int insert(PubXcDh pubXcDh);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PubXcDh> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PubXcDh> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PubXcDh> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PubXcDh> entities);

    /**
     * 修改数据
     *
     * @param pubXcDh 实例对象
     * @return 影响行数
     */
    int update(PubXcDh pubXcDh);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

