package com.nju.sdpt.service.Impl;

import com.nju.sdpt.mapper.LaAyMapper;
import com.nju.sdpt.service.LaayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LaayServiceImpl implements LaayService {
    @Autowired
    LaAyMapper laAyMapper;
    @Override
    public String getLaayByAjxh(Integer ajxh,String fybh) {
        List<String> laays=laAyMapper.getLaayByAjxh(ajxh);
        StringBuilder pubLaay = new StringBuilder();
        for (int i=0;i<laays.size();i++){
            pubLaay.append(laays.get(i));
            if(i<laays.size()-1){
                pubLaay.append(";");
            }
        }
        return pubLaay.toString();
    }

    @Override
    public String getKtsjByAjxh(Integer ajxh,String fybh) {
        Date date;
        Date time=new Date();
        try {
            date = laAyMapper.getKtsjByAjxh(ajxh,time);
        } catch (Exception e) {
            return "";
        }
        if(date==null){
            return "";
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }
}
