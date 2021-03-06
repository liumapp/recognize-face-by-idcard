package com.liumapp.recognize.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author liumapp
 * @file FileManager.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/25/18
 */
@Component
public class FileManager implements InitializingBean {

    private String fileName;

    private String savePath;

    private Logger logger = LoggerFactory.getLogger(FileManager.class);

    private String baseStr;

    public void save (MultipartFile file) throws IOException {
        this.fileName = file.getOriginalFilename();
        File destFile = new File(savePath + file.getOriginalFilename());
        file.transferTo(destFile);
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public MultipartFile base64toMultipart (String base64) {
        Base64File base64File = this.base64File(base64);
        return base64File;
    }

    public Base64File base64File (String base64) {
        try {
            String[] baseStr = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            this.baseStr = baseStr[1];
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStr[1]);

            for (int i = 0 ; i < b.length ; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64File(b, baseStr[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * export file on browser
     * @return response entity
     */
    public ResponseEntity<FileSystemResource> exportDownloadFile (File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.checkFolder();
    }

    /**
     * 检查目录是否存在
     */
    private void checkFolder () throws Exception {
        File file = new File(this.savePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                logger.info("fileManager initialized");
            } else {
                logger.error("fileManager require a save path , which must be a folder!");
                throw new Exception("file manager require a save path , which must be a folder");
            }
        } else {
            logger.info("fileManager initialized with a folder named : " + this.savePath);
            file.mkdir();
        }
    }

    public String getBaseStr() {
        return baseStr;
    }
}
