package com.nju.sdpt.constant;

import com.nju.sdpt.util.StringUtil;

import java.util.Objects;

/**
 * 常量类
 */
public class SdptConstants {
    /**
     * 统计自定义类型
     * 应用于 数据统计
     */
    public static class REPORT_CUS_TYPE{
        // 按人
        public static final String PEO = "PEO";
        // 按次
        public static final String COUNT = "COUNT";
    }


    /**
     * 历史数据来源
     * 应用于 当事人电话信息和地址信息
     */
    public static class HIS_DATA_LY{
        // 历史工单
        public static final String HIS_YYSD = "HIS_YYSD";
        // 历史案件
        public static final String HIS_CASE = "HIS_CASE";
    }

    /**
     * 修复状态
     */
    public static class REPAIR_STATUS{
        // 待修复
        public static final String RPBS001 = "RPBS001";
        // 修复中
        public static final String RPBS002 = "RPBS002";
        // 修复成功
        public static final String RPBS003 = "RPBS003";
        // 修复失败
        public static final String RPBS004 = "RPBS004";
    }
    /**
     * 当事人数据录入类型
     */
    public static class INPUT_DATA_TYPE{
        // 录入身份证
        public static final String INPUT_ID_CARD = "INPUT_ID_CARD";
        // 录入联系电话
        public static final String INPUT_PHONE = "INPUT_PHONE";
        // 录入地址
        public static final String INPUT_ADDRESS = "INPUT_ADDRESS";
    }

    /**
     * 明文短信发送类型
     */
    public static class MWDX_SEND_TYPE{
        /**
         * 法官催单
         * 短信接收对象：送达专员
         */
        public static final Integer FGCD = 1;
        /**
         * 送达人员反馈
         * 短信接收对象: 法官
         */
        public static final Integer SDRYFK = 2;
        /**
         * 法官短信通知
         */
        public static final Integer FGDXTZ = 3;
        /**
         * 送达反馈表-确认
         * 短信接收对象: 送达专员
         */
        public static final Integer SDFK_QR = 4;
        /**
         * 送达反馈表-退回
         * 短信接收对象: 送达专员
         */
        public static final Integer SDFK_TH = 5;
        /**
         * 预约送达成功通知分案员
         * 短信接收对象: 送达专员
         */
        public static final Integer YYSDTZ= 6;
        /**
         * 分配工单任务后通知送达员
         * 短信接收对象: 送达专员
         */
        public static final Integer TZSDY= 7;
    }

    /**
     * 系统版 - 明文模板短信
     */
    public static class SYSTEM_MWDX_DXMB{

        //送达反馈 - 法官 - 确认送达结果
        public static final String SDFK_QRMB = "【天津法院】您好！案号为：%s（送达编号：%s）的案件，法官已确认送达结果，请尽快联系法官，完成材料移交。";

        //送达反馈 - 法官 - 退回
        public static final String SDFK_THMB = "【天津法院】您好！案号为：%s（送达编号：%s）的案件，法官已确认退回，请尽快联系法官，确认送达方案。";
    }

    /**
     * 外呼线路类型对应关系
     */
    public enum SEAT_WEB_CALL_TYPE{
        CUCC_SEAT("CUCC","CUCC"),
        ENTRY_SEAT("ENTRY","ENTRY"),
        WCCMCC_SEAT("WCCMCC","ENTRY"),
        DEFAULT("ERROR","ERROR")
        ;

        //号码渠道类型
        private String operatorType;

        //线路类型
        private String seatType;

        public static SEAT_WEB_CALL_TYPE getByOperatorType(String operatorType){
            for (SEAT_WEB_CALL_TYPE seatWebCallType:SEAT_WEB_CALL_TYPE.values()){
                if(Objects.equals(seatWebCallType.getOperatorType(),operatorType)){
                    return seatWebCallType;
                }
            }
            return SEAT_WEB_CALL_TYPE.DEFAULT;
        }

        SEAT_WEB_CALL_TYPE(String operatorType, String seatType) {
            this.operatorType = operatorType;
            this.seatType = seatType;
        }

        public String getOperatorType() {
            return operatorType;
        }

