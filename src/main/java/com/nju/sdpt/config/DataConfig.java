package com.nju.sdpt.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.nju.sdpt.data.dynamicdDatabases.DataSourceEnum;
import com.nju.sdpt.data.dynamicdDatabases.DynamicDataSource;
import com.nju.sdpt.data.dynamicdDatabases.EncryptDataSource;
import com.nju.sdpt.data.dynamicdDatabases.getpasswd;
import com.nju.sdpt.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库配置
 */
@Configuration
@PropertySource({ "classpath:config/jdbc.yml" })
@EnableTransactionManagement(proxyTargetClass = true)
@Component
public class DataConfig {
    @Autowired
    private Environment env ;

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

    /**
     *  将jdbc相关的异常转换为spring的异常类型
     */
    @Bean
    public BeanPostProcessor persistenceTransLation(){
        return new PersistenceExceptionTranslationPostProcessor() ;
    }

    /**
     * 多数据源
     * @return
     */
//    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> sourceMap = new HashMap<>();
        //取得所有的datasource
        EnumSet<DataSourceEnum> enums = EnumSet.allOf(DataSourceEnum.class);
        for(DataSourceEnum dataSource:enums){
//            sourceMap.put(dataSource.getKey(),generateDataSource(dataSource.getKey()));
            sourceMap.put(dataSource.getKey(),getDruidDataSource(dataSource.getKey()));
        }
        dynamicDataSource.setTargetDataSources(sourceMap);
        //默认数据源(更改默认数据源为测试服）
//        dynamicDataSource.setDefaultTargetDataSource(sourceMap.get(DataSourceEnum.TJJNFY.getKey()));
        String sourceByFydm = DataSourceEnum.getSourceByFydm(SDPT_FYDM);
        dynamicDataSource.setDefaultTargetDataSource(sourceMap.get(sourceByFydm));
        return dynamicDataSource;
    }

    private EncryptDataSource generateDataSource(String key){
        EncryptDataSource dataSource
                = new EncryptDataSource();
        key = key.toLowerCase() ;
        String url = "jdbc.url."+key;
        String username = "jdbc.username."+key;
        String password = "jdbc.password."+key;
        String driverClassName = "jdbc.driverClassName."+key;
//        String driverClassName = "jdbc.driverClassName."+key;
        dataSource.setDriverClassName(env.getProperty(driverClassName));
//        dataSource.setDriverClassName(env.getProperty(driverClassName));
        dataSource.setUrl(env.getProperty(url));
        dataSource.setUsername(env.getProperty(username));
        dataSource.setPassword(env.getProperty(password));

        //配置连接池
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        dataSource.setMaxIdle(Integer.parseInt(env.getProperty("jdbc.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
//        dataSource.setTimeBetweenEvictionRunsMillis(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis"));
        return dataSource;
    }

    private DruidDataSource getDruidDataSource(String key){
        DruidDataSource dataSource=new DruidDataSource();
        key = key.toLowerCase();
        String url = "jdbc.url."+key;
        String username = "jdbc.username."+key;
        String password = "jdbc.password."+key;
        String driverClassName = "jdbc.driverClassName."+key;
        dataSource.setDriverClassName(env.getProperty(driverClassName));
        dataSource.setUrl(env.getProperty(url));
        dataSource.setUsername(env.getProperty(username));
        dataSource.setPassword(encryptPassword(env.getProperty(password)));

        //配置连接池
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.maxActive")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("spring.datasource.maxWait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("spring.datasource.maxPoolPreparedStatementPerconnectionSize")));
        dataSource.setUseGlobalDataSourceStat(true);
        try {
            dataSource.setFilters(env.getProperty("spring.datasource.filters"));
//            Properties prop=new Properties();
//            prop.load(new StringReader(env.getProperty("spring.datasource.connectionProperties")));
//            dataSource.setConnectProperties(prop);
        } catch (SQLException  e) {
            e.printStackTrace();
        }

        return dataSource;

    }

    /**
     * 加密数据库密码的工具
     * @param password
     * @return
     */
    private String encryptPassword(String password){

        String result = password;
        if(StringUtil.indexOf(password, ",")<0){
            return result;
        }
        String[] separate=password.split(",");
        String fydm=separate[0];
        String xlh=separate[1];
        if (StringUtil.equals(fydm, "000000")){
            result = xlh;
        }else{
            getpasswd oo=new getpasswd();
            result=oo.passwd(fydm,xlh);
        }
        return result;
    }

    // 配置Druid的监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams=new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams=new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
