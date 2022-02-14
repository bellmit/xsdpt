package com.nju.sdpt.service;


import com.nju.sdpt.model.DmbModel;
import java.util.Date;



/**
 * 代码表服务
 *
 */
public interface DmService {
    /**
     * 根据类别编号和代码编号得到某个法院的个性代码
     *
     * @param lbbh
     *            类别编号
     * @param dmbh
     *            代码编号
     *            法院编号
     * @return DmModel
     */
    public DmbModel getDmByLbbhAndDmbh(String lbbh, String dmbh);


    /**
     * 通过案件性质、审判程序、审判程序代字获取当事人诉讼地位
     */
    public DmbModel getDsrssdwByAjxzAndSpcxAndSpcxdz(String dmbh, String ajxz, String spcx, String spcxdz, Date larq);

    /**
     * 无缓存的Dmb服务
     * @param lbbh
     * @param dmbh
     */
    public DmbModel getModuleByLbbhAndDmbh2(String lbbh, String dmbh);
}
