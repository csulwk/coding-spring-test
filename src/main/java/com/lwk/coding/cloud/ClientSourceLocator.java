package com.lwk.coding.cloud;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * 自定义配置源，用于读取远程配置
 * @author kai
 * @date 2020-02-20 21:46
 */
@Slf4j
@Getter
@Order(Ordered.LOWEST_PRECEDENCE)
public class ClientSourceLocator implements PropertySourceLocator {

    /**
     * 本地配置文件版本
     */
    private final String CLIENT_VERSION_NAME = "config.client.version";
    /**
     * 本地配置最新文件
     */
    private String configPath;
    /**
     * 本地配置历史文件
     */
    private String configPathHis;
    /**
     * 是否使用远程配置中心文件覆盖本地配置文件
     */
    private boolean coverClientEnabled;
    /**
     * 当远程配置中心不可用时是否使用本地配置文件
     */
    private boolean applyClientEnabled;

    private ConfigServicePropertySourceLocator remoteSourceLocator;

    public ClientSourceLocator(ConfigServicePropertySourceLocator remoteSourceLocator, String configName,
                               boolean coverClientEnabled, boolean applyClientEnabled) {
        String appPath = new ApplicationHome().toString();
        this.remoteSourceLocator = remoteSourceLocator;
        this.configPath = appPath + "/" + configName + ".properties";
        this.configPathHis = appPath + "/" + configName + "-his.properties";
        this.coverClientEnabled = coverClientEnabled;
        this.applyClientEnabled = applyClientEnabled;
    }

    @Override
    public PropertySource<?> locate(Environment environment) {

        log.info("当前执行：{}.{}", "CodingSourceLocator", "locate()");

        PropertySource<?> clientSource = null;
        // 远程配置
        PropertySource<?> serverSource = remoteSourceLocator.locate(environment);

        if (serverSource != null) {
            log.info("配置中心连接成功，校验远程配置并保存到本地文件...");
            Properties clientProp = loadProperties(configPath);
            if (clientProp == null || compareVersion(clientProp, serverSource)) {
                downloadServerProperties(serverSource);
            }
        } else if (applyClientEnabled) {
            log.info("配置中心连接失败，读取本地文件中的配置项...");
            // 获取本地文件的配置源
            clientSource = getPropertySource(configPath);
        }
        log.info("结束执行：{}.{}", "CodingSourceLocator", "locate()");
        return clientSource;
    }

    /**
     * 加载本地配置文件
     * @param path 配置文件路径
     * @return 配置项
     */
    private Properties loadProperties(String path) {
        log.info("当前执行：{}.{}", "CodingSourceLocator", "loadProperties()");
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path)) {
            prop.load(inputStream);
            return prop;
        } catch (FileNotFoundException e) {
            log.info("加载的本地配置文件不存在", e);
            return null;
        } catch (IOException e) {
            log.info("加载本地配置文件时异常", e);
            return null;
        }
    }

    /**
     * 比较本地和远程配置文件的版本
     * @param clientProp 本地配置项
     * @param serverSource 远程配置源
     * @return 当版本不一致时返回true则需下载远程配置到本地；当版本一致时返回false则无需重新下载
     */
    private boolean compareVersion(Properties clientProp, PropertySource<?> serverSource) {
        log.info("当前执行：{}.{}", "CodingSourceLocator", "compareVersion()");

        String serverVersion = (String) serverSource.getProperty(CLIENT_VERSION_NAME);
        String clientVersion = clientProp.getProperty(CLIENT_VERSION_NAME);
        log.info("比较本地和远程配置源的版本：client->{}, server->{}", clientVersion, serverVersion);
        if (serverVersion == null) {
            log.info("未获取到远程配置源的版本...");
            return true;
        }
        if (!serverVersion.equals(clientVersion)) {
            log.info("本地和远程的版本不一致...");
            return true;
        } else {
            log.info("本地和远程的版本一致...");
            return false;
        }
    }

    /**
     * 获取本地文件的配置源
     * @param path 配置文件路径
     * @return 本地配置源
     */
    private PropertySource<?> getPropertySource(String path) {
        log.info("当前执行：{}.{}", "CodingSourceLocator", "getPropertySource()");
        log.info("获取本地配置源: path = {}", path);
        Properties prop = loadProperties(path);
        if (prop == null || prop.isEmpty()) {
            log.info("获取本地配置源失败...");
            return null;
        }
        return new PropertiesPropertySource(path, prop);
    }

    /**
     * 获取远程配置项，将本地配置备份保留
     * @param serverSource 远程配置源
     */
    private void downloadServerProperties(PropertySource<?> serverSource) {
        log.info("当前执行：{}.{}", "CodingSourceLocator", "downloadServerProperties");

        if (!coverClientEnabled) {
            log.info("当前设置为不覆盖本地配置：coverClientEnabled = {}", coverClientEnabled);
            return;
        }

        Properties serverProp = new Properties();
        String configVersion = (String) serverSource.getProperty(CLIENT_VERSION_NAME);
        EnumerablePropertySource<?> enums = (EnumerablePropertySource<?>) serverSource;
        String[] propNames = enums.getPropertyNames();
        for (String propName: propNames) {
            log.info("配置项{} : {}", propName, enums.getProperty(propName).toString());
            serverProp.setProperty(propName, enums.getProperty(propName).toString());
        }

        File curFile = new File(configPath);
        File hisFile = new File(configPathHis);
        if (curFile.exists()) {
            hisFile.delete();
            try {
                Path targetPath = Files.move(curFile.toPath(), hisFile.toPath());
                if (targetPath == null && !hisFile.delete()) {
                    return;
                }
            } catch (IOException e) {
                log.error("本地配置文件备份时异常", e);
            }
        }

        coverClientProperties(configVersion, serverProp, curFile);
    }

    /**
     * 将远程配置写入到本地配置文件中
     * @param configVersion 远程配置源版本
     * @param serverProp 远程配置项
     * @param desFile 写入目标文件
     */
    private void coverClientProperties(String configVersion, Properties serverProp, File desFile) {
        log.info("当前执行：{}.{}", "CodingSourceLocator", "coverClientProperties");
        log.info("配置项写入本地文件：name->{}, version->{}", desFile.toString(), configVersion);
        try (FileOutputStream fos = new FileOutputStream(desFile, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("============================================\n");
            sb.append("THIS IS A CLIENT FILE OF THE CONFIG SERVER ~\n");
            sb.append("#============================================");
            serverProp.setProperty(CLIENT_VERSION_NAME, configVersion == null ? "" : configVersion);
            serverProp.store(fos, sb.toString());
        } catch (FileNotFoundException e) {
            log.error("配置项写入的本地文件不存在", e);
        } catch (IOException e) {
            log.error("配置项写入本地文件时异常", e);
        }
    }

}
