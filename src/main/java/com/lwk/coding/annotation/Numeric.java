package com.lwk.coding.annotation;

import com.lwk.coding.validator.NumericValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数字校验自定义注解
 * @author kai
 * @date 2019-12-29 22:13
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NumericValidator.class})
public @interface Numeric {
    int length() default -1;
    String message() default "必须为数字";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
