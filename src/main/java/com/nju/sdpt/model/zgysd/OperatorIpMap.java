package com.nju.sdpt.model.zgysd;


import java.util.concurrent.ConcurrentHashMap;

public class OperatorIpMap {
    private static ConcurrentHashMap<String,String> ipMap = new ConcurrentHashMap<>();

    public static void  addIp(Integer ajxh,String fybh,String ip){
        String key = ajxh+"_"+fybh;
        ipMap.put(key,ip);
    }

    public static String getIp(Integer ajxh,String fybh){
        String key = ajxh+"_"+fybh;
        return ipMap.get(key);
    }
}
