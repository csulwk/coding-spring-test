package com.lwk.coding.entity.req;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.Numeric;
import com.lwk.coding.annotation.SpecificValue;
import com.lwk.coding.constant.CodeSet;
import com.lwk.coding.constant.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 请求参数
 * @author kai
 * @date 2019-12-02 0:56
 */
@ApiModel
public class CardReq implements Serializable {

    /**
     * 查询类型
     */
    @ApiModelProperty("查询类型")
    @SpecificValue(value = {Constants.OPER_TYPE_OP001,Constants.OPER_TYPE_OP002}, message = CodeSet.CODE_E101)
    @NotBlank(message = CodeSet.CODE_E101)
    private String operType;

    /**
     * 卡号
     */
    @ApiModelProperty("卡号")
    @Numeric(message = CodeSet.CODE_E102)
    @NotBlank(message = CodeSet.CODE_E102)
    @Length(message = CodeSet.CODE_E102, min = 6)
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
