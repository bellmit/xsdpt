package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubWebCallInfoEntity;
import com.nju.sdpt.entity.PubYysdJbEntity;
import com.nju.sdpt.model.*;
import lombok.Data;

import java.util.List;

@Data
public class WbrywdsdVO {
    private String ah;
    private PubYysdJbEntity pubYysdJbEntity;
    private List<EmssdModel> emssdModel;
    private List<PubWebCallInfoModel> pubWebCallInfoModels;
    private List<GgsdModel> ggsdModel;
    private List<DzsdModel> dzsdModel;
    private List<DxtzListModel> dxtzListModels;
    private List<LylqModel> lylqModels;
    private List<ZjsdModel> zjsdModels;
    private String laay;

    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }

    public List<ZjsdModel> getZjsdModels() {
        return zjsdModels;
    }

    public void setZjsdModels(List<ZjsdModel> zjsdModels) {
        this.zjsdModels = zjsdModels;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public PubYysdJbEntity getPubYysdJbEntity() {
        return pubYysdJbEntity;
    }

    public void setPubYysdJbEntity(PubYysdJbEntity pubYysdJbEntity) {
        this.pubYysdJbEntity = pubYysdJbEntity;
    }

    public List<EmssdModel> getEmssdModel() {
        return emssdModel;
    }

    public void setEmssdModel(List<EmssdModel> emssdModel) {
        this.emssdModel = emssdModel;
    }

    public List<PubWebCallInfoModel> getPubWebCallInfoModels() {
        return pubWebCallInfoModels;
    }

    public void setPubWebCallInfoModels(List<PubWebCallInfoModel> pubWebCallInfoModels) {
        this.pubWebCallInfoModels = pubWebCallInfoModels;
    }

    public List<GgsdModel> getGgsdModel() {
        return ggsdModel;
    }

    public void setGgsdModel(List<GgsdModel> ggsdModel) {
        this.ggsdModel = ggsdModel;
    }

    public List<DzsdModel> getDzsdModel() {
        return dzsdModel;
    }

    public void setDzsdModel(List<DzsdModel> dzsdModel) {
        this.dzsdModel = dzsdModel;
    }

    public List<DxtzListModel> getDxtzListModels() {
        return dxtzListModels;
    }

    public void setDxtzListModels(List<DxtzListModel> dxtzListModels) {
        this.dxtzListModels = dxtzListModels;
    }

    public List<LylqModel> getLylqModels() {
        return lylqModels;
    }

    public void setLylqModels(List<LylqModel> lylqModels) {
        this.lylqModels = lylqModels;
    }
}
