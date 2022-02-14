package com.nju.sdpt.data.dynamicdDatabases;

import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {
    @Value("${SDPT.FYDM}")
    static String SDPT_FYDM;
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>() ;
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceHolder.get();
    }

    /**
     * 根据法院代码切换数据源
     * @param fydm
     */
    public static void router(String fydm){
        if(StringUtil.isEmpty(fydm)){
            return;
        }
        if(DataSourceEnum.getSourceByFydm(fydm)!=null){
            //根据法院代码切换
            dataSourceHolder.set(DataSourceEnum.getSourceByFydm(fydm));
        }else{
            for(DataSourceEnum dse:DataSourceEnum.values()){
                //根据法院简称切换
                if(StringUtil.equals(dse.getKey(),fydm)){
                    dataSourceHolder.set(fydm);
                    break;
                }
            }
        }
    }

    /**
     * 根据法院代码切换数据源
     */
    public static void routerToSdpt(){
            dataSourceHolder.set(DataSourceEnum.getSourceByFydm(SDPT_FYDM));
    }

    public static void routerByFybh(String fybh){
        String fydm = FYEnum.getFyByFybh(fybh).getFydm();
        router(fydm);
    }

    /**
     * 根据数据源名称切换数据源
     * @param sourceEnum
     */
    public static void router(DataSourceEnum sourceEnum){
        dataSourceHolder.set(sourceEnum.getKey());
    }
    public static String getCurrentDB(){
        return dataSourceHolder.get();
    }
}
