package com.nju.sdpt.service;

/**
 * 外呼线路业务类
 */
public interface WebcallSeatService {

    /**
     * 根据号码类型获取空闲线路id
     * @param operatorType
     * @return
     */
    String getSeatByType(String operatorType);

    /**
     * 修改线路状态为空闲
     * @param seatId
     */
    void updateCallSeat(String seatId);

    /**
     * 处理 x 分钟以前的占用线路
     * @param minutes
     */
    void updateCallSeatMinutes(int minutes);
}
