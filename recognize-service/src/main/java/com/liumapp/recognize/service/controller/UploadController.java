package com.liumapp.recognize.service.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.recognize.core.match.MatchUtil;
import com.liumapp.recognize.service.entity.MultyDocEntity;
import com.liumapp.recognize.service.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liumapp
 * @file UploadController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/24/18
 */
@RestController()
@RequestMapping("upload")
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FileManager fileManager;

    @Autowired
    private MatchUtil matchUtil;

    @RequestMapping("/multybase64")
    @ResponseBody
    public String multyBase64Upload (@RequestBody MultyDocEntity[] list) {
        String msg = "success";
        try {
            LinkedList<String> contents = new LinkedList<String>();
            // save file in local
            for (MultyDocEntity doc : list) {
                MultipartFile file = fileManager.base64toMultipart(doc.getContent());
                fileManager.save(file);
                contents.add(fileManager.getBaseStr());
            }
            // match
            matchUtil.setBase64Image1(contents.pop());
            matchUtil.setBase64Image2(contents.pop());
            org.json.JSONObject result = matchUtil.match();
            msg = result.toString();
            logger.info("get info from match util : " + result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return msg;
    }

}
