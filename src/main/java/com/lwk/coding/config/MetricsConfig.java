package com.lwk.coding.config;

import com.lwk.coding.metrics.MDCTaskExecutor;
import org.slf4j.MDC;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author kai
 * @date 2020-03-16 10:51
 */
@Configuration
public class MetricsConfig {

    @Autowired
    private BeanFactory beanFactory;

    @Bean("metricsExecutor")
    public Executor getExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new MDCTaskExecutor();
        // 设置核心线程数
        poolTaskExecutor.setCorePoolSize(5);
        // 设置最大线程数
        poolTaskExecutor.setMaxPoolSize(20);
        // 设置队列容量
        poolTaskExecutor.setQueueCapacity(50);
        // 设置线程活跃时间（秒）
        poolTaskExecutor.setKeepAliveSeconds(100);
        // 设置线程名称的前缀标识
        poolTaskExecutor.setThreadNamePrefix("MetricsExecutor-");
        // 设置拒绝策略
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        poolTaskExecutor.initialize();
        return new LazyTraceExecutor(beanFactory, poolTaskExecutor);
    }

}
