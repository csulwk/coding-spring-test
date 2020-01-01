package com.lwk.coding.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.constant.RetMsg;
import com.lwk.coding.entity.CardInfoEntity;
import com.lwk.coding.entity.req.TestReq;
import com.lwk.coding.entity.resp.TestResp;
import com.lwk.coding.dao.ITestDao;
import com.lwk.coding.service.ITestService;
import com.lwk.coding.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kai
 * @date 2019-12-29 22:13
 */
@Service("testService")
public class TestServiceImpl implements ITestService {
    private static final Logger LOG = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private ITestDao testDao;

    @Override
    public JSONObject queryCardInfo(TestReq req) {
        List<CardInfoEntity> list = testDao.queryCardInfo(req);
        if (list.isEmpty()) {
            LOG.info("queryCardInfo is empty");
            return ResultUtil.resp(RetMsg.RET_E103);
        }

        List<TestResp> result = list.stream().map(e -> {
            return assemblyCardInfo(e);
        }).collect(Collectors.toList());
        return ResultUtil.resp(RetMsg.SUCCESS, list);
    }

    private TestResp assemblyCardInfo(CardInfoEntity ciEntity) {
        TestResp resp = new TestResp();
        resp.setCiId(ciEntity.getCiId());
        resp.setCiCardNumber(ciEntity.getCiCardNumber());
        return resp;
    }
}
