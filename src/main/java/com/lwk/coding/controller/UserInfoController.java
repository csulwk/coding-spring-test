package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.constant.RetMsg;
import com.lwk.coding.entity.UserInfo;
import com.lwk.coding.service.impl.UserInfoService;
import com.lwk.coding.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kai
 * @date 2020-01-07 23:17
 */
@Api("查询用户信息")
@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("根据姓名查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名", required = false)
    })
    @RequestMapping(value = {"/{name}", "/"}, method = RequestMethod.GET)
    public JSONObject queryUserInfoByName(@PathVariable(value = "name", required = false) String name) {
        log.info("根据姓名查询用户信息 -> " , JSONObject.toJSONString(name));

        if (StringUtils.isEmpty(name)) {
            return ResultUtil.resp(RetMsg.RET_E101);
        }

        UserInfo user = userInfoService.getObjByName(name);

        if (user == null) {
            return ResultUtil.resp(RetMsg.RET_E102, "查询结果为空");
        }
        return ResultUtil.retSuccess(user);
    }
}
