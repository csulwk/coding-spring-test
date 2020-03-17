package com.lwk.coding.dao;

import com.lwk.coding.entity.CardInfoEntity;
import com.lwk.coding.entity.req.CardReq;

import java.util.List;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */

public interface ICardDao {
    /**
     * 分页查询
     * @param req
     * @return
     */
    List<CardInfoEntity> queryCardInfo(CardReq req);
}
