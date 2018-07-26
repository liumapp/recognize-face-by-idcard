package com.liumapp.recognize.core.job;

import org.json.JSONObject;

/**
 * @author liumapp
 * @file JobDetail.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/26
 */
public abstract class JobDetail<T extends JobData> {

    protected JSONObject jobResult;

    public JobDetail() {
        this.jobResult = new JSONObject();
    }

    public abstract JSONObject handle(T data);

}
