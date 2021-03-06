package com.lwk.coding.util;

import com.lwk.coding.Application;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kai
 * @date 2020-01-02 20:59
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JasyptTest {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void encrypt() {
        log.info("encrypt: {}", encryptor.encrypt("pw"));
    }

    @Test
    public void decrypt() {
        log.info("decrypt: {}" ,encryptor.decrypt("pw"));
    }
}
