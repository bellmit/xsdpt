package com.nju.sdpt.mapper;

import com.nju.sdpt.entity.PubMwdxSendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PubMwdxSendEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_MWDX_SEND
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_MWDX_SEND
     *
     * @mbggenerated
     */
    int insert(PubMwdxSendEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_MWDX_SEND
     *
     * @mbggenerated
     */
    int insertSelective(PubMwdxSendEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_MWDX_SEND
     *
     * @mbggenerated
     */
    PubMwdxSendEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_MWDX_SEND
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PubMwdxSendEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUB_MWDX_SEND
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PubMwdxSendEntity record);

    @Select(value = "select * from PUB_MWDX_SEND where SENDSTATUS = 0")
    List<PubMwdxSendEntity> selectSendingMwdxList();

    @Select(value = "select * from PUB_MWDX_SEND where LSH = #{serialNumber}")
    List<PubMwdxSendEntity> selectListBySerialNumber(@Param("serialNumber") String serialNumber);

    @Select(value = "select * from PUB_MWDX_SEND where CUSID = #{cusid}")
    PubMwdxSendEntity pubMwdxSendEntity(@Param("cusid") String cusid);

//    @Select(value = "")
    @SelectProvider(type = PubMwdxSendEntityMapper.SqlProviderLylq.class, method = "fgLoadList")
    List<PubMwdxSendEntity> fgLoadList(@Param("fgbh") String fgbh, @Param("fybh") String fybh,@Param("status") int  status);

    @Select(value = "select * from PUB_MWDX_SEND where AJXH = #{ajxh} and FYBH = #{fybh} and SENDTYPE = 3")
    List<PubMwdxSendEntity> findByAjxhAndFybh(@Param("ajxh") Integer ajxh,@Param("fybh") String fybh);

    class SqlProviderLylq{
        public String fgLoadList(@Param("fgbh") String fgbh, @Param("fybh") String fybh,@Param("status") int  status) {
            StringBuilder sql = new StringBuilder();
            //??????
            sql.append("select * from PUB_MWDX_SEND where FGBH= '"+fgbh+"' and FYBH = '"+fybh+"'");
            //??????
            if(status!=-1){
                sql.append(" and SENDSTATUS = "+status);
            }
            return sql.toString();
        }
    }
}