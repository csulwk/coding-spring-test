package com.lwk.coding.service.impl;

import com.lwk.coding.Application;
import com.lwk.coding.entity.UserInfo;
import com.lwk.coding.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author kai
 * @date 2020-01-02 0:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserInfoServiceTest {

    @Autowired
    private IUserInfoService userInfoService;

    @Test
    public void testGetByName() {
        System.setProperty ("LOG_HOME", "D:\\Workspace\\logs\\");
        String userName = "Miya";
        UserInfo userInfo = userInfoService.getObjByName(userName);
        System.out.println("====UserInfo====");
        if (userInfo != null) {
            System.out.println(userInfo.getUiId());
            System.out.println(userInfo.getUiName());
            System.out.println(userInfo.getUiAge());
        }
        System.out.println("====END====");
    }

    @Test
    public void testGetAll() {
        List<UserInfo> list = userInfoService.getAllObj();
        if (list.size() > 0) {
            list.stream().forEach(s -> {
                System.out.println(s.getUiId());
                System.out.println(s.getUiName());
                System.out.println(s.getUiAge());
            });
        }
        System.out.println("====END====");
    }

    @Test
    public void testSave() {

    }

    @Test
    public void testDeleteById() {

    }

    @Test
    public void testUpdate() {

    }

}
