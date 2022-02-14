package com.nju.sdpt.viewobject;

import lombok.Data;

import java.util.List;

/**
 * 工单流转VO
 */
@Data
public class RwlzVO {
    List<Integer> yysdbhList;//预约送达编号数组
    Integer creater;
    Integer target;//目标
}
