package com.nju.sdpt.service;


import com.nju.sdpt.entity.PubDxtzInfoEntity;
import com.nju.sdpt.entity.PubMwdxSendEntity;
import com.nju.sdpt.entity.PubZybInfoEntity;
import com.nju.sdpt.model.DxtzListModel;
import com.nju.sdpt.model.PubSsdrHmEntityModel;
import com.nju.sdpt.viewobject.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DxtzService {

    Boolean sendShortMsg(SendShortMsgVo sendShortMsgVo, String yhm);

    List<DxtzListModel> loadList(DxtzLoadListVo loadListVo);
    List<DxtzListModel> loadList(DxtzLoadListVo loadListVo,String start,String end);

    List<PubMwdxSendEntity> fgLoadList(String yhm, String fybh,int status);

    void editSdjg(DxtzEditSdjgVo dxtzEditSdjgVo);

    List<PubSsdrHmEntityModel> querySdpPhone(QuerySdpPhoneVo querySdpPhoneVo);

    Boolean sendPlaintext(MwdxSendVo mwdxSendVo, String yhm);

    PubMwdxSendEntity pubMwdxSendEntity(String cusid);

    String uploadFile(MultipartFile file,PubZybInfoEntity zybInfoEntity);

    Integer savePubDxtzInfoEntity(PubDxtzInfoEntity pubDxtzInfoEntity);

    Integer savePubZybInfoEntity(PubZybInfoEntity pubZybInfoEntity);

    Integer savePubMwdxSendEntity(PubMwdxSendEntity pubMwdxSendEntity);

    void HandlerLinkStatus();

    void HandlerMsgStatus();
}
