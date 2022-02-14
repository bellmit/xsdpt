package com.nju.sdpt.mapper;

import com.nju.sdpt.constant.SDCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SdjgMapper {

    @SelectProvider(type = SqlProvider.class, method = "getExistSdjg")
    String getExistSdjg(Integer yysdbh, Integer ssdrbh, SDCategory type, boolean sdjgSuccess);

    @SelectProvider(type = SqlProvider.class, method = "getSdjgList")
    List<String> getSdjgList(Integer yysdbh, Integer ssdrbh, SDCategory type);


    class SqlProvider {
        public String getExistSdjg(Integer yysdbh, Integer ssdrbh, SDCategory type, boolean sdjgSuccess) {
            StringBuilder sql = new StringBuilder("select ").append(type.getSelectSql());
            sql.append(" from ").append(type.getTableName());
            sql.append(" where YYSDBH=").append(yysdbh);
            sql.append(" and ").append(type.getDsrColName()).append("=").append(ssdrbh);
            sql.append(" order by ").append(type.getTimeName()).append(" desc");
            if (type.equals(SDCategory.EMSSD)) {
                sql.append(" limit 1");
            }
            return sql.toString();
        }

        public String getSdjgList(Integer yysdbh, Integer ssdrbh, SDCategory type) {
            StringBuilder sql =
                    new StringBuilder("select CASE WHEN {SDJG} is NULL THEN "+(type.equals(SDCategory.EMSSD)?"'-1'":"-1")+" ELSE {SDJG} END {SDJG} ");
            sql.append(" from ").append(type.getTableName());
            sql.append(" where YYSDBH=").append(yysdbh);
            sql.append(" and ").append(type.getDsrColName()).append("=").append(ssdrbh);
            return sql.toString().replace("{SDJG}",type.getSdjgName());
        }
    }



}
