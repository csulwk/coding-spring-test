package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.RequestMonitor;
import com.lwk.coding.service.IHystrixService;
import com.lwk.coding.util.ResultUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST服务调用和熔断处理
 * @author kai
 * @date 2020-03-02 21:35
 */
@Api("熔断测试查询")
@RequestMapping(value = "/hystrix")
@RestController
@Slf4j
public class HystrixController {

    @Autowired
    private IHystrixService hystrixService;

    @RequestMonitor(desc = "熔断测试查询")
    @GetMapping("/{name}")
    public JSONObject queryHystrixController(@PathVariable(value = "name", required = true) String name) {
        log.info("熔断测试查询 -> {}" , JSONObject.toJSONString(name));
        String result = hystrixService.queryHystrix(name);
        return ResultUtil.retSuccess(result);
    }
}
