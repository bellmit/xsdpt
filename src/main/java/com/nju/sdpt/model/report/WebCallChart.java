package com.nju.sdpt.model.report;

import com.nju.sdpt.util.NumberUtil;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 外呼相关数据
 */
@Data
public class WebCallChart extends BaseChart {

    /**
     * 外呼数量
     */
    public Integer webCallNum;

    /**
     * 外呼接听数量
     */
    public Integer webCallJtNum;

    /**
     * 接听率
     */
    public String webCallJtRate;

    /**
     * 承认本人数
     */
    public Integer crbrNum;

    /**
     * 承认本人率
     */
    public String crbrRate;

    /**
     * 转邮寄数
     */
    public Integer jtZEmsNum;

    /**
     * 转来院数
     */
    public Integer jtZLylqNum;

    /**
     * 转电子数 （即短信通知）
     */
    public Integer jtZDxtzNum;

    /**
     * 电话送达成功数量(新增)
     */
    public Integer webCallSucNum;

    /**
     * 电话送达失败数量(新增)
     */
    public Integer webCallFailNum;
    /**
     *电话送达成功率(新增)
     */
    public String webCallSucRate;

    public void loadSucRate() {
        webCallJtRate = loadSucRateBase(true,webCallNum,webCallJtNum==null?null:(webCallNum==null?null:webCallNum-webCallJtNum));
        crbrRate = loadSucRateBase(true,crbrNum,webCallJtNum==null?null:(crbrNum==null?null:webCallJtNum-crbrNum));
        webCallSucRate = loadSucRateBase(true,webCallSucNum,webCallFailNum);
    }

}
