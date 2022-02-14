package com.nju.sdpt.service;

import com.nju.sdpt.model.*;
import com.nju.sdpt.service.Impl.YysdInfoServiceImpl;

import java.util.List;

public interface YysdInfoService {
    List<YysdModel> getYysdListByGdryxm(String yhmc, Integer sdjg, String start, String end);
    List<ZsalModel> zsalSjtj();

    List<ZwhlModel> zwhlSjtj();
    List<ZdxlModel> zdxlSjtj();
    List<ZxflModel> zxflSjtj();

}
