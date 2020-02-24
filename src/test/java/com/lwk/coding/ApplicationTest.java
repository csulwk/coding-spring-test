package com.lwk.coding;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;

/**
 * @author kai
 * @date 2019-12-29 22:04
 */
@Slf4j
public class ApplicationTest {

    @Test
    public void test() {
        ApplicationHome app = new ApplicationHome();

        //System.out.println(app.getSource().getAbsolutePath());
        //System.out.println(app.getSource().toString());
        System.out.println(app.toString());

    }
}
