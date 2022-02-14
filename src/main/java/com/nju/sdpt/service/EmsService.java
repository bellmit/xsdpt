package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.model.CxsjResult;
import com.nju.sdpt.model.EmssdModel;
import com.nju.sdpt.viewobject.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface EmsService {
    Boolean uploadEmsmd(Integer yysdbh, MultipartFile file,String yhbh,Integer kdid,String fybh) throws IOException;

    String downloadEmsmd(Integer kdid,String fybh,Integer ajxh);
    CxsjResult findByMlmc(String ah, String mlmc, String fybh);

    Boolean deleteEmsmd(Integer kdid, String fybh, String yhbh,Integer ajxh);

    List<EmssdModel> getEmsModelByYysdbhList(String fybh,List<Integer> yysdbhList);

    boolean updateEmsSdjg(Integer kdid, String fybh, String sdjg, Date date);

    boolean deleteEmsInfo(Integer yysdbh, Integer kdid);

    boolean deleteKdtdByKdid(Integer kdid, String fybh);

    boolean existsEmsInfoByKdid(Integer yysdbh, Integer kdid);

    List<PubKdtdEntity> getEmsInfoByKddh(String kddh);

    PubKdtdEntity getGlEmsInfoByKddh(String kddh);
}
