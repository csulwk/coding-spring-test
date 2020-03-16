package com.lwk.coding.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author kai
 * @date 2020-03-16 11:34
 */
public interface IQueryService {
    JSONObject queryByName(String name);
}
