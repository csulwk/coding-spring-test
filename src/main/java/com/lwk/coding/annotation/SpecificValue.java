package com.lwk.coding.annotation;

/**
 * @author kai
 * @date 2019-12-29 22:13
 */

import com.lwk.coding.validator.SpecificValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SpecificValueValidator.class})
public @interface SpecificValue {
    String[] value();
    String message() default "不合法的参数值";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
