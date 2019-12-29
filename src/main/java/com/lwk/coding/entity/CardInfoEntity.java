package com.lwk.coding.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @author kai
 * @date 2019-12-02 2:10
 */
public class CardInfoEntity {

    private String ciId;
    private String ciCardNumber;
    private String ciCardStatus;
    private String ciDate;

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

    public String getCiCardStatus() {
        return ciCardStatus;
    }

    public void setCiCardStatus(String ciCardStatus) {
        this.ciCardStatus = ciCardStatus;
    }

    public String getCiDate() {
        return ciDate;
    }

    public void setCiDate(String ciDate) {
        this.ciDate = ciDate;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
