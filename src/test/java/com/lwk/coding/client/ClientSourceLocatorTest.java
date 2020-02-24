package com.lwk.coding.client;

import com.lwk.coding.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.File;

/**
 * 配置中心服务测试类
 * @author kai
 * @date 2020-02-24 20:58
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ClientSourceLocatorTest {

    private final String CLIENT_FILE_NAME = "client-config-test";
    private String appPath;
    private String configPath;
    private String configPathHis;

    @Before
    public void getClientPath() {
        appPath = new ApplicationHome().toString();
        configPath = appPath + "/" + CLIENT_FILE_NAME + ".properties";
        configPathHis = appPath + "/" + CLIENT_FILE_NAME + "-his.properties";
        log.info("应用路径：{}", appPath);
    }

    @Test
    public void testGetCloudConfig() {
        log.info("测试开始执行：{}", "testGetCloudConfig()");
        File configFile = new File(configPath);
        File configFileHis = new File(configPathHis);
        deleteFiles(configFile, configFileHis);

        ConfigServicePropertySourceLocator locator = getPropertySourceLocator();
        ClientSourceLocator codingLocator = new ClientSourceLocator(locator,
                CLIENT_FILE_NAME, true, true);
        PropertySource<?> propertySource = codingLocator.locate(new StandardServletEnvironment());
        if (propertySource != null) {
            log.info("propertySource is not null...");
            Assert.assertTrue(configFile.exists());
            Assert.assertTrue(configFileHis.exists());
        }
        log.info("测试执行结束：{}", "testGetCloudConfig()");
    }

    /**
     * 定义配置中心
     * @return
     */
    private ConfigServicePropertySourceLocator getPropertySourceLocator() {
        ConfigurableEnvironment ce = new StandardServletEnvironment();
        ConfigClientProperties ccp = new ConfigClientProperties(ce);
        ccp.setProfile("docker");
        ccp.setLabel("master");
        ccp.setUri(new String[]{"http://106.12.159.160:9188/"});
        return new ConfigServicePropertySourceLocator(ccp);
    }

    /**
     * 删除指定文件
     * @param files
     */
    private void deleteFiles(File... files) {
        for ( File file : files) {
            log.info("删除文件：{}", file);
            file.delete();
        }
    }

}
