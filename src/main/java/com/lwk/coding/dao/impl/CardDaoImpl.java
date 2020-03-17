package com.lwk.coding.dao.impl;

import com.lwk.coding.entity.CardInfoEntity;
import com.lwk.coding.entity.req.CardReq;
import com.lwk.coding.dao.ICardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */
@Repository("cardDao")
@Slf4j
public class CardDaoImpl implements ICardDao {

    @Override
    public List<CardInfoEntity> queryCardInfo(CardReq req) {
        log.info("查询数据库：{}", req);
        return null;
    }
}
