package com.lwk.coding.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author kai
 * @date 2020-01-02 22:30
 */
@Configuration
@Slf4j
public class DBConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
        log.info("url:{}, user:{}, pwd:{}", dbUrl, dbUsername, dbPassword);
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(dbDriverClassName);
        builder.url(dbUrl);
        builder.username(dbUsername);
        builder.password(dbPassword);
        return builder.build();
    }

}
