package com.nju.sdpt.model.report;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 案由 案件量关系
 */
@Data
public class ChartCassLaayModel {

    //收案
    List<Map<String,Object>> laayCaseData;

    //送达成功案
    List<Map<String,Object>> laayCaseSucData;

}
