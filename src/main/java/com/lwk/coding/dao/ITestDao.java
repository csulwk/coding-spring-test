package com.lwk.coding.dao;

import com.lwk.coding.entity.CardInfoEntity;
import com.lwk.coding.entity.req.TestReq;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kai
 * @date 2019-12-29 22:10
 */
@Repository("testDao")
public interface ITestDao {
    /**
     * 分页查询
     * @param req
     * @return
     */
    List<CardInfoEntity> queryCardInfo(TestReq req);
}
