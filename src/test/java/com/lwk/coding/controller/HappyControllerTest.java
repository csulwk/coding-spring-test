package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.Application;
import com.lwk.coding.entity.UserInfo;
import com.lwk.coding.entity.req.HappyReq;
import com.lwk.coding.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
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
public class HappyControllerTest {

    @Autowired
    private HappyController happyController;

    @Test
    public void testSelect() {
        JSONObject result = happyController.selectMessage();
        log.info("result: {}", result);
    }

    @Test
    public void testInsert() {
        HappyReq req = new HappyReq();
        req.setName("abc");
        req.setMessage("hello");

        JSONObject result = happyController.insertMessage(req);
        log.info("result: {}", result);
    }

}
