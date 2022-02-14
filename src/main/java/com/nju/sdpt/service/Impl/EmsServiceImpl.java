package com.nju.sdpt.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.DmbEntity;
import com.nju.sdpt.entity.DzjzWdWjDO;
import com.nju.sdpt.entity.PubKdtdEntity;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.mapper.KdtdMapper;
import com.nju.sdpt.mapper.RpoEmsInfoEntityMapper;
import com.nju.sdpt.model.CxsjResult;
import com.nju.sdpt.model.EmssdModel;
import com.nju.sdpt.model.FYEnum;
import com.nju.sdpt.service.EmsService;
import com.nju.sdpt.service.SsdrService;
import com.nju.sdpt.util.*;
import com.nju.sdpt.viewobject.Result;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class EmsServiceImpl implements EmsService {

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    @Autowired
    DmbMapper dmbMapper;
    @Autowired
    KdtdMapper kdtdMapper;
    @Autowired
    RpoEmsInfoEntityMapper rpoEmsInfoEntityMapper;
    @Autowired
    private SsdrService ssdrService;

    private static final String DZJZ_UPLOAD = "gatewayUploadMul.do";

    private static final String DZJZ_DELETE = "delete/deleteWj.do";

    public String getServerUrl(String fybh, boolean delete) {
        StringBuilder serverUrl = new StringBuilder();
        serverUrl.append(dmbMapper.findXtcygjbUrlByGjmc("电子卷宗"));
        serverUrl.append(delete ? DZJZ_DELETE : DZJZ_UPLOAD);
        return serverUrl.toString();
    }

    @Override
    public CxsjResult findByMlmc(String ah, String mlmc, String fybh) {
        CxsjResult cxsjResult = new CxsjResult();
        HttpClient client = new DefaultHttpClient();
        DynamicDataSource.routerByFybh(fybh);
        String serverUrl = dmbMapper.findXtcygjbUrlByGjmc("电子卷宗");
        String targetUrl = serverUrl + "gatewayFindByMlmc.do";

//        ah = Base64Util.encode_utf8(ah);
//        mlmc = Base64Util.encode_utf8(mlmc);

        HttpPost httpPost = new HttpPost(targetUrl);
        List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
        paramsList.add(new BasicNameValuePair("ah", Base64Util.encode_utf8(ah)));
        paramsList.add(new BasicNameValuePair("mlmc", Base64Util.encode_utf8(mlmc)));
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
//					uefEntity.setContentType("application/json");
            httpPost.setEntity(uefEntity);
//					httpPost.setHeader("contentType", "application/json");
            System.out.println("executing request:" + httpPost.getURI());
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String str = EntityUtils.toString(entity, "GBK");
                    System.out.println("---------------" + response.getStatusLine());
                    System.out.println("Response content:" + str);
                    System.out.println("---------------");

                    if(response.getStatusLine().getStatusCode() != 200) {
                        cxsjResult.setSuccess(false);
                        return cxsjResult;
                    }else {
                        cxsjResult = JSON.parseObject(str, CxsjResult.class);
                        return cxsjResult;
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cxsjResult.setSuccess(false);
        return cxsjResult;
    }

    @Override
    public Boolean uploadEmsmd(Integer ajxh, MultipartFile file, String yhbh, Integer kdid, String fybh) throws IOException {
        boolean success;
        String targetUrl = getServerUrl(fybh, false);
        if (targetUrl == null) {
            return false;
        }
        String root = Base64Util.encode_utf8("正卷");
        String folder = Base64Util.encode_utf8("EMS面单");
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String filename = "ems面单_" + kdid + fileSuffix;
        String wjmc = Base64Util.encode_utf8(filename);
//        serverUrl = "http://130.1.67.18:8081/";测试
        try {
            Map<String, Map<String, Object>> params = new HashMap<String, Map<String, Object>>() {{
                put("TextBody", new HashMap() {{
                    put("ajxh", String.valueOf(ajxh));
                    put("fybh", fybh);
                    put("yhbh", yhbh);
                    put("wjmc", wjmc);
                    put("root", root);
                    put("folder", folder);
                }});
                put("Content", new HashMap() {{
                    put("file", new ByteArrayBody(file.getBytes(), wjmc));
                }});
            }};
            String result = HttpUtil.sendHttpFilePost(params, targetUrl);
            success = !StringUtil.equals(result, "failed");
            if (success) {
                if (FYEnum.isBhFybh(fybh)) {
                    result = result.replace("\r", "").replace("\n", "");
                    String wjid = JSONObject.parseObject(result).getString("obj");
                    kdtdMapper.updateKdmdStatusByBh(kdid, wjid, filename, "Y");
                } else {
                    kdtdMapper.updateKdmdStatus(kdid, "Y");
                }
            }
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    /**
     * 删除Ems面单
     * @param kdid
     * @param fybh
     * @param yhbh
     * @return
     */
    @Override
    public Boolean deleteEmsmd(Integer kdid, String fybh, String yhbh,Integer ajxh) {
        boolean success;
        try {
            String targetUrl = getServerUrl(fybh, true);
            if (targetUrl == null) {
                return false;
            }
            String wjid ="";
            if(StringUtil.equals(fybh,"74")){
                wjid = kdtdMapper.getwjid(kdid);
            }else {
                String filename = "ems面单_" + kdid + ".";
                // 判断是否使用了mongodb数据库
                DmbEntity dmByLbbhAndDmbh = dmbMapper.getDmByLbbhAndDmbh("系统缺省", "电子卷宗mongo");
                if(dmByLbbhAndDmbh!=null){
                    // 获取到新的地址
                    String url=dmByLbbhAndDmbh.getDmms()+"getWjListByWjmc.do";
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("fydm", FYEnum.getFYDMByFybh(fybh));
                    params.put("ajxh", ajxh.toString());
                    params.put("wjmc",filename+"jpg");
                    ComparatorDateUtil c=new ComparatorDateUtil();
                    List<DzjzWdWjDO> DzjzWdWjDos=MongoUtil.getListByPost(url, params, DzjzWdWjDO.class);
                    Collections.sort(DzjzWdWjDos,c);
                    DzjzWdWjDO DzjzWdWjDo=DzjzWdWjDos.get(DzjzWdWjDos.size()-1);
                    wjid=DzjzWdWjDo.getWjid();
                }else{
                    wjid = kdtdMapper.getWjidByFilename(filename).get(0);
                }
            }
            if(StringUtil.isEmpty(wjid)){
                return false;
            }
            String finalWjid = wjid;
            Map<String, Map<String, Object>> params = new HashMap() {{
                put("TextBody", new HashMap() {{
                    put("yhbh", yhbh);
                    put("fybh", fybh);
                    put("wjid", finalWjid);
                }});
            }};
            String result = HttpUtil.sendHttpFilePost(params, targetUrl);
            result = result.replace("\r", "").replace("\n", "");
            success = !StringUtil.equals(result, "failed") && JSONObject.parseObject(result).getBoolean("success");
            if(success){
                DynamicDataSource.router(FydmUtil.getFydmByFybh(fybh));
                if (FYEnum.isBhFybh(fybh)) {
                    kdtdMapper.updateKdmdStatusByBh(kdid, null, null, "N");
                } else {
                    kdtdMapper.updateKdmdStatus(kdid, "N");
                }
            }else {
                success = false;
            }
//            success = true;
        } catch (Exception e) {
            success = false;
        }
        return success;
    }


    @Override
    public String downloadEmsmd(Integer kdid, String fybh, Integer ajxh) {
        //  http://130.26.0.8:8081/dzjz/getFile/"+wjDO.getWjid()
        String filename = "ems面单_" + kdid + ".";
        String wjid = "";
        String wjmc = "";
        DzjzWdWjDO DzjzWdWjDo=null;
        if ("74".equals(fybh)) {
            wjid = kdtdMapper.getwjid(kdid);
            if(wjid== null || wjid.length() == 0) {
                return "";
            }
            wjmc = System.currentTimeMillis() + kdtdMapper.getWjmc(kdid);
        } else {
            DmbEntity dmByLbbhAndDmbh = dmbMapper.getDmByLbbhAndDmbh("系统缺省", "电子卷宗mongo");
            if(dmByLbbhAndDmbh!=null){
                // 获取到文件存放的地址
                String url=dmByLbbhAndDmbh.getDmms()+"getWjListByWjmc.do";
                Map<String, String> params=new HashMap<String, String>();
                if(ajxh<0){
                    ajxh=kdtdMapper.getAjxhByKdid(kdid);
                }
                params.put("fydm", FYEnum.getFYDMByFybh(fybh));
                params.put("ajxh", ajxh.toString());
                params.put("wjmc",filename+"jpg");
                ComparatorDateUtil c=new ComparatorDateUtil();
                List<DzjzWdWjDO> DzjzWdWjDos=MongoUtil.getListByPost(url, params, DzjzWdWjDO.class);
                if(DzjzWdWjDos.size()==0){
                    return "";
                }
                Collections.sort(DzjzWdWjDos,c);
                DzjzWdWjDo=DzjzWdWjDos.get(DzjzWdWjDos.size()-1);
                wjid=DzjzWdWjDo.getWjid();
                wjmc=System.currentTimeMillis()+filename+"jpg";
            }else {
                List<String> list = null;
                list = kdtdMapper.getWjidByFilename(filename);
                if(list != null && list.size() > 0) {
                    wjid = list.get(0);
                } else {
                    return "";
                }
                wjmc = System.currentTimeMillis() + kdtdMapper.getWjmcByWjid(wjid);
            }
        }
        String serverUrl = dmbMapper.findXtcygjbUrlByGjmc("电子卷宗");
        String url = serverUrl + "getFile/" + wjid;
        String filePath = "E:\\workspace\\" + fybh + "\\" + wjmc;
        downloadNew(url, filePath, filename);
        return filePath;
    }

    @Override
    public List<EmssdModel> getEmsModelByYysdbhList(String fybh, List<Integer> yysdbhList) {
        List<EmssdModel> emssdModels = new LinkedList<>();
        DynamicDataSource.routerByFybh(fybh);
        StringBuffer stringBuilder = new StringBuffer();
        for (Integer jbbh : yysdbhList) {
            stringBuilder.append(jbbh + ",");
        }
        String sql = "";
        if (stringBuilder.length() > 0) {
            sql = stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        List<PubKdtdEntity> kdtdEntities = kdtdMapper.findGdByYysdbhs(sql);
        for (PubKdtdEntity pubKdtdEntity : kdtdEntities) {
            EmssdModel emssdModel = new EmssdModel(pubKdtdEntity);
            emssdModel.setFymc(FYEnum.getFyByFybh(fybh).getJc());
            emssdModel.setFybh(fybh);
            emssdModels.add(emssdModel);
        }
        return emssdModels;
    }


    public static void downloadNew(String fileurl, String storePath, String Name) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String downloadUrl = fileurl;
            String fileName = Name;
            String directory = storePath;
//            file = new File(directory+fileName);
            file = new File(directory);
//            file = new File(directory);
            if (file.exists()) {
                downloadedLength = file.length();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl).build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    total = total + len;
                    savedFile.write(b, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean updateEmsSdjg(Integer kdid, String fybh, String sdjg, Date date){
        DynamicDataSource.routerByFybh(fybh);
        return kdtdMapper.uploadSdjgByKdid(kdid, sdjg, date);
    }

    @Override
    public boolean deleteEmsInfo(Integer yysdbh, Integer kdid){
        DynamicDataSource.router(SDPT_FYDM);
        return rpoEmsInfoEntityMapper.deleteRecordByYysdbhAndKdid(yysdbh, kdid);
    }

    @Override
    public boolean deleteKdtdByKdid(Integer kdid, String fybh){
        DynamicDataSource.routerByFybh(fybh);
        return kdtdMapper.deleteKdtdBykdid(kdid);
    }

    @Override
    public boolean existsEmsInfoByKdid(Integer yysdbh,Integer kdid){
        DynamicDataSource.router(SDPT_FYDM);
        return rpoEmsInfoEntityMapper.selectCountByYYsdbhAndKdid(yysdbh, kdid)>0;
    }

    @Override
    public List<PubKdtdEntity> getEmsInfoByKddh(String kddh) {
        return kdtdMapper.findKdtdByKddh(kddh);
    }

    @Override
    public PubKdtdEntity getGlEmsInfoByKddh(String kddh) {
        List<PubKdtdEntity> kdtdEntities=kdtdMapper.findKdtdByKddh(kddh);
        if(kdtdEntities==null){
            return null;
        }
        return kdtdEntities.get(0);
    }
}
