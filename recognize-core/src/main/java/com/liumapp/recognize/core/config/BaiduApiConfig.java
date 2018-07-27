package com.liumapp.recognize.core.config;

import com.baidu.aip.face.AipFace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liumapp
 * @file BaiduApiConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/27
 */
@Configuration
public class BaiduApiConfig {

    @Value("${liumapp.recognize.baidu.appId}")
    private String APP_ID;

    @Value("${liumapp.recognize.baidu.appKey}")
    private String APP_KEY;

    @Value("${liumapp.recognize.baidu.appSecret}")
    private String APP_SECRET;

    @Bean
    public AipFace aipFace () {
        AipFace client = new AipFace(APP_ID, APP_KEY, APP_SECRET);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        return client;
    }

}
