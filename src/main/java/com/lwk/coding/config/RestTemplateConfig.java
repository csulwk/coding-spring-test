package com.lwk.coding.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * REST服务参数配置
 * @author kai
 * @date 2020-03-01 17:09
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Value("${rest.timeout.connect:8000}")
    private int restConnectTime;

    @Value("${rest.timeout.read:6000}")
    private int restReadTime;

    @Bean
    public RestTemplate getRestTemplate(){
        log.info("REST超时设置：ConnectTimeout -> {}, ReadTime -> {}", restConnectTime, restReadTime);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(restConnectTime);
        factory.setReadTimeout(restReadTime);
        return new RestTemplate(factory);
    }

}
