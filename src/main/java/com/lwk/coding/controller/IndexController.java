package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */
@Api("功能测试接口")
@RequestMapping(value = "/t")
@RestController
@Slf4j
public class IndexController {

    @ApiOperation("测试接口")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public JSONObject index() {
        log.info("IndexController -> {}", "测试接口");
        return ResultUtil.retSuccess();
    }
}
