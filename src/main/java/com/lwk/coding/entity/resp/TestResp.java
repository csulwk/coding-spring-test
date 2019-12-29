package com.lwk.coding.entity.resp;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author kai
 * @date 2019-12-02 2:13
 */
public class TestResp implements Serializable {
    private String ciId;
    private String ciCardNumber;

    public String getCiId() {
        return ciId;
    }

    public void setCiId(String ciId) {
        this.ciId = ciId;
    }

    public String getCiCardNumber() {
        return ciCardNumber;
    }

    public void setCiCardNumber(String ciCardNumber) {
        this.ciCardNumber = ciCardNumber;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
