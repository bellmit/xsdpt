package com.nju.sdpt.controller;


import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.model.PubYysdSsdrdzModel;
import com.nju.sdpt.service.SsdrDzService;
import com.nju.sdpt.viewobject.SelectSsdrdzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 受送达人地址信息相关业务 控制层
 */
@RestController
@RequestMapping("/ssdrdz")
public class SsdrAddressController {

    @Autowired
    SsdrDzService ssdrDzService;
    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;
    /**
     * 根据工单编号和受送达人编号查询地址信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/select_ssdrdz.zf",method = RequestMethod.POST)
    public List<PubYysdSsdrdzModel> querySsdrList(HttpServletRequest request, @RequestBody SelectSsdrdzVo selectSsdrdzVo){
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        //查询具体数据
        List<PubYysdSsdrdzModel> result = ssdrDzService.selectSsdrdz(selectSsdrdzVo);
        return result;
    }
}
