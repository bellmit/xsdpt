package com.nju.sdpt;

import com.alibaba.fastjson.JSON;
import com.google.common.io.BaseEncoding;
import com.mysql.jdbc.util.Base64Decoder;
import com.nju.sdpt.controller.ZgysdController;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.*;
import com.nju.sdpt.model.*;
import com.nju.sdpt.service.*;
import com.nju.sdpt.service.Impl.ZjsdServiceImpl;
import com.nju.sdpt.util.*;
import com.nju.sdpt.viewobject.ReportQueryVo;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.nju.sdpt.schedule.ScheduledStastics.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SdptApplicationTests {

    @Resource
    SdfkService sdfkService;

    @Resource
    DxtzService dxtzService;

    @Resource
    ZgysdController zgysdController;

    @Resource
    PubLcspFjbMapper pubLcspFjbMapper;

    @Resource
    DmbMapper dmbMapper;

    @Resource
    ScheduleShortMsgService scheduleShortMsgService;

    @Resource
    PubYysdSsdrEntityMapper pubYysdSsdrEntityMapper;

    @Resource
    ScheduledStastics scheduledStastics;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    YysdService yysdService;
    @Autowired
    RpoEmsInfoEntityMapper rpoEmsInfoEntityMapper;
    @Autowired
    PubDxtzInfoEntityMapper pubDxtzInfoEntityMapper;

    @Autowired
    PubYysdCsxxxMapper pubYysdCsxxxMapper;

    @Autowired
    RyzxModelMapper ryzxModelMapper;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;

    @Autowired
    PubWebCallInfoEntityMapper pubWebCallInfoEntityMapper;

    @Autowired
    PubYysdRyxxEntityMapper pubYysdRyxxEntityMapper;

    @Autowired
    PubYysdJbEntityMapper pubYysdJbEntityMapper;

    @Autowired
    ScheduleReapirService scheduleReapirService;

    @Autowired
    PubLylqSdhzEntityMapper pubLylqSdhzEntityMapper;

    @Autowired
    ScheduleWebCallService scheduleWebCallService;


    @Autowired
    ZjsdServiceImpl zjsdService;
    @Test
    public void updateAddress() {
        List<PubLylqSdhzEntity> pubLylqSdhzEntities = pubLylqSdhzEntityMapper.findAll();
        for (PubLylqSdhzEntity pubLylqSdhzEntity : pubLylqSdhzEntities) {
            if (pubLylqSdhzEntity.getSdhzfolder().contains("\\spgl\\sdpt\\uploadFile\\uploadFile\\")) {
                pubLylqSdhzEntity.setSdhzfolder(pubLylqSdhzEntity.getSdhzfolder().replace("\\spgl\\sdpt\\uploadFile\\uploadFile\\", "\\spgl\\sdpt\\uploadFile\\sdpt\\unimas_back\\uploadFile\\"));
                pubLylqSdhzEntityMapper.updateByPrimaryKey(pubLylqSdhzEntity);
            }
        }
    }

    @Test
    public void testSdpt(){
        //定义读取目录
        String readPath = "E:\\N_ftp\\enter\\webCall\\webCallResponse";
        XMLObjUtil.isChartPathExit(readPath);

        File[] filesList = XMLObjUtil.getCurFilesList(readPath,"[prod]");
        if (null != filesList && filesList.length > 0) {
            for (File file : filesList) {
                String path = file.getPath();
                try {
                    DhsdHjmxs dhsdHjmxs=(DhsdHjmxs)XMLObjUtil.convertXmlFileToObject(DhsdHjmxs.class, path);
                    System.out.println(dhsdHjmxs);
                }catch (Exception e){

                }
            }
        }
    }

    @Test
    public void testShortMsg(){
        dxtzService.HandlerMsgStatus();
    }

    @Test
    public void jxdhqk(){
        zjsdService.handleZjsdResponseXML();
    }

    @Test
    public void CallResponseTest(){
        scheduleWebCallService.handPhoneCallResponseXML();
    }

    @Test
    public void testGetSdfkData() {
//        List<PubYysdSsdrEntity> ssdrs = pubYysdSsdrEntityMapper.findAllSsdr();
//        HashMap<String, Integer> map = new HashMap<>();
//        for(PubYysdSsdrEntity ssdr : ssdrs) {
//            if(null == ssdr.getWsnum()) {
//                continue;
//            }
//            if(null != ssdr.getYysdbh() && null != ssdr.getSsdrbh()) {
//                map.put(ssdr.getYysdbh() + "_" + ssdr.getSsdrbh(), ssdr.getWsnum());
//            }
//        }
//        int count;
//        List<RpoEmsInfoEntity> emss = pubYysdSsdrEntityMapper.findAllEms();
//        for(RpoEmsInfoEntity ems :emss) {
//            DynamicDataSource.routerByFybh(ems.get);
//        }
//        List<PubDxtzInfoEntity> dxs = pubYysdSsdrEntityMapper.findAllDx();
//        for(PubDxtzInfoEntity dx :dxs) {
//            if(null != dx.getYysdbh() && null != dx.getDsrbgh()) {
//                if(map.containsKey(dx.getYysdbh() + "_" + dx.getDsrbgh())) {
//                    dx.setWsnum(map.get(dx.getYysdbh() + "_" + dx.getDsrbgh()));
//                    pubDxtzInfoEntityMapper.updateByPrimaryKey(dx);
//                    System.out.println(dx.getYysdbh()+"+"+dx.getDsrbgh()+"+"+dx.getWsnum());
//                }
//            }
////        }
//        for(PubYysdSsdrEntity ssdr : ssdrs) {
//            count = 0;
//            PubYysdWsEntity[] wss = yysdService.selectByYysdbh(ssdr.getYysdbh());
//            for(PubYysdWsEntity ws : wss) {
//                if(null == ws.getSsdrbh() || null == ws.getWsly()) {
//                    continue;
//                }
//                if(ws.getSsdrbh().equals(ssdr.getSsdrbh()) && ws.getWsly() != 4) {
//                    ++ count;
//                }
//            }
//            if(count == 0) {
//                continue;
//            }
//            ssdr.setWsnum(count);
//            pubYysdSsdrEntityMapper.updateByPrimaryKey(ssdr);
//            System.out.println("工单号：" + ssdr.getYysdbh() +"," + "当事人：" + ssdr.getSsdrmc() + "," + "文书数量:" + count );
//        }

        scheduledStastics.scheduledUpdateSfcsx();

    }


    @Test
    public void ajxxConvertTest() throws Exception {
//        String ajxx = "";
//        zgysdController.ajxxConvert(ajxx);
//        scheduleShortMsgService.handleShortMsgResponseXML();
//        PubYysdSsdrEntity pubYysdSsdrEntity = pubYysdSsdrEntityMapper.selectByPrimaryKey(new PubYysdSsdrEntityKey(2, 283922));

//        zgysdController.ajxxConvert("%7B%22ajidlist%22%3A+%5B%222860%22%5D%7D");

//        scheduledStastics.scheduledUpdateEmsCount();
//        ReportQueryVo queryVo = new ReportQueryVo();
//        queryVo.setStartTime("2020-01-01");
//        queryVo.setEndTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
//        statisticsService.scheduledUpdateEmsCount(queryVo);
        scheduleShortMsgService.getShortUrlStatus();
        scheduleShortMsgService.getPlaintextShortUrlStatus();

    }

    // 统计
    @Test
    public void test4() {
        DynamicDataSource.router(SDPT_FYDM);
        dxtzService.HandlerMsgStatus();
    }

    @Test
    public void test5() throws UnsupportedEncodingException {
        System.out.println(new String(Base64Util.getFromBASE64("W3sid2ppZCI6IkY0MjQ0RUIxLTc2MjItNDQ0Mi1BMjdGLTBEQjg0ODU5OEE0NSIsIndqc3NtbCI6IuawkeS6i+ijgeWumuebuOWFs+adkOaWmSIsIndqbWMiOiJpbWFnZTk4ODkuanBnIiwiaW5kZXgiOjF9LHsid2ppZCI6IjEyODlEOThCLTVDOTUtNDE4RC1CQUI3LTg2QTlFMzI5OTI2RiIsIndqc3NtbCI6IuawkeS6i+ijgeWumuebuOWFs+adkOaWmSIsIndqbWMiOiJpbWFnZTk4OTAuanBnIiwiaW5kZXgiOjJ9XQ=="), "UTF-8"));
        return;
//        new EmsServiceImpl().findByMlmc("(2021)津0101民初5903号", "起诉状");

    }

    @Test
    public void scheduledUpdateCsxxx() {
        // 获取需要更新的信息数量
        int num = pubYysdCsxxxMapper.findNum();
        // 批量选择和插入或者更新
        for (int i = 0; i < (num / 5000); i++) {
            List<PubYysdCsxxxEntity> pubYysdCsxxxEntities = pubYysdCsxxxMapper.findInLimit(5000, i * 5000);
            pubYysdCsxxxMapper.insertOrUpdate(pubYysdCsxxxEntities);
            System.out.println(i * 5000);
        }
    }

    @Test
    public void testScheduled() {
        // 返回人员所有的信息
        List<PubYysdRyxxEntity> ryxxs = pubYysdRyxxEntityMapper.getAllSdry();
        List<RyzxModel> ryzxModels = new ArrayList<>();
        for (PubYysdRyxxEntity ryxx : ryxxs) {
            //  每个人按照当天、当周、当月、全部计算绩效
            for (int i = 1; i < 5; i++) {
                RyzxModel ryzxModel = new RyzxModel();
                if (null == ryxx.getFybh() || ryxx.getFybh().length() == 0) {
                    continue;
                }
                // 将用户代码作为这一表中，与ryxx表统一的字段
                ryzxModel.setYhdm(ryxx.getYhdm());
                ryzxModel.setFybh(Integer.parseInt(ryxx.getFybh()));
                ryzxModel.setSdzy(ryxx.getYhmc());
                ryzxModel.setTjsj(DateUtil.format(new Date(), "yyyy-MM-dd"));
                ryzxModel.setScope(i);
                /**
                 * 查询信息
                 * 将要查询的信息填写到相应的实体类中，然后之后输出到方法中进行查询
                 */
                ReportQueryVo queryVo = new ReportQueryVo();
                queryVo.setGdOrAhType("GD");
                queryVo.setFybhSet(Arrays.asList("-1000"));
                queryVo.setSdptRy(true);
                queryVo.setLoginYhm(ryxx.getYhdm());
                if (i == 1) {
                    queryVo.setStartTime(getYestoday());
                    queryVo.setEndTime(getToday());
                } else if (i == 2) {
                    queryVo.setStartTime(getWeekStart());
                    queryVo.setEndTime(getToday());
                } else if (i == 3) {
                    queryVo.setStartTime(getMonthStart());
                    queryVo.setEndTime(getToday());
                } else if (i == 4) {
                    queryVo.setStartTime(getYearStart());
                    queryVo.setEndTime(getToday());
                }
                // 在表中更新工单反馈总量
                ryzxModel.setGdfkzl(pubYysdJbEntityMapper.lj_end_case(queryVo));
                // 在表中更新工单送达成功总量
                ryzxModel.setGdsdcgl(pubYysdJbEntityMapper.lj_suc_case(queryVo));
                // 在表中更新工单送达失败总量，计算方式:送达总量-成功量
                ryzxModel.setGdsdsbl(ryzxModel.getGdfkzl() - ryzxModel.getGdsdcgl());

                List<Map<String, Integer>> mapList = pubYysdJbEntityMapper.lj_ssdr(queryVo);
                if (CollectionUtils.isNotEmpty(mapList)) {
                    Map<String, Integer> integerMap = mapList.get(0);
                    // 在表中更新送达总人数
                    ryzxModel.setSdzrs(Integer.valueOf(String.valueOf(integerMap.get("ljSddr"))));
                    // 在表中更新送达成功的人数
                    ryzxModel.setSdcgrs(Integer.valueOf(String.valueOf(integerMap.get("ljSucSddr"))));
                    // 在表中更新送达失败的人数
                    ryzxModel.setSdsbrs(Integer.valueOf(String.valueOf(integerMap.get("ljEndSddr"))) - Integer.valueOf(String.valueOf(integerMap.get("ljSucSddr"))));
                }

                // 存储有关电话的信息内容
                // 计算通话总时长
                int thzsc = pubWebCallInfoEntityMapper.CalWebCallzthsc(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 :
                        pubWebCallInfoEntityMapper.CalWebCallzthsc(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                // 拨打电话按次统计
                int bddhlac = pubWebCallInfoEntityMapper.CalWebCallac(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 :
                        pubWebCallInfoEntityMapper.CalWebCallac(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                // 拨打电话按人统计
                int bddhlar = pubWebCallInfoEntityMapper.CalWebCallar(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 :
                        pubWebCallInfoEntityMapper.CalWebCallar(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                // 拨打电话有效次数
                int bddhyxcs = pubWebCallInfoEntityMapper.CalWebCallyxac(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 :
                        pubWebCallInfoEntityMapper.CalWebCallyxac(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                // 拨打电话有效人数
                int bddhyxrs = pubWebCallInfoEntityMapper.CalWebCallyxar(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 :
                        pubWebCallInfoEntityMapper.CalWebCallyxar(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                ryzxModel.setBddhlac(bddhlac);
                ryzxModel.setBddhlar(bddhlar);
                ryzxModel.setThzsc(thzsc);

                ryzxModel.setPjthsc((float) thzsc / (float) bddhlac);
                ryzxModel.setDhjtlacRate(bddhlac == 0 ? 0 : (float) bddhyxcs / (float) bddhlac);
                //ryzxModel.setDhjtlacRate((float)bddhyxcs/(float)bddhlac);
                //ryzxModel.setDhjtlarRate((float)bddhyxrs/(float)bddhlar);
                // 获取同意电子送达的人数
                ryzxModel.setTydzsdrs(pubWebCallInfoEntityMapper.Tydzsdrs(ryxx.getYhdm()) == null ? 0 : pubWebCallInfoEntityMapper.Tydzsdrs(ryxx.getYhdm()));
                // 获取短信的相关的信息。获取短信发送量和带链接短信总量
                // List<Map<String,Integer>> dxtzMap = reportMapper.loadDxtzData(queryVo,"ENTRY");
//                DxtzChart dxtzChart = new DxtzChart();
//                if(CollectionUtils.isNotEmpty(dxtzMap)){
//                    dxtzChart.loadNum(dxtzMap);
//                    dxtzChart.loadSucRate(true);
//                }
//                // 存放短信发送量
//                ryzxModel.setDxfsl(dxtzChart.getDxtzNum());
//                // 存放带链接的发送量
//                ryzxModel.setDljdxzl(dxtzChart.getDljDxtzNum());
                // 存放短信发送量
                ryzxModel.setDxfsl(pubYysdCsxxxMapper.getDxl(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : pubYysdCsxxxMapper.getDxl(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));
                // 存放带链接的发送量
                ryzxModel.setDljdxzl(pubYysdCsxxxMapper.getYljDxl(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : pubYysdCsxxxMapper.getYljDxl(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));

                ryzxModel.setYjcs(pubYysdCsxxxMapper.getYjcs(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : pubYysdCsxxxMapper.getYjcs(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));

                // 获取并存放来院领取的人数
                ryzxModel.setLylqcs(pubYysdCsxxxMapper.getLylqCX(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : pubYysdCsxxxMapper.getLylqCX(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));

                ryzxModel.setYjsdcsxaj(pubYysdCsxxxMapper.StatisticsYJCSX(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));
                ryzxModel.setDhsdcsxaj(pubYysdCsxxxMapper.StatisticsDHCSX(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));
                ryzxModel.setSdcsxaj(pubYysdCsxxxMapper.StatisticsCSX(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()));

                int total = ryzxModelMapper.getTotalNum(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : ryzxModelMapper.getTotalNum(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                int sdsc = ryzxModelMapper.getSdsc(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : ryzxModelMapper.getSdsc(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                int xysc = ryzxModelMapper.getXysc(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime()) == null ? 0 : ryzxModelMapper.getXysc(ryxx.getYhdm(), queryVo.getStartTime(), queryVo.getEndTime());
                ryzxModel.setPjsdsc(total == 0 ? 0 : sdsc / total);
                ryzxModel.setJswtrwlzhxysj(total == 0 ? 0 : xysc / total);


                // 如果未更新成功则添加此记录
                ryzxModelMapper.insertOrUpdate(ryzxModel);
                ///System.out.println("Test:111111");
            }
        }
    }

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    @Test
    public void testRepair() {
        DynamicDataSource.router(SDPT_FYDM);
        scheduleReapirService.handleCallRepairQueryResultResponseXML();
    }

    @Test
    public void sendMzsjtj() {
        //加载数据环境
        DynamicDataSource.router(SDPT_FYDM);
        // "E:\\N_ftp\\out\\mzsjtj\\" 生成的文件会在这个地址下面
        scheduleShortMsgService.sendMzsjtj();
    }

    @Test
    public void testMongodb() {
        // http://130.1.67.108:8080/dzjz-mongo/getWsExceptHSZ.do
        Map<String, String> params = new HashMap<String, String>();
        params.put("fydm", "120222 217");
        params.put("ajxh", "217115");
        params.put("wjmc", "ems面单_211997.jpg");
        List<DzjzWdWjDO> listByPost = MongoUtil.getListByPost("http://130.5.0.12:8084/dzjz-mongo/getWjListByWjmc.do", params, DzjzWdWjDO.class);
        System.out.println(listByPost.get(0).getWjlj());
    }

    @Test
    public void testRwlzsc() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Integer> yysdbhs = ryzxModelMapper.getYysdbhBylz();
        for (int yysdbh : yysdbhs) {
            List<lzEntity> lzEntities = ryzxModelMapper.getlzEntity(yysdbh);
            for (int i = 1; i < lzEntities.size(); i++) {
                try {
                    long late = sdf.parse(sdf.format(lzEntities.get(i).getCreatetime())).getTime();
                    long early = sdf.parse(sdf.format(lzEntities.get(i - 1).getCreatetime())).getTime();
                    int min = (int) ((late - early) / (1000 * 60));
                    if (min != 0) {
                        ryzxModelMapper.insertRwlz(yysdbh, lzEntities.get(i - 1).getYhdm(), min);
                        // System.out.println("OK");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            // System.out.println("Big");
        }
    }

    @Test
    public void testXysc() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<lzEntity> lzEntities = ryzxModelMapper.getEntity();
        for (lzEntity lzEntity : lzEntities) {
            String lateTime = ryzxModelMapper.getTime(lzEntity.getYysdbh(), lzEntity.getCreatetime().toString());

            try {
                long late = 0;
                if (lateTime == null || lateTime == "") {
                    late = Calendar.getInstance().getTime().getTime();
                } else {
                    late = sdf.parse(lateTime).getTime();
                }
                long early = sdf.parse(sdf.format(lzEntity.getCreatetime())).getTime();
                int min = (int) ((late - early) / (1000 * 60));
                if (min != 0) {
                    ryzxModelMapper.insertXysc(lzEntity.getYysdbh(), lzEntity.getYhdm(), min);
                    // System.out.println("OK");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }
            // System.out.println("Big");
        }
    }

    @Test
    public void TestSdsc() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<XyscEntity> XyscEntities = ryzxModelMapper.getXyscEntity();
        for (XyscEntity xyscEntity : XyscEntities) {
            try {
                long late = sdf.parse(xyscEntity.getSdsj()).getTime();
                long early = sdf.parse(xyscEntity.getCreatetime()).getTime();
                int min = (int) ((late - early) / (1000 * 60));
                if (min != 0) {
                    ryzxModelMapper.insertRwlz(xyscEntity.getYysdbh(), xyscEntity.getYhdm(), min);
                    // System.out.println("OK");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testTydzsdrs() {
        System.out.println(pubWebCallInfoEntityMapper.Tydzsdrs("test") == null ? 0 : pubWebCallInfoEntityMapper.Tydzsdrs("test"));
    }

    @Test
    public void testfushu() {
        System.out.println(Integer.valueOf("-54") < 0);
    }

    @Test
    public void testModel() {
        List<RyzxModel> ryzxModels = ryzxModelMapper.findInAll(20, 0);
        System.out.println();
    }

    @Test
    public void uploadwj() throws Exception {


        //获取请求路径
//        String url="http://130.1.1.122:8989/service/discovery?service=document.filesystem.upload";
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpClient client = HttpClientBuilder.create().build();
//        String serviceUrl = null;
//        try {
//            serviceUrl = EntityUtils.toString(client.execute(httpGet).getEntity());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        //获取下载路径
//        String url="http://130.1.1.122:8989/service/discovery?service=document.filesystem.download";
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpClient client = HttpClientBuilder.create().build();
//        String serviceUrl = EntityUtils.toString(client.execute(httpGet).getEntity());
//
//
//        System.out.println(serviceUrl);
//
//            DynamicDataSource.routerByFybh("64");
//            List<PubLcspFjbModel> pubLcspFjbModels = new ArrayList<>();
//            pubLcspFjbModels = pubLcspFjbMapper.getPubLcspFjModels();
//            DmbEntity dmb = dmbMapper.getDmByLbbhAndDmbh("系统缺省", "法院代码");
//            System.out.println(dmb.getXgdm());
//            UploadRequestModel uploadRequestModel = new UploadRequestModel();
//            UploadResult uploadResult = null;
////            for (PubLcspFjbModel pubLcspFjbModel : pubLcspFjbModels) {
//                //上传文件
//
////                if (pubLcspFjbModel.getDate() == null) {
////                    continue;
////                }
//                HttpPost httpPost = new HttpPost(serviceUrl);
////                pubLcspFjbModel.setContent((byte[]) pubLcspFjbMapper.getContent(pubLcspFjbModel.getId()));
////                Calendar calendar = Calendar.getInstance();
////                calendar.setTime(pubLcspFjbModel.getDate());
////                uploadRequestModel.setOverride(true);
////                uploadRequestModel.setId("fzxt");
////                uploadRequestModel.setBase64(false);
////                uploadRequestModel.setPath("/LCSP/" + dmb.getXgdm() + "/" + calendar.get(Calendar.YEAR) + "/" + pubLcspFjbModel.getAjxh() +
////                        "/" + pubLcspFjbModel.getId() + "/" + pubLcspFjbModel.getDescription());
////                uploadRequestModel.setContent(BaseEncoding.base64().encode(pubLcspFjbModel.getContent()));
////                httpPost.setEntity(new StringEntity(JSON.toJSONString(uploadRequestModel), "utf-8"));
////                uploadResult = JSON.parseObject(EntityUtils.toString(client.execute(httpPost).getEntity()), UploadResult.class);
////                if (uploadResult.getCode().equals(200)) {
////                    pubLcspFjbMapper.update(uploadResult.getPath(), pubLcspFjbModel.getId());
////                    pubLcspFjbModel = null;
////                } else {
////                    System.out.print(pubLcspFjbModel.getId() + "：失败，失败原因：  " + uploadResult.getMessage());
////                }
//
//
//            DownloadRequestModel downloadRequestModel = new DownloadRequestModel();
//            downloadRequestModel.setId("fzxt");
//            downloadRequestModel.setBase64(false);
//            downloadRequestModel.setPath("/LCSP/64/2021/256409/e6e8455e-5e71-4f66-b6d4-619faa323ae9/补正裁定.docx");
//            httpPost.setEntity(new StringEntity(JSON.toJSONString(downloadRequestModel)));
//            DownloadResult downloadResult= JSON.parseObject(EntityUtils.toString(client.execute(httpPost).getEntity()), DownloadResult.class);
//            FileUtil.base64ToFile("E:\\N_ftp\\" , FileUtil.decode(downloadResult.getContent()), "wj.doc");
//           FileUtil.ByteToFile(FileUtil.decode(downloadResult.getContent()),"E:\\N_ftp\\" + "wj.doc");
//
//
////            }
//
//            System.out.println("河西法院上传完成");


        //获取请求路径
        String url = "http://130.1.1.122:9898/service/discovery?service=document.filesystem.upload";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String serviceUrl = null;
        try {
            serviceUrl = EntityUtils.toString(client.execute(httpGet).getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        //获取下载路径
//        String url="http://130.1.1.122:8989/service/discovery?service=document.filesystem.download";
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpClient client = HttpClientBuilder.create().build();
//        String serviceUrl = EntityUtils.toString(client.execute(httpGet).getEntity());


        System.out.println(serviceUrl);

        for (FYEnum enmu : FYEnum.values()) {
            DynamicDataSource.routerByFybh(enmu.getFybh());
            List<PubLcspFjbModel> pubLcspFjbModels = new ArrayList<>();
            pubLcspFjbModels = pubLcspFjbMapper.getPubLcspFjModels();
            DmbEntity dmb = dmbMapper.getDmByLbbhAndDmbh("系统缺省", "法院代码");
            System.out.println(dmb.getXgdm());
            UploadRequestModel uploadRequestModel = new UploadRequestModel();
            UploadResult uploadResult = null;
            if (pubLcspFjbModels == null) {
                continue;
            }
            for (PubLcspFjbModel pubLcspFjbModel : pubLcspFjbModels) {
                //上传文件

                if (pubLcspFjbModel.getDate() == null) {
                    continue;
                }
                HttpPost httpPost = new HttpPost(serviceUrl);
                pubLcspFjbModel.setContent((byte[]) pubLcspFjbMapper.getContent(pubLcspFjbModel.getId()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(pubLcspFjbModel.getDate());
                uploadRequestModel.setOverride(true);
                uploadRequestModel.setId("fzxt");
                uploadRequestModel.setBase64(false);
                uploadRequestModel.setPath("/LCSP/" + dmb.getXgdm() + "/" + calendar.get(Calendar.YEAR) + "/" + pubLcspFjbModel.getAjxh() +
                        "/" + pubLcspFjbModel.getId() + "/" + pubLcspFjbModel.getDescription());
                if (pubLcspFjbModel.getContent() == null) {
                    continue;
                }
                uploadRequestModel.setContent(BaseEncoding.base64().encode(pubLcspFjbModel.getContent()));
                httpPost.setEntity(new StringEntity(JSON.toJSONString(uploadRequestModel), "utf-8"));
                uploadResult = JSON.parseObject(EntityUtils.toString(client.execute(httpPost).getEntity()), UploadResult.class);
                if (uploadResult.getCode().equals(200)) {
                    pubLcspFjbMapper.update(uploadResult.getPath(), pubLcspFjbModel.getId());
                    pubLcspFjbModel = null;
                } else {
                    System.out.print(pubLcspFjbModel.getId() + "：失败，失败原因：  " + uploadResult.getMessage());
                }


//            DownloadRequestModel downloadRequestModel = new DownloadRequestModel();
////            downloadRequestModel.setId("fzxt");
////            downloadRequestModel.setBase64(false);
////            downloadRequestModel.setPath(pubLcspFjbMapper.getWjlj(pubLcspFjbModel.getId()));
////            httpPost.setEntity(new StringEntity(JSON.toJSONString(downloadRequestModel)));
//            DownloadResult downloadResult= JSON.parseObject(EntityUtils.toString(client.execute(httpPost).getEntity()), DownloadResult.class);
//            FileUtil.base64ToFile("E:\\N_ftp\\" , FileUtil.decode(downloadResult.getContent()), "wj.doc");
//           FileUtil.ByteToFile(FileUtil.decode(downloadResult.getContent()),"E:\\N_ftp\\" + "wj.doc");


            }

            System.out.println(enmu.getName() + " 法院上传完成");
        }


    }

    @Test
    public void testData() {
        ArrayList<PubYysdJbEntity> jbs=new ArrayList<>();
        try {
            File xmlFile = new File("D:\\data\\河东法院渤海银行.txt");
            //读取xml内容
            InputStreamReader isr = new InputStreamReader(new FileInputStream(xmlFile), "GBK");
            BufferedReader reader = new BufferedReader(isr);
            //进行解析
            List<String> res = new ArrayList<>();
            String cache;
            while ((cache = reader.readLine()) != null) {
                res.add(cache);
            }

//            DynamicDataSource.router("57");

            reader.close();
            int i = 0;
            for (String str : res) {
                i++;
                String[] split = str.split("\t");
                String ah = split[0];
//                String sfzjh = split[1];
//                String jklx = split[2];
//                String fybh = split[3];
//                String fhje = split[4];
//                String djfhrq = split[5];
//                String fhrq = split[6];
//                String dlrxm = split[7];
//                String je=split[8];
//                String dlrxm = split[7];
                System.out.println("第" + i + "条:" + ah);
                ArrayList<PubYysdJbEntity> jb= (ArrayList<PubYysdJbEntity>) pubLylqSdhzEntityMapper.findByName(ah);
                for(PubYysdJbEntity pubYysdJbEntity:jb){
                    jbs.add(pubYysdJbEntity);
                }
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

//for (PubLcspFjbModel pubLcspFjbModel : pubLcspFjbModels) {
//        //上传文件
//
//        HttpPost httpPost = new HttpPost(serviceUrl);
//        pubLcspFjbModel.setContent((byte[])lcspFujianDao.getContent(pubLcspFjbModel.getId()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(pubLcspFjbModel.getDate());
//        UploadRequestModel uploadRequestModel = new UploadRequestModel();
//        uploadRequestModel.setOverride(true);
//        uploadRequestModel.setId("fzxt");
//        uploadRequestModel.setBase64(false);
//        uploadRequestModel.setPath("/LCSP/"+ dmb.getFybh() +"/"+ calendar.get(Calendar.YEAR) +"/"+ pubLcspFjbModel.getAjxh() +
//        "/" + pubLcspFjbModel.getId() + "/" + pubLcspFjbModel.getDescription());
//        uploadRequestModel.setContent(BaseEncoding.base64().encode(pubLcspFjbModel.getContent()));
//        httpPost.setEntity(new StringEntity(JSON.toJSONString(uploadRequestModel)));
//        UploadResult uploadResult = JSON.parseObject(EntityUtils.toString(client.execute(httpPost).getEntity()), UploadResult.class);
//        if(uploadResult.getCode().equals(200)) {
//        try {
//        lcspFujianDao.update( uploadResult.getPath(), pubLcspFjbModel.getId());
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//        } else {
//        System.out.print(pubLcspFjbModel.getId() +"：失败，失败原因：  " + uploadResult.getMessage());
//        }