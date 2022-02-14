package com.nju.sdpt.service;

/**
 * Created by XXT on 2019/5/8.
 */


import com.nju.sdpt.model.UserCheckResult;
import com.nju.sdpt.model.XtyhModel;
import com.nju.sdpt.model.YhqxModel;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 系统用户服务接口 提供与系统用户相关的功能接口
 *
 *
 */
public interface XtyhService {



    /**
     * 根据用户名查找法官名字
     * @param yhm
     * @return
     */

    String getFgmcByYhm(String yhm);
    /**
     * 根据用户名查找密码
     * @param yhm
     * @return
     */
    String getYhklByYhm(String yhm);

    Integer getYhbhByYhm(String yhm);

    XtyhModel getXtyhByYhm(String yhm);

    XtyhModel getXtyhByYhbh(long yhbh);

    /**
     * 根据用户名查看用户权限
     * @param yhm
     * @return
     */
    YhqxModel getYhqxByYhm(String yhm,String fybh);

    XtyhModel getXtyhByYhmc(String auditor);
}
