package com.lwk.coding.validator;

import com.lwk.coding.annotation.Numeric;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数字校验
 * @author kai
 * @date 2019-12-29 22:13
 */
public class NumericValidator implements ConstraintValidator<Numeric, String> {

    private int length;

    @Override
    public void initialize(Numeric constraintAnnotation) {
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (length != -1 && StringUtils.length(value) != length) {
            return false;
        }
        return StringUtils.isNumeric(StringUtils.trim(value));
    }
}
