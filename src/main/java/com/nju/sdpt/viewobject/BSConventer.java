package com.nju.sdpt.viewobject;

import com.nju.sdpt.util.FydmUtil;

/**
 * 转换
 *
 * @author Administrator
 *
 */
public class BSConventer {
    /**
     * 将案件标识转化为案件序号
     *
     * @param ajbs
     * @return
     */
    public static Integer convertToAJXH(String ajbs) {
        String temp;
        temp = ajbs.substring(5);
        Integer ajxh =Integer.parseInt(temp);
        return ajxh;
    }

    /**
     * 将案件标识转化为法院编号
     *
     * @param ajbs
     * @return
     */
    public static String convertToFYBH(String ajbs) {
        String temp;
        temp = ajbs.substring(0,4);
        Long fybhNum = Long.parseLong(temp);
        return fybhNum.toString();
    }

    /**
     * 将案件序号转化为案件标识
     *
     * @param ajxh
     * @return
     */
    public static String convertToAJBS(Long ajxh, String yjfy, String fydm) {
        String result = new String();
        String s_ajxh = ajxh.toString();
        int len = s_ajxh.length();
        for (int i = len; i < 11; i++) {
            s_ajxh = "0" + s_ajxh;
        }
        int len_Fy = yjfy.length();
        // 改为前面加0
        // for(int i=len_Fy;i<4;i++){
        // yjfy = yjfy+"0";
        // }
        for (int i = len_Fy; i < 4; i++) {
            yjfy = "0" + yjfy;
        }
        result = yjfy + s_ajxh.substring(1);
        return result;
    }



    /**
     * 人员标识转换为用户名
     * @param
     * @param
     * @param
     * @return
     */
    public static long rybsToYhbh(String rybs,String fybh){
        Long rybsNum = Long.parseLong(rybs);
        long yhbh = rybsNum-(Integer.parseInt(fybh))* 66536L;
        return yhbh;
    }

    public static long yhbhToRybs(long yhbh,String fybh){
        return yhbh+(Integer.parseInt(fybh))* 66536L;
    }

    /**
     * 工单专属案件标识转换
     * @param ajxh
     * @param fybh
     * @param yysdbh
     * @return
     */
    public static String convertToYysdAjbs(Integer ajxh, String fybh, Integer yysdbh) {
        StringBuilder result = new StringBuilder();
        String fybhStr = fybh;
        for (int i = fybhStr.length(); i < 4 ; i++){
            fybhStr="0"+fybhStr;
        }
        String ajxhStr = ajxh.toString();
        for (int i = ajxhStr.length(); i < 11 ; i++){
            ajxhStr="0"+ajxhStr;
        }
        String yysdbhStr = yysdbh.toString();
        for (int i = yysdbhStr.length(); i< 11 ; i++){
            yysdbhStr = "0"+yysdbhStr;
        }
        result.append(fybhStr).append(ajxhStr).append(yysdbhStr);
        return result.toString();
    }
}

