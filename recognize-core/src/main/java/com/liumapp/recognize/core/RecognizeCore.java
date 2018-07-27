package com.liumapp.recognize.core;

import com.liumapp.recognize.core.config.BaiduApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liumapp
 * @file RecognizeCore.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/26
 */
@Configuration
@Import({BaiduApiConfig.class})
public class RecognizeCore {

}
