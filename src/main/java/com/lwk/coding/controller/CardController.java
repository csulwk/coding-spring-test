package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.MetricsMonitor;
import com.lwk.coding.annotation.RequestMonitor;
import com.lwk.coding.entity.req.CardReq;
import com.lwk.coding.service.ICardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author kai
 * @date 2019-12-29 22:10
 */
@Api("测试接口")
@RequestMapping(value = "/v1")
@RestController
@Slf4j
public class CardController {

    @Autowired
    private ICardService cardService;

    // 用在请求的方法上，说明方法的用途、作用
    @ApiOperation("获取卡片信息")
    // 用在请求的方法上，表示一组参数说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "operType", value = "查询类型", defaultValue = "OP001", required = true),
            @ApiImplicitParam(name = "cardNumber", value = "卡号", defaultValue = "123456", required = true)
    })
    @RequestMonitor(desc = "请求查询卡片信息")
    @MetricsMonitor(desc = "CRADTEST")
    @PostMapping("/card")
    public JSONObject queryCardInfo(@Valid @RequestBody CardReq req) {
        log.info("测试接口, requestData:{}", JSONObject.toJSONString(req));

        return cardService.queryCardInfo(req);
    }
}
