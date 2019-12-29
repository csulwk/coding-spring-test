package com.lwk.coding.dao.impl;

import com.lwk.coding.entity.CardInfoEntity;
import com.lwk.coding.entity.req.TestReq;
import com.lwk.coding.dao.ITestDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */
@Repository
public class TestDaoImpl implements ITestDao {

    @Override
    public List<CardInfoEntity> queryCardInfo(TestReq req) {
        return null;
    }
}
