package com.lwk.coding.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.constant.RetMsg;
import com.lwk.coding.entity.CardInfoEntity;
import com.lwk.coding.entity.req.CardReq;
import com.lwk.coding.entity.resp.CardResp;
import com.lwk.coding.dao.ICardDao;
import com.lwk.coding.service.ICardService;
import com.lwk.coding.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kai
 * @date 2019-12-29 22:13
 */
@Service("cardService")
@Slf4j
public class CardServiceImpl implements ICardService {

    @Autowired
    private ICardDao cardDao;

    @Override
    public JSONObject queryCardInfo(CardReq req) {
        List<CardInfoEntity> list = cardDao.queryCardInfo(req);
         if (list == null || list.isEmpty()) {
            log.info("queryCardInfo is empty");
            return ResultUtil.resp(RetMsg.RET_E103);
        }

        List<CardResp> result = list.stream().map(e -> {
            return assemblyCardInfo(e);
        }).collect(Collectors.toList());
        return ResultUtil.resp(RetMsg.SUCCESS, list);
    }

    private CardResp assemblyCardInfo(CardInfoEntity ciEntity) {
        CardResp resp = new CardResp();
        resp.setCiId(ciEntity.getCiId());
        resp.setCiCardNumber(ciEntity.getCiCardNumber());
        return resp;
    }
}
