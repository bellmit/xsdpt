package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.PubGroupEntity;
import com.nju.sdpt.entity.PubRwwtEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.model.RwwtModel;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.Impl.RwwtServiceImpl;
import com.nju.sdpt.service.RwwtService;
import com.nju.sdpt.viewobject.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务委托功能控制层
 * @author zzy
 * @date 2020/06/15
 */
@RestController
public class RwwtController {
    @Autowired
    RwwtService rwwtService;
    @Autowired
    GdRyxxService gdRyxxService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    /**
     * 受委托组成员查询
     * @param request
     * @param groupName
     * @return
     */
    @PostMapping("/query_group.aj")
    public List<PubGroupEntity> getGroupRyxx(HttpServletRequest request, String groupName, String fybh){
        DynamicDataSource.router(SDPT_FYDM);
        List<PubGroupEntity> ret = new ArrayList<>();
        try {
            ret = rwwtService.getGroupRyxx(groupName,fybh);
        } catch (Exception e) {

        }
        return ret;
    }

    /**
     * 根据工单号查询任务委托记录
     * @param request
     * @param yysdbh
     * @return
     */
    @PostMapping("/look_rwwt.aj")
    public List<RwwtModel> look_rwwt(HttpServletRequest request, Integer yysdbh){
        DynamicDataSource.router(SDPT_FYDM);
        return rwwtService.getRwwtByYysdbh(yysdbh);
    }

    /**
     * 获取当前用户的任务委托列表
     * @param request
     * @param cljg 处理结果
     * @return
     */
    @PostMapping("/getRwwtList.aj")
    public List<RwwtModel> getRwwtList(HttpServletRequest request, Integer cljg){
        DynamicDataSource.router(SDPT_FYDM);
        HttpSession session = request.getSession();
        String yhm = (String) session.getAttribute("yhm");
        String yhmc = gdRyxxService.findByYhdm(yhm).getYhmc();
        return rwwtService.getRwwtByClrmcAndCljg(yhmc,cljg);
    }

    /**
     * 上传处理结果
     * @param request
     * @param cljg 处理结果
     * @return
     */
    @PostMapping("/uploadRwwtjg.aj")
    public void uploadRwwtjg(HttpServletRequest request, String cljg,Integer rwwtid){
        DynamicDataSource.router(SDPT_FYDM);
         rwwtService.uploadRwwtjg(rwwtid,cljg);
    }
}
