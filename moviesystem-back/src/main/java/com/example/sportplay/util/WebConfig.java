package com.example.sportplay.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 解决跨域问题
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")//项目中的所有接口都支持跨域
            //    .allowedOrigins("http://localhost:8080", "null")//所有地址都可以访问，也可以配置具体地址
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")//"GET", "POST", "PUT", "DELETE", "OPTIONS"
                .maxAge(3600)// 跨域允许时间
                .allowCredentials(true)
                .allowedHeaders("*");
    }

}