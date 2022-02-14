package com.nju.sdpt.model;

import com.nju.sdpt.entity.PubSsdrHmEntity;
import com.nju.sdpt.entity.PubYysdSsdrEntity;
import com.nju.sdpt.entity.PubYysdSsdrdzEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SsdrGddcModel {
    PubYysdSsdrEntity ssdr;
    List<String> hmList;
    List<String> dzList;
    Integer yywssl;//预约文书数量
    List<String> wslb;//文书类型集合
    Date yjrq;//邮寄日期
    String yjjg;
    Date lylqsj;//来院领取时间
    String sftydzsd;//是否同意电子送达
}
