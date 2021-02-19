package com.lwk.coding.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.constant.RetMsg;
import com.lwk.coding.entity.HappyMessage;
import com.lwk.coding.entity.req.HappyReq;
import com.lwk.coding.mapper.IHappyMessageMapper;
import com.lwk.coding.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kai
 * @date 2020-01-07 23:17
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/happy")
@RestController
@Slf4j
public class HappyController {

    @Autowired
    private IHappyMessageMapper happyMessageMapper;

    @PostMapping("/insert")
    public JSONObject insertMessage(@Valid @RequestBody HappyReq req) {
        log.info("新增消息 -> {}" , JSONObject.toJSONString(req));

        HappyMessage msg = new HappyMessage();
        msg.setHmName(req.getName());
        msg.setHmMessage(req.getMessage());
        msg.setUpdateBy("coding");
        int cnt = happyMessageMapper.saveMessage(msg);

        JSONObject result = ResultUtil.retSuccess();
        if (cnt < 1) {
            return ResultUtil.resp(RetMsg.ERROR, "新增消息失败");
        }
        return result;
    }

    @GetMapping("/select")
    public JSONObject selectMessage() {
        log.info("获取消息...");
        List<HappyMessage> list =  happyMessageMapper.selectMessage();
        return ResultUtil.retSuccess(list);
    }
}
