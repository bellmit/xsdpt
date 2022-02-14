package com.nju.sdpt.service.Impl;

import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.entity.PubWebcallSeatEntity;
import com.nju.sdpt.mapper.PubWebcallSeatEntityMapper;
import com.nju.sdpt.service.WebcallSeatService;
import com.nju.sdpt.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 外呼线路业务实现类
 */
@Service
public class WebcallSeatServiceImpl implements WebcallSeatService {

    @Resource
    private PubWebcallSeatEntityMapper pubWebcallSeatEntityMapper;

    @Override
    public String getSeatByType(String operatorType) {
        SdptConstants.SEAT_WEB_CALL_TYPE callType = SdptConstants.SEAT_WEB_CALL_TYPE.getByOperatorType(operatorType);
        PubWebcallSeatEntity seatEntity = pubWebcallSeatEntityMapper.selectSeat(callType.getSeatType());
        if(null != seatEntity && null != seatEntity.getId()){
            //将线路状态更改为 使用中
            seatEntity.setStatus(1);
            seatEntity.setUpdatetime(new Date());
            //保存
            pubWebcallSeatEntityMapper.updateByPrimaryKey(seatEntity);

            //返回线路id
            return seatEntity.getSeatid();
        }
        return null;
    }

    @Override
    public void updateCallSeat(String seatId) {
        if(StringUtil.isNotBlank(seatId)){
            pubWebcallSeatEntityMapper.updateCallSeatBySeatId(seatId);
        }
    }

    @Override
    public void updateCallSeatMinutes(int minutes) {
        pubWebcallSeatEntityMapper.updateCallSeatMinutes(minutes);
    }
}
