package com.nju.sdpt.service.Impl;

/**
 * Created by XXT on 2019/5/8.
 */

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.XtglYhbEntity;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.mapper.XtglYhbMapper;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.DmService;
import com.nju.sdpt.service.XtyhService;
import com.nju.sdpt.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 系统用户服务的实现
 *
 */
@Service
@Slf4j
public class XtyhServiceImpl implements XtyhService {



    @Autowired
    private XtglYhbMapper xtglYhbMapper;

    @Autowired
    private DmbMapper dmbMapper;

    @Autowired
    private DmService dmService;

    public UserCheckResult getXtyh(String yhdm, String yhkl) {
        UserCheckResult result = new UserCheckResult();
        result.setSuccess(false);
        XtglYhbEntity xtyh = xtglYhbMapper.findByYhdm(yhdm);
        if (null != xtyh) {
            if (yhkl.equals(xtyh.getYhkl())){
                //密码验证成功
                result.setSuccess(true);
                result.setXtyh(new XtyhModel(xtyh));
                return result;
            }else{
                result.setSuccess(false);
                result.setResultCode(ResultCodeEnum.ILLEGAL_PASSWORD);
                return result;
            }
        }else{
            result.setSuccess(false);
            result.setResultCode(ResultCodeEnum.ILLEGAL_USERNAME);
            return result;
        }
    }


    /**
     * 根据用户名查找法官名称
     * @param yhm
     * @return
     */
    public String getFgmcByYhm(String yhm){
      return xtglYhbMapper.findByYhdm(yhm).getYhmc();
    }

    /**
     * 根据用户名查找法官密码
     * @param yhm
     * @return
     */
    public String getYhklByYhm(String yhm) {
        try{
            return xtglYhbMapper.findByYhdm(yhm).getYhkl();
        }catch (Exception e){
            e.printStackTrace();
            return "1234";
        }

    }

    /**
     * 根据用户名查找法官密码
     * @param yhm
     * @return
     */
    public Integer getYhbhByYhm(String yhm) {
         return xtglYhbMapper.findByYhdm(yhm).getYhbh();
    }

    /**
     * 根据用户名查找用户
     * @param yhm
     * @return
     */
    @Override
    public XtyhModel getXtyhByYhm(String yhm) {
        return new XtyhModel(xtglYhbMapper.findByYhdm(yhm));
    }

    @Override
    public XtyhModel getXtyhByYhbh(long yhbh) {
        return new XtyhModel(xtglYhbMapper.findByyhbh(yhbh));
    }

    @Override
    public YhqxModel getYhqxByYhm(String yhm,String fybh) {
        DynamicDataSource.routerByFybh(fybh);
        XtglYhbEntity xtglYhbEntity = xtglYhbMapper.findByYhdm(yhm);
        List<String> jsList = xtglYhbMapper.findJsByYhbh(xtglYhbEntity.getYhbh());
        DmbModel dmbEntity = dmService.getDmByLbbhAndDmbh("USR206-99",xtglYhbEntity.getYhbm());
        YhqxModel yhqxModel = new YhqxModel();
        yhqxModel.setYhdm(yhm);
        yhqxModel.setYhmc(xtglYhbEntity.getYhmc());
        yhqxModel.setBmbh(xtglYhbEntity.getYhbm());
        yhqxModel.setBmmc(dmbEntity.getDmms());
        yhqxModel.setFybh(fybh);
        //检查是否有院长身份
        for (String js:jsList){
            if(StringUtil.equals(js,"院长")||StringUtil.equals(js,"副院长")||StringUtil.equals(js,"院领导")){
                yhqxModel.setAuthority(1);
                return yhqxModel;
            }
        }
        //非审判部门权限为2
        if (!StringUtil.equals(dmbEntity.getBz(),"审判")){
            yhqxModel.setAuthority(2);
            return yhqxModel;
        }
        //检查是否有庭长身份
        for (String js:jsList){
            if(StringUtil.isNotBlank(js) && (js.indexOf("庭长") >= 0 || js.indexOf("局长") >= 0)){
                yhqxModel.setAuthority(3);
                return yhqxModel;
            }
        }
        //其他情况
        yhqxModel.setAuthority(4);
        return yhqxModel;
    }

    @Override
    public XtyhModel getXtyhByYhmc(String yhmc) {
        return new XtyhModel(xtglYhbMapper.findByYhdmc(yhmc));
    }

}
