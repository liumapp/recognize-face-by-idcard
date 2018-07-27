package com.liumapp.recognize.core.idcard;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author liumapp
 * @file IdCardUtil.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/27
 */
@Component
public class IdCardUtil {

    private String base64Image;

    @Resource(name = "baduAipFace")
    private AipFace aipFace;

    public String isIdCard () {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "1");
        options.put("face_type", "IDCARD");
        String imageType = "BASE64";

        // 人脸检测
        JSONObject res = aipFace.detect(base64Image, imageType, options);
        return res.toString();
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
