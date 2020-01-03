package com.lwk.coding;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kai
 * @date 2019-12-01 11:19
 */
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.lwk")
@MapperScan("com.lwk.coding.mapper")
@EnableAsync
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("run application...");

        SpringApplication.run(Application.class, args);
    }
}
