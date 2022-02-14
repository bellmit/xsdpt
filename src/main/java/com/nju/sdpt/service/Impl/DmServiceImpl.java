package com.nju.sdpt.service.Impl;

/**
 * Created by XXT on 2019/5/8.
 */

import com.nju.sdpt.entity.DmbEntity;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.model.DmbModel;
import com.nju.sdpt.service.DmService;
import com.nju.sdpt.service.XxxService;
import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
@Slf4j

public class DmServiceImpl implements DmService {

    @Autowired
    private DmbMapper dmbMapper;
    @Autowired
    private XxxService xxxService;

    /**
     *
     * @return DmModel
     */
    public DmbModel getDmByLbbhAndDmbh(String lbbh, String dmbh) {
        DmbEntity dmbEntity = dmbMapper.getDmByLbbhAndDmbh(lbbh, dmbh);
        if (dmbEntity == null) {
            return null;
        }
        return new DmbModel(dmbEntity);
    }



    public DmbModel getDsrssdwByAjxzAndSpcxAndSpcxdz(String dmbh, String ajxz, String spcx, String spcxdz, Date larq){
        if (!StringUtil.isBlank(ajxz))
            ajxz = StringUtil.trim(ajxz);
        if (!StringUtil.isBlank(spcx))
            spcx = StringUtil.trim(spcx);

        // 1 2 3 4 5
        // (1-Z)(A-D)(A-C)(A-B)(1-)
        // 1--1刑事，2民事，3经济纠纷，4海事海商，5知识产权，
        // 6行政，7赔偿，8执行，/9诉前保全/，A请示，Z其他
        // 2--A一审，B二审，C审判监督（一审程序），D审判监督（二审程序）
        // E复核,F请示,G破产,H申诉复查,I减刑,J假释,S诉前保全,T适用特别,Z制裁复议

        String temp_ajxz = ajxz;
        if (temp_ajxz.equals("2") || temp_ajxz.equals("3")
                || temp_ajxz.equals("4") || temp_ajxz.equals("5"))
            temp_ajxz = "2";
        // 获得备注
        //如果信息项存在2016年新法标，并且（案件的立案时间在2016。01.01之后，或者信息项是“扣除理由”、“中止原因”、“延迟理由”，立案时间在2016-10-01之后，）采用新法标
        String column_code08 = xxxService.getLbbhByTableAndColumn("DSR_JB", "DSRSSDW", -1);
        String column_code2016 = xxxService.getLbbh2016ByTableAndColumn("DSR_JB", "DSRSSDW", -1);
        String column_code = "";
        boolean is2016 = false;
        if(StringUtil.isNotBlank(column_code2016)){
           if(DateUtil.compareDate(larq,new Date(116,0,1))>=0){
                column_code = column_code2016;
                is2016 = true;
            }
            else {
                column_code = column_code08;
            }
        }
        else{
            column_code = column_code08;
        }
        String temp_spcx = spcx;

        if (ajxz.equals("A") && spcx.equals("2")) {
            temp_ajxz = "A";
            temp_spcx = "2";
        }
        String toFind = column_code;
        String lbbh = "";
        if(is2016){
            lbbh = matchLbbh(toFind,temp_ajxz + temp_spcx + spcxdz);
            if(StringUtil.isBlank(lbbh)){
                lbbh = matchLbbh(toFind,temp_ajxz + temp_spcx  + "0");
            }
            //match lbbh if ajxz spcx and spcxdz compat, if not, match ajxz, spcx and ajxz only later.
            if(StringUtil.isBlank(lbbh)){
                lbbh = matchLbbh(toFind,temp_ajxz + "00");
            }
        }
        else{
            lbbh = matchLbbh(toFind,temp_ajxz + temp_spcx);
            if(StringUtil.isBlank(lbbh)){
                lbbh = matchLbbh(toFind,temp_ajxz + "*");
            }
        }
        if ("6".equals(ajxz) && "3".equals(spcx) && "2".equals(spcxdz)) {
            lbbh = "FBS0096-97";
        }
        if(ajxz.equals("7")&&spcx.equals("B")){
            //行政赔偿案件按行政案件查询
           lbbh = "FBS0123-97";

        }
        return  getDmByLbbhAndDmbh(lbbh, dmbh);
    }
    private String matchLbbh(String sjxx,String header){
        String[] lbbhList = sjxx.split(",");
        for(String lbbh:lbbhList){
            if(lbbh.startsWith(header)){
                return lbbh.substring(header.length());
            }
        }
        return "";
    }
    @Override
    public DmbModel getModuleByLbbhAndDmbh2(String lbbh, String dmbh) {
        DmbEntity dmbEntity = dmbMapper.getDmByLbbhAndDmbh(lbbh, dmbh);
        if (dmbEntity == null) {
            return null;
        }
        return new DmbModel(dmbEntity);
    }

}
