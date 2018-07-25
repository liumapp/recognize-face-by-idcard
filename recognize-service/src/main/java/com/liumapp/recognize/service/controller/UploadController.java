package com.liumapp.recognize.service.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.recognize.service.entity.MultyDocEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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


    @RequestMapping("/multybase64")
    @ResponseBody
    public String multyBase64Upload (@RequestBody MultyDocEntity[] list) {
        try {
            int index = 0;
            for (MultyDocEntity doc : list) {
                MultipartFile file = fileManager.base64toMultipart(doc.getContent());
                fileManager.save(file);
                convertDocPattern.setFileIndex(index);
                convertDocPattern.setConvertId(doc.getConvertId());
                convertDocPattern.setDocPath(fileManager.getSavePath());
                convertDocPattern.setPdfPath(fileManager.getSavePath());
                convertDocPattern.setOriginalName(fileManager.getFileName());
                convertDocPattern.setSaveName(fileManager.getFileName() + ".pdf");
                convertDocPublisher.send(JSON.toJSONString(convertDocPattern));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString("success");
    }

}
