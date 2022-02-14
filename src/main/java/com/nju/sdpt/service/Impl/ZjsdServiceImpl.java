package com.nju.sdpt.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.sdpt.constant.SdptConstants;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.entity.*;
import com.nju.sdpt.mapper.DmbMapper;
import com.nju.sdpt.mapper.PubZjsdInfoEntityMapper;
import com.nju.sdpt.model.RepairInfoModel;
import com.nju.sdpt.model.xml.CallRepairResponseXml;
import com.nju.sdpt.service.ZjsdService;
import com.nju.sdpt.util.Base64Util;
import com.nju.sdpt.util.XMLObjUtil;
import com.nju.sdpt.viewobject.RepairQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.nju.sdpt.service.Impl.EmsServiceImpl.downloadNew;

@Slf4j
@Service
public class ZjsdServiceImpl implements ZjsdService {

    private final Logger logger = LoggerFactory.getLogger(ZjsdServiceImpl.class);

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    PubZjsdInfoEntityMapper pubZjsdInfoEntityMapper;
    @Autowired
    DmbMapper dmbMapper;

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    @Override
    public void uploadZjsd(MultipartFile file, Integer zjsdbh) {
        String url;
        String originalFilename;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date());
        String path = "E:\\insdpt\\uploadFile\\"+format1+"\\";

        XMLObjUtil.isChartPathExit(path);
        //文件名+后缀
        originalFilename = file.getOriginalFilename();
        //获取后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("." ));
        String s = UUID.randomUUID().toString();
        url = format1+ "\\" + s + suffix;
        File file1 = new File(path + s + suffix);
        try {
            file.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pubZjsdInfoEntityMapper.updateZjsdSdhzById(url,zjsdbh);
    }

    @Override
    public String downloadZjsdWj(Integer yysdbh,Integer zjsdbh,String fybh,String wjid,String wjmc) {
        //  http://130.26.0.8:8081/dzjz/getFile/"+wjDO.getWjid()

        String filename ="直接送达文件包_"+yysdbh+".";
        DynamicDataSource.routerByFybh(fybh);
        String serverUrl = dmbMapper.findXtcygjbUrlByGjmc("电子卷宗");
        String url =serverUrl+"getFile/"+wjid;
        String filePath = "E:\\workspace\\"+fybh+"\\"+filename+"zip";
        downloadNew(url,filePath,filename);
        return filePath;
    }

    @Override
    public Boolean uploadWj(Integer ajxh, MultipartFile file,String yhbh,Integer yysdbh,Integer zjsdbh,String fybh){
        String serverUrl = null;
        DynamicDataSource.routerByFybh(fybh);
        serverUrl = dmbMapper.findXtcygjbUrlByGjmc("电子卷宗");
        if (serverUrl == null) {
            return false;
        }
        String root = null;
        try {
            root = Base64Util.encode("正卷".getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String folder = Base64Util.encode("直接送达文件".getBytes(Charset.forName("UTF-8")));
        String filename = "直接送达文件_"+yysdbh+"_"+zjsdbh+".pdf";
//            String filename = "";
        String wjmc = filename;
        filename = Base64Util.encode(filename.getBytes(Charset.forName("UTF-8")));
//        serverUrl = "http://130.1.67.18:8081/";测试
        String targetUrl = serverUrl + "gatewayUploadMul.do";
        HttpPost post = new HttpPost(targetUrl);
        HttpEntity entity = null;
        try {
            entity = MultipartEntityBuilder.create()
                    .addTextBody("ajxh", String.valueOf(ajxh))
                    .addTextBody("fybh", fybh)
                    .addTextBody("yhbh", yhbh)
                    .addTextBody("wjmc", filename)
                    .addTextBody("root", root)
                    .addTextBody("folder", folder)
                    .addPart("file", new ByteArrayBody(file.getBytes(), filename))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setEntity(entity);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(5000)
                .build();
        post.setConfig(requestConfig);
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse httpResponse = httpClient.execute(post)
        ) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String result;
            result = EntityUtils.toString(httpResponse.getEntity());
            result = result.replace("\r", "").replace("\n", "");
            JSONObject JS = JSONObject.parseObject(result);
            String wjid = JS.getString("obj");
            if (statusCode == HttpStatus.SC_OK) {
//                    kdtdMapper.updateKdmdStatusByBh(kdid,wjid,wjmc);
                DynamicDataSource.router(SDPT_FYDM);
                pubZjsdInfoEntityMapper.updateZjsdWjStatus(yysdbh,zjsdbh,wjid,wjmc);
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void handleZjsdResponseXML(){
        //定义读取目录
        String readPath = "E:\\outsdpt\\sdzx";
        XMLObjUtil.isChartPathExit(readPath);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        File[] filesList = XMLObjUtil.getCurFilesList(readPath);
        if (null != filesList && filesList.length > 0) {
            for (File file : filesList) {
                String path = file.getPath();
                try {
                    ZjsdResponseModel zjsdResponseModel=(ZjsdResponseModel) XMLObjUtil.convertXmlFileToObject(ZjsdResponseModel.class,path);
                    logger.info("[handleZjsdResponseXML(FileName:{})]数据：{}", file.getName(), JSON.toJSONString(zjsdResponseModel));

                    PubZjsdInfoEntity pubZjsdInfoEntity=pubZjsdInfoEntityMapper.selectByPrimaryKey(Integer.parseInt(zjsdResponseModel.getZjsdbh()));
                    if(pubZjsdInfoEntity==null){
                        continue;
                    }
                    if("拒收".equals(zjsdResponseModel.getQsjg())){
                        pubZjsdInfoEntity.setQszt(2);
                    }else if("投妥".equals(zjsdResponseModel.getQsjg())){
                        pubZjsdInfoEntity.setQszt(1);
                    }else if("本人签收".equals(zjsdResponseModel.getQsjg())){
                        pubZjsdInfoEntity.setQszt(3);
                    }
                    pubZjsdInfoEntity.setRemark(zjsdResponseModel.getSdbz());
                    pubZjsdInfoEntity.setSdhz(zjsdResponseModel.getHzbList().get(0).getHz());
                    String tpList="[";
                    for(int i=0;i<zjsdResponseModel.getSdgcjlList().size();i++){
                        tpList+=zjsdResponseModel.getSdgcjlList().get(i).getTp()+".jpg";
                        if(i+1<zjsdResponseModel.getSdgcjlList().size()){
                            tpList+=",";
                        }
                    }
                    tpList+="]";
                    pubZjsdInfoEntity.setSmsj(simpleDateFormat.parse(zjsdResponseModel.getSdsj()));
                    pubZjsdInfoEntity.setSdgcjl(tpList);
                    pubZjsdInfoEntityMapper.updateByPrimaryKey(pubZjsdInfoEntity);
                    //最后需要删除文件 避免多次读取
                    file.delete();
                } catch (Exception ex) {
                    logger.error(simpleDateFormat.format(new Date())+"文件"+file.getPath()+"解析失败");
                }
            }
        }
    }
}
