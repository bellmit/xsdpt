package com.nju.sdpt.controller;

import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.model.RepairInfoModel;
import com.nju.sdpt.service.GdRyxxService;
import com.nju.sdpt.service.RepairInfoService;
import com.nju.sdpt.viewobject.RepairQueryVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 修复记录相关
 */
@Controller
@RequestMapping("/repairInfo")
@Api(value = "修复记录相关")
public class
RepairInfoController extends BaseController {

    @Autowired
    RepairInfoService repairInfoService;
    @Resource
    GdRyxxService gdRyxxService;

    /**
     * 加载列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/load_list.zf",method = RequestMethod.POST)
    @ResponseBody
    public List<RepairInfoModel> loadList(HttpServletRequest request, @RequestBody RepairQueryVo queryVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        queryVo.setGdryxm(gdRyxxService.getYhdm(request));
        //查询具体数据
        List<RepairInfoModel> result = repairInfoService.loadList(queryVo);
        return result;
    }

    @RequestMapping(value = "/handle_old_data.zf",method = RequestMethod.POST)
    @ResponseBody
    public Boolean handle_old_data(){
        repairInfoService.handle_old_data();
        return true;
    }
}
