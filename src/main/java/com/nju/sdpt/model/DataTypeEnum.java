package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/24.
 */

import com.nju.sdpt.util.StringUtil;

/**
 * 数据类型Enum
 * @author byron
 *
 */
public enum DataTypeEnum {

    INTEGER("01","正整数",null,"java.lang.Integer","integer"),

    DOUBLE("02","小数型",null,"java.lang.Double","double"),

    STRING10("03","字符型",10,"java.lang.String","varchar"),

    STRING20("04","字符型",20,"java.lang.String","varchar"),

    STRING40("05","字符型",40,"java.lang.String","varchar"),

    STRING100("06","字符型",100,"java.lang.String","varchar"),

    STRING200("07","字符型",200,"java.lang.String","varchar"),

    STRING500("08","字符型",500,"java.lang.String","varchar"),

    STRING1000("09","字符型",1000,"java.lang.String","varchar"),

    STRING10000("12","字符型",100000,"java.lang.String","varchar"),

    DATE("10","日期型",null,"java.lang.Date","date"),

    DATETIME("11","日期时间型",null,"java.lang.Datetime","datetime");


    /**
     * 数据类型编号
     */
    String bh;

    /**
     * 数据类型名称
     */
    String name;

    /**
     * 类型长度
     */
    Integer length;

    /**
     * 数据类型java中表示
     */
    String java_type;

    /**
     * 数据类型数据库中表示
     */
    String db_type;

    DataTypeEnum(String bh, String name, Integer length, String java_type, String db_type){
        this.bh = bh;
        this.name=name;
        this.length = length;
        this.java_type = java_type;
        this.db_type = db_type;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJava_type() {
        return java_type;
    }

    public void setJava_type(String javaType) {
        java_type = javaType;
    }

    public String getDb_type() {
        return db_type;
    }

    public void setDb_type(String dbType) {
        db_type = dbType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 根据数据类型名称获得数据类型
     *
     * @param mc
     * @return
     */
    public static DataTypeEnum getEnumByMc(String mc){
        for(DataTypeEnum dataType:DataTypeEnum.values()){
            if(StringUtil.equals(dataType.getName(), mc)){
                return dataType;
            }
        }
        return null;
    }

    /**
     * 根据数据类型的编号获得数据类型
     *
     * @param jdbh
     * @return
     */
    public static DataTypeEnum getEnumByBh(String jdbh){
        for(DataTypeEnum dataType:DataTypeEnum.values()){
            if (jdbh != null) {
                if(StringUtil.equals(dataType.getBh(), jdbh)){
                    return dataType;
                }
            }

        }
        return null;
    }

    /**
     * 根据java数据类型得到数据类型
     * @param java_type
     * @return
     */
    public static DataTypeEnum getEnumByJavaType(String java_type){
        for(DataTypeEnum dataType:DataTypeEnum.values()){
            if(StringUtil.equals(dataType.getJava_type(), java_type)){
                return dataType;
            }
        }
        return null;
    }

    /**
     * 根据数据库数据类型得到数据类型
     * @param db_type
     * @return
     */
    public static DataTypeEnum getEnumByDbType(String db_type){
        for(DataTypeEnum dataType:DataTypeEnum.values()){
            if(StringUtil.equals(dataType.getDb_type(), db_type)){
                return dataType;
            }
        }
        return null;
    }
}
