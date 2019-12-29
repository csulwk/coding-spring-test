package com.lwk.coding;

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
@EnableAsync
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("run application...");

        SpringApplication.run(Application.class, args);
    }
}
