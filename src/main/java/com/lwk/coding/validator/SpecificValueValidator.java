package com.lwk.coding.validator;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.SpecificValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 特定值校验
 * @author kai
 * @date 2019-12-29 22:13
 */
public class SpecificValueValidator implements ConstraintValidator<SpecificValue, String> {

    private String[] values;

    @Override
    public void initialize(SpecificValue constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }

        for (String val : values) {
            if (val.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
