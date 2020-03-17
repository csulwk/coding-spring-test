package com.lwk.coding;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kai
 * @date 2019-12-01 11:19
 */
@SpringBootApplication
@EnableCircuitBreaker
@ComponentScan("com.lwk")
@MapperScan("com.lwk.coding.mapper")
@EnableAsync
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("Start Running Application...");

        SpringApplication.run(Application.class, args);
    }
}
