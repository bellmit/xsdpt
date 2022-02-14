package com.nju.sdpt.service.Impl;

import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.mapper.PubYysdRyxxEntityMapper;
import com.nju.sdpt.model.GdryCheckResult;
import com.nju.sdpt.model.UserContextModel;
import com.nju.sdpt.service.GdRyxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class GdRyxxServiceImpl implements GdRyxxService {

    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;

    @Override
    public GdryCheckResult checkUserLoginResult(String username, String password) {

        GdryCheckResult gdryCheckResult = new GdryCheckResult();
        PubYysdRyxxEntity byYhdm = pubYysdRyxxEntityMapper.findByYhdm(username);

        if(byYhdm != null && password.equals(byYhdm.getYhkl())){
            gdryCheckResult.setSuccess(true);
            gdryCheckResult.setPubYysdRyxxEntity(byYhdm);
        }else{
            gdryCheckResult.setSuccess(false);
        }

        return gdryCheckResult;

    }

    @Override
    public List<PubYysdRyxxEntity> findByYhmc(String gdryxm) {
        return pubYysdRyxxEntityMapper.findByYhmc(gdryxm);
    }

    @Override
    public PubYysdRyxxEntity findByYhdm(String yhm) {
        return pubYysdRyxxEntityMapper.findByYhdm(yhm);
    }

    @Override
    public PubYysdRyxxEntity selectByPrimaryKey(int yhbh) {
        return pubYysdRyxxEntityMapper.selectByPrimaryKey(yhbh);
    }

    @Override
    //双重判断,防止取空了
    public String getYhdm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserContextModel userContext = (UserContextModel) session.getAttribute("userContext");
        String yhdm;
        if(userContext!=null){
            yhdm=  userContext.getYhdm();
        }else {
            yhdm = (String) session.getAttribute("yhm");
        }
        return yhdm;
    }

    @Override
    public List<PubYysdRyxxEntity> findAdminByfybh(String fybh) {
        return pubYysdRyxxEntityMapper.findAdminByfybh(fybh);
    }



    @Override
    public Integer getYhbhBySession(HttpSession session) {
        UserContextModel userContext = (UserContextModel) session.getAttribute("userContext");
        Integer yhbh;
        if(userContext!=null){
            yhbh=  Integer.valueOf(userContext.getYhbh()+"");
        }else {
            String yhdm = (String) session.getAttribute("yhm");
            PubYysdRyxxEntity ryxxEntity = pubYysdRyxxEntityMapper.findByYhdm(yhdm);
            yhbh = ryxxEntity.getYhbh();
        }
        return yhbh;
    }

    @Override
    public PubYysdRyxxEntity getYhBySession(HttpSession session) {
        UserContextModel userContext = (UserContextModel) session.getAttribute("userContext");
        String yhdm = (String) session.getAttribute("yhm");
        return  pubYysdRyxxEntityMapper.findByYhdm(yhdm);
    }
}
