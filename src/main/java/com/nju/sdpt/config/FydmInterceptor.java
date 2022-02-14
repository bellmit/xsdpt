package com.nju.sdpt.config;


import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.util.StringUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mybatis拦截器,针对滨海的sql单独处理
 */
@Component
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class})
})
public class FydmInterceptor implements Interceptor {

    private static Map<String, Fydm> fydms = new ConcurrentHashMap<>(256);
    private final String fydm = "120242 22A";

    private Map<String, Object> notExists = new ConcurrentHashMap<>(256);

    private static final Object PRESENT = new Object();

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        String currentDB = DynamicDataSource.getCurrentDB();

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String id = mappedStatement.getId();
        Object parameter = invocation.getArgs()[1];

        final SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        if (!SqlCommandType.UPDATE.equals(sqlCommandType) && !SqlCommandType.SELECT.equals(sqlCommandType)) {
            return invocation.proceed();
        }


        final boolean b = fydms.containsKey(id);

        if (!b) {
            Fydm fydmAnno = hasFydm(id);
            if (null != fydmAnno) {
                fydms.put(id, fydmAnno);
            }
        }


        final Fydm fydmAnno = fydms.get(id);

        //没有Fydm注解且数据源是滨海法院,sql拼接法院代码
        if (fydmAnno==null && StringUtil.equals(currentDB,"tjbhxqfy")) {
            final String methodName = invocation.getMethod().getName();
            if ("query".equals(methodName) || "update".equals(methodName)) {
                //sql拆分
                BoundSql boundSql = mappedStatement.getBoundSql(parameter);
                String originalSql = boundSql.getSql().trim();
                String afterSql = originalSql;
                //将top 1替换为limit 1
                if(afterSql.contains("top 1")){
                    afterSql = afterSql.replace("top 1"," ");
                    afterSql += " limit 1";
                }

                int selectNum = getSubStrNum(originalSql,"select");
                for (int i=0; i<selectNum;i++ ) {
                    afterSql =dealSql(afterSql,i);
                }
                BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), afterSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
                MappedStatement newMs = this.copyFromMappedStatement(mappedStatement, new FydmInterceptor.BoundSqlSqlSource(newBoundSql));
                invocation.getArgs()[0] = newMs;
            }
        }

        SqlObject sqlObject = new SqlObject();
        sqlObject.setId(mappedStatement.getId());
        sqlObject.setCurrentTime(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        sqlObject.setTimeLength(end-start);
//        redisTemplate.opsForList().leftPush("sqlList",sqlObject);
//        redisTemplate.opsForList().trim("sqlList",0,10000);
//        redisTemplate.opsForZSet().incrementScore("sqlFre",mappedStatement.getId(),1);
//        redisTemplate.expire("sqlList",8*3600, TimeUnit.SECONDS);
        return result;
    }

    private String dealSql(String afterSql, int i) {
        int selecteIndex = getFromIndex(afterSql,"select",i+1);
        int fromIndex =  getFromIndex(afterSql,"from",i+1);
        int whereIndex =  getFromIndex(afterSql,"where",i+1);
        String beforeFromStr = afterSql.substring(0, fromIndex);
        String tableStr = afterSql.substring(fromIndex+5,whereIndex);
        String afterWhereStr = afterSql.substring(whereIndex+6);
        StringBuilder resultSql = new StringBuilder();
        resultSql.append(beforeFromStr).append(" from ");
        resultSql.append(tableStr).append(" where ");
        String[] tables = tableStr.split(",");
        //多表需要做连接
        if (tables.length > 1) {
            for (int j = 0; j < tables.length; j++) {
                String[] table = tables[j].trim().split(" ");
                resultSql.append(" " + table[table.length - 1].trim() + ".FYDM = '" + fydm + "' and ");
            }
        } else {
            resultSql.append("  FYDM =  '" + fydm + "' and ");
        }
        resultSql.append(afterWhereStr);
        return resultSql.toString();
    }

    private int getSubStrNum(String str, String sub) {
        int fromIndex = 0;
        int count = 0;
        while(true){
            int index = str.indexOf(sub, fromIndex);
            if(-1 != index){
                fromIndex = index + 1;
                count++;
            }else{
                break;
            }
        }
        return count;
    }


    //子字符串modelStr在字符串str中第count次出现时的下标
    private int getFromIndex(String str, String modelStr, Integer count) {
        //对子字符串进行匹配
        Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
        int index = 0;
        while (slashMatcher.find()) {
            index++;
            if (index == count) {
                break;
            }
        }
        return slashMatcher.start();
    }


    private Fydm hasFydm(String id) throws Exception {

        if (notExists.containsKey(id)) {
            return null;
        }

        int lastIndex = id.lastIndexOf(".");

        String className = id.substring(0, lastIndex);

        String methodName = id.substring(lastIndex + 1);

        final Class<?> clazz = Class.forName(className);
        final Fydm clazzAnnotation = clazz.getAnnotation(Fydm.class);

        if (null != clazzAnnotation) {
            return clazzAnnotation;
        } else {
            final Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    final Fydm annotation = method.getAnnotation(Fydm.class);
                    if (null == annotation) {
                        notExists.put(id, PRESENT);
                        return null;
                    }

                    return annotation;
                }
            }
        }
        notExists.put(id, PRESENT);
        return null;
    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        MappedStatement newMs = builder.build();
        return newMs;
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return this.boundSql;
        }
    }


}
