package com.lwk.coding.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 客户端缓存配置项
 * @author kai
 * @date 2020-02-20 21:01
 */
@Slf4j
public class ClientConfig {

    /**
     * 是否使用远程配置中心文件覆盖本地配置文件
     */
    @Value("${coding.config.client.cover:true}")
    private boolean coverClientEnabled;
    /**
     * 当远程配置中心不可用时是否使用本地配置文件
     */
    @Value("${coding.config.client.apply:true}")
    private boolean applyClientEnabled;
    /**
     * 本地配置文件名称
     */
    private final String CLIENT_FILE_NAME = "client-config";

    @Bean
    @ConditionalOnProperty(name = "coding.config.client.enabled", havingValue = "true", matchIfMissing = true)
    public ClientSourceLocator getClientSourceLocator(ConfigServicePropertySourceLocator sourceLocator) {
        log.info("配置参数的值：configName->{}, coverClientEnabled->{}, applyClientEnabled->{}",
                CLIENT_FILE_NAME, coverClientEnabled, applyClientEnabled);
        return new ClientSourceLocator( sourceLocator, CLIENT_FILE_NAME, coverClientEnabled, applyClientEnabled);
    }
}
