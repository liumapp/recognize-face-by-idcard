package com.liumapp.recognize.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liumapp
 * @file CorsConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/25/18
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }
}
