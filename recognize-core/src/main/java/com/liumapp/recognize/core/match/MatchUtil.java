package com.liumapp.recognize.core.match;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author liumapp
 * @file MatchUtil.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/27
 */
@Component
public class MatchUtil {

    private String base64Image1;

    private String base64Image2;

    @Resource(name = "baduAipFace")
    private AipFace client;

    public JSONObject match () {
        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应
        MatchRequest req1 = new MatchRequest(base64Image1, "BASE64");
        MatchRequest req2 = new MatchRequest(base64Image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);
        JSONObject res = client.match(requests);
        return res;
    }

    public String getBase64Image1() {
        return base64Image1;
    }

    public void setBase64Image1(String base64Image1) {
        this.base64Image1 = base64Image1;
    }

    public String getBase64Image2() {
        return base64Image2;
    }

    public void setBase64Image2(String base64Image2) {
        this.base64Image2 = base64Image2;
    }
}
