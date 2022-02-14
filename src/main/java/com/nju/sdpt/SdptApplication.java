package com.nju.sdpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableScheduling
@MapperScan("com.nju.sdpt.mapper")
@SpringBootApplication
@EnableCaching
@EnableAsync
public class SdptApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SdptApplication.class, args);
    }

//    @Bean
//    public FilterRegistrationBean httpFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new HttpFilter());
//        registrationBean.addUrlPatterns("/threadLocal/*");
//        return registrationBean;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
//    }
//
//    @Autowired
//    AccessIntrcepter accessIntrcepter;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(accessIntrcepter).addPathPatterns("/**");
//    }


}
