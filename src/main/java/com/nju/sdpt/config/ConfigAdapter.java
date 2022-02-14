package com.nju.sdpt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
class ConfigAdapter implements WebMvcConfigurer {

    private static final String[] STATIC_RESOURCES_URLS = {"/css/**","/assets/**","/dist/**","/fonts/**","/images/**","/js/**","/static/**","/dzsd/zgysdLoginforFz","/downloadWs.do","/img/**","/bootstrap/**","/antd/**"};

    @Resource
    AccessIntrcepter accessIntrcepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /**  对所有的访问拦截
        registry.addInterceptor(accessIntrcepter)
                .addPathPatterns("/**").excludePathPatterns(Arrays.asList(STATIC_RESOURCES_URLS));
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }
}