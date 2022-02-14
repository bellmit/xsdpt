package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubYysdJbEntity;
import lombok.Data;

import java.util.List;

//工单导出Model
@Data
public class GddcModel {
    PubYysdJbEntity jb;
    List<SsdrGddcModel> ssdrList;
}
