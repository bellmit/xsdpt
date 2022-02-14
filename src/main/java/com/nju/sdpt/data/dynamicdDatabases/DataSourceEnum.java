package com.nju.sdpt.data.dynamicdDatabases;


import com.nju.sdpt.util.StringUtil;

/**
 * 数据源枚举
 *
 */
public enum DataSourceEnum {
    /**
     * 公告
     */
    GG("gonggao","gonggao"),

    /**
     * 测试库
     */
    TEST("120000 2000","test") ,/**
     /**
     * 测试库
     */
    SDPTTEST("120000 2000 2000","sdpttest") ,/**
     * 法综测试库
     */
    FZTEST("120000 2","fztest") ,
    /**
     * 天津高院
     */
    TJGY("120000 200","tjgy") ,
    /**
     * 天津一中院
     */
    TJYZY("120100 210","tjyzy"),
    /**
     * 天津二中院
     */
    TJEZY("120200 220","tjezy"),
    /**
     * 天津海事法院
     */
    TJHSFY("960200 230","tjhsfy"),
    /**
     * 天津和平法院
     */
    TJHPFY("120101 211","tjhpfy"),
    /**
     * 天津南开法院
     */
    TJNKFY("120104 212","tjnkfy"),
    /**
     * 天津河北法院
     */
    TJHBFY("120105 213","tjhbfy"),
    /**
     * 天津红桥法院
     */
    TJHQFY("120106 214","tjhqfy"),
    /**
     * 天津西青法院
     */
    TJXQFY("120107 215","tjxqfy"),
    /**
     * 天津北辰法院
     */
    TJBCFY("120108 216","tjbcfy"),
    /**
     * 天津河东法院
     */
    TJHDFY("120202 221","tjhdfy"),

    /**
     * 天津河西法院
     */
    TJHXFY("120203 222","tjhxfy"),

    /**
     * 天津塘沽法院
     */
    TJTGFY("120204 223","tjtgfy"),

    /**
     * 天津汉沽法院
     */
    TJHGFY("120205 224","tjhgfy"),

    /**
     * 天津大港法院
     */
    TJDGFY("120206 225","tjdgfy"),

    /**
     * 天津东丽法院
     */
    TJDLFY("120207 226","tjdlfy"),

    /**
     * 天津津南法院
     */
    TJJNFY("120208 227","tjjnfy"),

    /**
     * 天津宁河法院
     */
    TJNHFY("120221 228","tjnhfy"),

    /**
     * 天津武清法院
     */
    TJWQFY("120222 217","tjwqfy"),

    /**
     * 天津静海法院
     */
    TJJHFY("120223 218","tjjhfy"),

    /**
     * 天津宝坻法院
     */
    TJBDFY("120224 219","tjbdfy"),

    /**
     * 天津蓟县法院
     */
    TJJXFY("120225 21A","tjjxfy"),
    /**
     * 天津开发区人民法院
     */
    TJKFQFY("120241 229","tjkfqfy"),
    /**
     * 天津滨海新区法院
     */
    TJBHXQFY("120242 22A","tjbhxqfy"),
    /**
     * 天津铁路法院
     */
    TJTLFY("920103 132","tjtlfy"),
    /**
     * 天津铁路法院
     */
    TJSZY("120300 230","tjszy"),
    /**
     * 滨海新区
     */


    /**
     * 天津自贸法院
     */
    TJZMFY("120304 234","tjzmfy");

    private String fydm ;
    private String key ;

    DataSourceEnum(String fydm,String key){
        this.fydm = fydm ;
        this.key = key ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public static String getSourceByFydm(String fydm){
        if(StringUtil.isEmpty(fydm)) {
            return null;
        }
        for (DataSourceEnum src : DataSourceEnum.values()) {
            if (StringUtil.equals(fydm, src.getFydm())) {
                return src.getKey();
            }
        }
        return null;
    }
}
