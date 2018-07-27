package com.liumapp.recognize.core.match;

import com.baidu.aip.face.AipFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file MatchUtil.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/27
 */
@Component
public class MatchUtil {

    @Autowired
    private AipFace client;

    public void match () {
        
    }

}
