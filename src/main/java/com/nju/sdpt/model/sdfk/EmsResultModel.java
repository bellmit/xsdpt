package com.nju.sdpt.model.sdfk;

import com.nju.sdpt.util.DateUtil;
import com.nju.sdpt.util.StringUtil;
import com.nju.sdpt.viewobject.KdcxVO;

import java.util.Date;
import java.util.Objects;

/**
 * 送达反馈表 - EMS
 */
public class EmsResultModel extends BaseModel {

    /**
     * 接收人地址
     */
    private String jsrdz;

    /**
     * 快递单号
     */
    private String kddh;

    /**
     * 创建时间
     */
    private Date yjsj;

    /**
     * 送达结果
     */
    private String sdjg;

    /**
     * 物流轨迹
     */
    private KdcxVO[] kdcxVO;

    /**
     * 送达反馈字符串拼接
     *
     * @return
     */
    public String getSdfkStr() {
        String template = "邮寄地址：%s\n邮寄单号：%s\n"
                + "邮寄时间：%s\n"
                + "送达结果：%s\n"
                + "物流信息：\n%s"; //物流轨迹
        String kdcx = "";
        if (null != this.kdcxVO && kdcxVO.length > 0) {
            for (KdcxVO vo : kdcxVO) {
                kdcx += vo.getAcceptTime() + " \t " + vo.getAcceptAddress() + " \t " + vo.getRemark() + "\n";
            }
        }
        if (StringUtil.isBlank(sdjg)){
            sdjg="暂无";
        }
        //格式化外呼时长
        String result = String.format(template, jsrdz, kddh, DateUtil.format(yjsj, "yyyy.MM.dd"), sdjg,kdcx);
        result += "------------------------------------------------------------------\n";
        return result;
    }

    public String getJsrdz() {
        return jsrdz;
    }

    public void setJsrdz(String jsrdz) {
        this.jsrdz = jsrdz;
    }

    public String getKddh() {
        return kddh;
    }

    public void setKddh(String kddh) {
        this.kddh = kddh;
    }

    public Date getYjsj() {
        return yjsj;
    }

    public void setYjsj(Date yjsj) {
        this.yjsj = yjsj;
    }

    public KdcxVO[] getKdcxVO() {
        return kdcxVO;
    }

    public void setKdcxVO(KdcxVO[] kdcxVO) {
        this.kdcxVO = kdcxVO;
    }
    public String getSdjg() {
        return sdjg;
    }

    public void setSdjg(String sdjg) {
        this.sdjg = sdjg;
    }
}
