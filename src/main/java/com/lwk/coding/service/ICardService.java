package com.lwk.coding.service;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.entity.req.CardReq;

/**
 * @author kai
 * @date 2019-12-29 22:13
 */
public interface ICardService {
    JSONObject queryCardInfo(CardReq req);
}
