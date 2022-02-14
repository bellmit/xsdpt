package com.nju.sdpt.service;

import com.nju.sdpt.model.GdcxModel;
import com.nju.sdpt.model.YysdModel;

import java.util.List;

public interface GdcxService {
    List<YysdModel> getGdcxResult(GdcxModel gdcxModel);

    List<YysdModel> getTsyysdResult(GdcxModel gdcxModel);

    Boolean editSdzt(String sdzt, Integer yysdbh);
    Boolean editCallData(Integer webcallid, String callState, String electronsend, String remarks, String phone, String address);
}
