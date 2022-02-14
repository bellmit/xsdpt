package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.model.*;
import com.nju.sdpt.viewobject.DxsdInfoVO;
import com.nju.sdpt.viewobject.SdDataVO;
import com.nju.sdpt.viewobject.WebCallVo;

import java.util.List;

public interface SdxxService {
    List<DzsdModel> getDzsdInfo(Integer ajxh, String fybh);
    List<EmssdModel> getEmssdInfo(Integer ajxh, String fybh);
    List<GgsdModel> getGgsdInfo(Integer ajxh, String fybh);

    SdDataVO getAllSdData(String fybh, String yhm);

    List<PubWebCallInfoModel> getDhsdInfo(Integer ajxh, String fybh);

    List<LylqModel> getLylqInfo(Integer ajxh, String fybh);

    List<DxsdInfoVO> getDxsdInfo(Integer ajxh, String fybh);

    PubKdtdEntity getKdtdByKdid(Integer kdid);

    boolean deleteKdhzByKdid(Integer kdid);

    List<EmssdModel> getEmssdGdInfo(Integer yysdbh, String fybh);
    List<ZjsdModel> getZjsdGdInfo(Integer yysdbh);

    List<EmssdModel> getEmssdGdInfo(String ssdrmc, String sfzhm, String fybh);

    List<ZjsdModel> getZjsdInfo(Integer ajxh, String fybh);
}
