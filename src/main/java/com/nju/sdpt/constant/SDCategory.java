package com.nju.sdpt.constant;

import com.nju.sdpt.util.StringUtil;

public enum SDCategory {

    WEB_CALL("PUB_WEB_CALL_INFO", "WEB_CALL", "电话送达", "SSDRBH", "CREATETIME", " TOP 1 CASE WHEN SDJG is NULL THEN -1 ELSE SDJG END SDJG", "SDJG"),
    LYLQ("PUB_LYLQ_INFO", "LYLQ", "来院领取", "SSDRBH", "UPDATE_TIME", " TOP 1 CASE WHEN SDJG is NULL THEN -1 ELSE SDJG END SDJG", "SDJG"),
    DXTZ("PUB_DXTZ_INFO", "DXTZ", "短信通知", "SSDRBH", "CREATETIME", " TOP 1 CASE WHEN SDJG is NULL THEN -1 ELSE SDJG END SDJG", "SDJG"),
    ZJSD("PUB_ZJSD_INFO", "ZJSD", "直接送达", "SSDRBH", "SMSJ", " TOP 1 CASE WHEN ZJSDJG is NULL THEN -1 ELSE ZJSDJG END ZJSDJG", "ZJSDJG"),
    EMSSD("PUB_KDTD", "EMSSD", "EMS送达", "DSRBH", "KDID", " CASE WHEN SDJG is NULL THEN '-1' ELSE SDJG END SDJG", "SDJG");

    private String tableName;
    private String type;
    private String name;
    private String dsrColName;
    private String timeName;
    private String selectSql;
    private String sdjgName;

    SDCategory(String tableName, String type, String name, String dsrColName, String timeName, String colName, String sdjgName) {
        this.tableName = tableName;
        this.type = type;
        this.name = name;
        this.dsrColName = dsrColName;
        this.timeName = timeName;
        this.selectSql = colName;
        this.sdjgName = sdjgName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SDCategory valueOfName(String name) {
        SDCategory[] values = SDCategory.values();
        for (SDCategory value : values) {
            if (StringUtil.equals(value.name, name)) {
                return value;
            }
        }
        return null;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }

    public String getDsrColName() {
        return dsrColName;
    }

    public void setDsrColName(String dsrColName) {
        this.dsrColName = dsrColName;
    }

    public String getSdjgName() {
        return sdjgName;
    }

    public void setSdjgName(String sdjgName) {
        this.sdjgName = sdjgName;
    }
}
