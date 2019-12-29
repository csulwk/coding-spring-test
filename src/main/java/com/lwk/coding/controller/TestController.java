package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.entity.req.TestReq;
import com.lwk.coding.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */
@RequestMapping(value = "/t")
@RestController
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public JSONObject queryCardInfo(@Valid @RequestBody TestReq req) {
        LOG.info("测试接口, requestData:{}", JSONObject.toJSONString(req));

        return testService.queryCardInfo(req);
    }
}
