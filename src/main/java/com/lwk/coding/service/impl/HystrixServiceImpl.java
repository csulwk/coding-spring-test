package com.lwk.coding.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.service.IHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author kai
 * @date 2020-03-02 21:37
 */
@Slf4j
@Service("hystrixService")
public class HystrixServiceImpl implements IHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand( commandKey = "hystrixService", fallbackMethod = "queryNone")
    @Override
    public String queryHystrix(String name) {
        String url = String.format("http://106.12.159.160:9102/user/%s", name);
        log.info("请求地址为：{}", url);
        if ("sleep".equals(name)) {
            try {
                long sleepTime = 12000;
                log.info("当前请求许等待{}ms...", sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                log.error("线程睡眠错误!", e);
            }
        }
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        log.info("响应结果为：{}", result);
        return "Request Success ~";
    }

    public String queryNone(String name) {
        log.info("服务调用熔断处理，参数为：{}", name);
        return "Request Failed !";
    }
}
