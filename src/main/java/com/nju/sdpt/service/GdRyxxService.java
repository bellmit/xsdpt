package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubYysdRyxxEntity;
import com.nju.sdpt.model.GdryCheckResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface GdRyxxService {

    GdryCheckResult checkUserLoginResult(String username, String password);

    List<PubYysdRyxxEntity> findByYhmc(String gdryxm);

    PubYysdRyxxEntity findByYhdm(String yhm);

    PubYysdRyxxEntity selectByPrimaryKey(int yhbh);

    String getYhdm(HttpServletRequest request);

    List<PubYysdRyxxEntity> findAdminByfybh(String fybh);


    Integer getYhbhBySession(HttpSession session);

    PubYysdRyxxEntity getYhBySession(HttpSession session);
}
