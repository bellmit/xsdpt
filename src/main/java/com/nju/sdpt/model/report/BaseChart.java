package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaseChart {
    /**
     * 反射赋值
     * @param mapList
     */
    public void loadNum(List<Map<String, Integer>> mapList) {
        if(CollectionUtils.isNotEmpty(mapList)){
            Map<String, Integer> integerMap = mapList.get(0);
            Field[] fields = this.getClass().getFields();

            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                Integer value = 0;
                if(integerMap.get(name)!=null) {
                   value = Integer.valueOf(String.valueOf(integerMap.get(name)));
                }
                String type = fields[i].getGenericType().toString();
                name = name.substring(0,1).toUpperCase() + name.substring(1);
                if(Objects.equals("class java.lang.Integer",type)){
                    try {
                        for (Method method : this.getClass().getMethods()) {
                            if(Objects.equals(method.getName(),"set"+name)){
                                method.invoke(this,value);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

     String loadSucRateBase(Boolean bfh,Integer sucNum,Integer failNum) {
        String rate = "";
        if(!(sucNum==null||failNum==null||sucNum+failNum==0)){
            rate = String.format("%.2f", ((double)sucNum / (sucNum+failNum)) * 100);
            if(new BigDecimal(rate).compareTo(new BigDecimal(100)) > 0){
                rate = "100.00";
            }
        }else {
            rate = "0.00";
        }

        if(bfh){
            rate += "%";
        }
        return rate;
    }
}
