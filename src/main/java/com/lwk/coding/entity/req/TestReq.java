package com.lwk.coding.entity.req;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.Numeric;
import com.lwk.coding.annotation.SpecificValue;
import com.lwk.coding.constant.CodeSet;
import com.lwk.coding.constant.Constants;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 请求参数
 * @author kai
 * @date 2019-12-02 0:56
 */
public class TestReq implements Serializable {

    /**
     * 查询类型
     */
    @SpecificValue(value = {Constants.OPER_TYPE_OP001,Constants.OPER_TYPE_OP002}, message = CodeSet.CODE_E101)
    @NotBlank(message = CodeSet.CODE_E101)
    private String operType;

    /**
     * 卡号
     */
    @Numeric(message = CodeSet.CODE_E102)
    @NotBlank(message = CodeSet.CODE_E102)
    private String cardNumber;

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
