package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */
@RequestMapping(value = "/t")
@RestController
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public JSONObject index() {
        return ResultUtil.retSuccess();
    }
}
