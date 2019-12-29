package com.lwk.coding.service;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.entity.req.TestReq;

/**
 * @author kai
 * @date 2019-12-29 22:13
 */
public interface ITestService {
    JSONObject queryCardInfo(TestReq req);
}
