package com.nju.sdpt.service.Impl;

import com.nju.sdpt.mapper.YysdInfoMapper;
import com.nju.sdpt.model.*;
import com.nju.sdpt.model.info.YysdInfoModel;
import com.nju.sdpt.service.YysdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class YysdInfoServiceImpl implements YysdInfoService {

    @Autowired
    YysdInfoMapper yysdInfoMapper;


    public class QueryParams {

        private Map<String, String> params = new HashMap<>();

        public QueryParams addParams(String key, String value) {
            params.put(key, value);
            return this;
        }

        public String getValue(String key) {
            return params.getOrDefault(key, null);
        }

        public boolean removeParams(String key, String value) {
            return params.remove(key, value);
        }

        public String fillSql(String sql){
            for (Map.Entry<String,String> entry:params.entrySet()){
                sql = sql.replace("#{"+entry.getKey()+"}",entry.getValue());
            }
            return sql;
        }
    }


    @Override
    public List<YysdModel> getYysdListByGdryxm(String yhmc, Integer sdjg, String start, String end) {
        QueryParams params = new QueryParams()
                .addParams("yhmc",yhmc)
                .addParams("start",start)
                .addParams("end",end);
        List<YysdInfoModel> list = yysdInfoMapper.getYysdInfoByGdryxm(params, YysdInfoMapper.YysdQueryType.getByNo(sdjg));
        return list.stream().map(YysdModel::new).collect(Collectors.toList());
    }
    @Override
    public List<ZsalModel> zsalSjtj() {
        return yysdInfoMapper.zsalSjtj();
    }

    @Override
    public List<ZdxlModel> zdxlSjtj() {
        return yysdInfoMapper.zdxlSjtj();
    }

    @Override
    public List<ZwhlModel> zwhlSjtj() {
        return yysdInfoMapper.zwhlSjtj();
    }

    @Override
    public List<ZxflModel> zxflSjtj() {
        return yysdInfoMapper.zxflSjtj();
    }


}
