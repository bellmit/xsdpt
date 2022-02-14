package com.nju.sdpt.model.info;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class YysdInfoModel {
    private String yysdbh;
    //案件序号
    private String ajxh;
    private String fybh;
    private String ah;
    private String ajmc;
    //预约人
    private String yyr;
    //预约时间
    private String yysj;
    // 送达状态
    private String sdzt;
    //送达员名称
    private String sdyxm;
    //预约人电话
    private String yyrdh;
    //送达结果
    private String sdjg;
    //当事人名称
    private List<String> dsrmc;
    //开庭时间
    private String ktsj;
    //委托任务数
    private String wtrws;
    private String laay;
    /**
     * 工单所属法院名称
     */
    private String fymc;
    //是否生成文件
    private String sfscwj;
    //法官预约时备注
    private String yysdbz;
    //催单时间
    private Date cdsj;

    private String sdsj;

    private String bmmc;
    private String bz;



    public YysdInfoModel() {
    }

    public YysdInfoModel(String yysdbh, String ajxh, String fybh, String ah, String ajmc, String yyr, String yysj, String sdzt, String sdyxm, String yyrdh, String sdjg, List<String> dsrmc, String ktsj, String wtrws,String yysdbz) {
        this.yysdbh = yysdbh;
        this.ajxh = ajxh;
        this.fybh = fybh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.yyr = yyr;
        this.yysj = yysj;
        this.sdzt = sdzt;
        this.sdyxm = sdyxm;
        this.yyrdh = yyrdh;
        this.sdjg = sdjg;
        this.dsrmc = dsrmc;
        this.ktsj = ktsj;
        this.wtrws = wtrws;
        this.yysdbz = yysdbz;
    }

    public YysdInfoModel(String yysdbh, String ajxh, String fybh, String ah, String ajmc, String yyr, String yysj, String sdzt, String sdyxm, String yyrdh, String sdjg, List<String> dsrmc, String ktsj, String wtrws, String laay, String fymc, String sfscwj,String yysdbz) {
        this.yysdbh = yysdbh;
        this.ajxh = ajxh;
        this.fybh = fybh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.yyr = yyr;
        this.yysj = yysj;
        this.sdzt = sdzt;
        this.sdyxm = sdyxm;
        this.yyrdh = yyrdh;
        this.sdjg = sdjg;
        this.dsrmc = dsrmc;
        this.ktsj = ktsj;
        this.wtrws = wtrws;
        this.laay = laay;
        this.fymc = fymc;
        this.sfscwj = sfscwj;
        this.yysdbz = yysdbz;
    }

    public YysdInfoModel(String yysdbh, String ajxh, String fybh, String ah, String ajmc, String yyr, String yysj, String sdzt, String sdyxm, String yyrdh, String sdjg, List<String> dsrmc, String ktsj, String wtrws, String laay, String fymc, String sfscwj, String yysdbz, Date cdsj, String sdsj, String bmmc, String bz) {
        this.yysdbh = yysdbh;
        this.ajxh = ajxh;
        this.fybh = fybh;
        this.ah = ah;
        this.ajmc = ajmc;
        this.yyr = yyr;
        this.yysj = yysj;
        this.sdzt = sdzt;
        this.sdyxm = sdyxm;
        this.yyrdh = yyrdh;
        this.sdjg = sdjg;
        this.dsrmc = dsrmc;
        this.ktsj = ktsj;
        this.wtrws = wtrws;
        this.laay = laay;
        this.fymc = fymc;
        this.sfscwj = sfscwj;
        this.yysdbz = yysdbz;
        this.cdsj = cdsj;
        this.sdsj = sdsj;
        this.bmmc = bmmc;
        this.bz = bz;
    }
}
