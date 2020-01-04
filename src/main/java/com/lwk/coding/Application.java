package com.lwk.coding;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kai
 * @date 2019-12-01 11:19
 */
@SpringBootApplication
@ComponentScan("com.lwk")
@MapperScan("com.lwk.coding.mapper")
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("run application...");

        SpringApplication.run(Application.class, args);
    }
}
