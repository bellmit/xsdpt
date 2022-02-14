package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/7.
 */

import com.nju.sdpt.util.StringUtil;


import lombok.extern.slf4j.Slf4j;


/**
 * 保存Session中关于案件和用户基本信息的类
 *
 */
@Slf4j
public class ContextHolder {


    private static final ThreadLocal userContextHolder = new ThreadLocal();

    private static final ThreadLocal caseContextHolder = new ThreadLocal();

    private static final ThreadLocal def_fydmHolder = new ThreadLocal();


    @SuppressWarnings("unchecked")
    public static void setUserContext(Object context) {
        userContextHolder.set(context);
    }

    public static Object getUserContext() {
        return (Object) userContextHolder.get();
    }

    public static void clearUserCustomerType() {
        userContextHolder.remove();
    }

    @SuppressWarnings("unchecked")
    public static void setCaseContext(Object context) {
        caseContextHolder.set(context);
    }

    public static Object getCaseContext() {
        return (Object) caseContextHolder.get();
    }

    public static void clearCaseCustomerType() {
        caseContextHolder.remove();
    }

    @SuppressWarnings("unchecked")
    public static void setDef_fydm(String fydm) {
        if(StringUtil.isBlank(fydm)){
//            LogUtil.logging("ContextHolder：接收的法院代码fydm为空！", log, LogUtil.type_error);
            System.out.println("ContextHolder：接收的法院代码fydm为空！");
        }
        def_fydmHolder.set((Object)fydm);
    }

    public static String getDef_fydm() {
        return (String) def_fydmHolder.get();
    }

    public static void clearDeffydmCustomerType() {
        def_fydmHolder.remove();
    }

}
