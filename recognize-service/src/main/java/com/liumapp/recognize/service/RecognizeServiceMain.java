package com.liumapp.recognize.service;

import com.liumapp.recognize.core.RecognizeCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author liumapp
 * @file RecognizeServiceMain.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/24/18
 */
@SpringBootApplication
@Import({RecognizeCore.class})
public class RecognizeServiceMain {

    public static void main (String[] args) {
        SpringApplication.run(RecognizeServiceMain.class, args);
    }

}
