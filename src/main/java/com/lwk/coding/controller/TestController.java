package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.RequestLog;
import com.lwk.coding.entity.req.TestReq;
import com.lwk.coding.service.ITestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author kai
 * @date 2019-12-29 22:10
 */
@Api("测试接口")
@RequestMapping(value = "/t")
@RestController
@Slf4j
public class TestController {

    @Autowired
    private ITestService testService;

    // 用在请求的方法上，说明方法的用途、作用
    @ApiOperation("获取卡片信息")
    // 用在请求的方法上，表示一组参数说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "operType", value = "查询类型", defaultValue = "OP001", required = true),
            @ApiImplicitParam(name = "cardNumber", value = "卡号", defaultValue = "123456", required = true)
    })
    @RequestLog(description = "请求查询卡片信息")
    @PostMapping("/test")
    public JSONObject queryCardInfo(@Valid @RequestBody TestReq req) {
        log.info("测试接口, requestData:{}", JSONObject.toJSONString(req));

        return testService.queryCardInfo(req);
    }
}
