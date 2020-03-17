package com.lwk.coding.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.constant.RetMsg;
import com.lwk.coding.service.IQueryService;
import com.lwk.coding.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author kai
 * @date 2020-03-16 11:35
 */
@Service("metricsService")
@Slf4j
public class MetricsServiceImpl implements IQueryService {

    @Override
    public JSONObject queryByName(String name) {
        if ("error".equals(name)) {
            return queryFailed(name);
        }
        return querySuccess(name);
    }

    /**
     * 测试查询成功
     * @param name
     * @return
     */
    private JSONObject querySuccess(String name) {
        log.info("查询成功...");
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        return ResultUtil.retSuccess(obj);
    }

    /**
     * 测试查询失败
     * @param name
     * @return
     */
    private JSONObject queryFailed(String name) {
        log.info("查询失败...");
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        return ResultUtil.resp(RetMsg.ERROR, obj);
    }
}
