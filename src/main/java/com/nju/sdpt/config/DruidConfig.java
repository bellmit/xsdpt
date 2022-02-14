package com.nju.sdpt.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.nju.sdpt.data.dynamicdDatabases.EncryptDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiaweiq
 * @date 2021/9/12 14:23
 */
//@Configuration
public class DruidConfig {
    @Autowired
    private Environment env ;

    @Value("${SDPT.FYDM}")
    String SDPT_FYDM;

//    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
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
        return dataSource;
    }
}