        public void setOperatorType(String operatorType) {
            this.operatorType = operatorType;
        }

        public String getSeatType() {
            return seatType;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }
    }

    /**
     * 日志类型
     */
    public enum LOG_TYPE{
        YYSD(1,"YYSD","创建预约送达"),
        GDFP(2,"GDFP","工单分配"),
        SDWC(3,"SDWC","送达完成"),
        CD(4,"CD","催单"),
        FK(5,"FK","反馈"),
        QSQRS(6,"QSQRS","修改当事人是否签署送达地址确认书"),
        TYDZSD(7,"TYDZSD","修改当事人是否同意电子送达"),
        CH(8,"CH","撤回"),
        RWLZ(9,"RWLZ","任务流转"),
        FGDHSD(10,"DHSD","法官电话送达"),
        FGDZSD(11,"DZSD","法官电子送达"),
        FGEMSSD(12,"EMSSD","法官EMS送达"),
        FGGGSD(13,"GGSD","法官公告送达"),
        FGZJSD(14,"ZJSD","法官直接送达"),
        FGLYLQ(15,"LYLQ","法官来院领取"),
        FGWTSD(16,"WTSD","法官委托送达"),
        FGZHJSD(17,"ZHJSD","法官转交送达"),
        FGDXSD(18,"DXSD","法官短信送达"),
        GDDHSD(20,"DHSD","工单电话送达"),
        GDDZSD(21,"DZSD","工单电子送达"),
        GDEMSSD(22,"EMSSD","工单EMS送达"),
        GDGGSD(23,"GGSD","工单公告送达"),
        GDZJSD(24,"ZJSD","工单直接送达"),
        GDLYLQ(25,"LYLQ","工单来院领取"),
        GDWTSD(26,"WTSD","工单委托送达"),
        GDZHJSD(27,"ZHJSD","工单转交送达"),
        GDDXSD(28,"DXSD","工单短信送达"),
        SDFKQR(29,"SDFK_QR","送达反馈确认"),
        SDFKTH(30,"SDFK_TH","送达反馈退回"),
        LRLXDZ(31,"INPUT_ADDRESS","录入联系地址"),
        LRSFZ(32,"INPUT_IDCARD","录入身份证"),
        LRLXDH(33,"INPUT_PHONE","录入联系电话"),
        WTDHSD(40,"WTDHSD","委托电话送达"),
        WTDXSD(41,"WTDXSD","委托短信送达"),
        WTEMSSD(42,"WTEMS","委托邮寄送达"),
        WTGGSD(43,"WTGGSD","委托公告送达"),
        WTZJSD(44,"WTZJSD","委托直接送达"),
        WTLYLQ(45,"WTLYLQ","委托来院领取"),
        QRFK(50,"QRFK","管理员确认反馈"),
        DEFAULT(-1,"WZSD","未知操作");


        int typeNum;
        String logjc;
        String content;

        LOG_TYPE(int typeNum, String logjc, String content) {
            this.typeNum = typeNum;
            this.logjc = logjc;
            this.content = content;
        }

        public int getTypeNum() {
            return typeNum;
        }

        public void setTypeNum(int typeNum) {
            this.typeNum = typeNum;
        }

        public String getLogjc() {
            return logjc;
        }

        public void setLogjc(String logjc) {
            this.logjc = logjc;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static LOG_TYPE getLogTypeByTypeNum(int typeNum){
            for (LOG_TYPE logType:LOG_TYPE.values()){
                if(logType.getTypeNum()==typeNum){
                    return logType;
                }
            }
            return LOG_TYPE.DEFAULT;
        }
        public static  LOG_TYPE getLogTypeByContent(String content){
            for (LOG_TYPE logType:LOG_TYPE.values()){
                if(StringUtil.equals(logType.getContent(),content)){
                    return logType;
                }
            }
            return LOG_TYPE.DEFAULT;
        }

        public static  LOG_TYPE getLogTypeByJc(String jc){
            for (LOG_TYPE logType:LOG_TYPE.values()){
                if(StringUtil.equals(logType.getLogjc(),jc)){
                    return logType;
                }
            }
            return LOG_TYPE.DEFAULT;
        }
    }
}
