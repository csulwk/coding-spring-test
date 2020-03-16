package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.MetricsMonitor;
import com.lwk.coding.annotation.RequestMonitor;
import com.lwk.coding.service.IQueryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai
 * @date 2020-03-16 11:07
 */
@Api("指标监控测试接口")
@RequestMapping(value = "/metrics")
@RestController
@Slf4j
public class MetricsController {

    @Autowired
    private IQueryService metricsService;

    @MetricsMonitor(desc = "METRICSTEST")
    @RequestMonitor(desc = "请求指标监控测试")
    @GetMapping("/{name}")
    public JSONObject queryMetrics(@PathVariable(value = "name", required = true) String name) {
        log.info("指标监控测试 -> {}" , JSONObject.toJSONString(name));
        return metricsService.queryByName(name);
    }
}
