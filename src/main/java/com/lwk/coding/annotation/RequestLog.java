package com.lwk.coding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求日志自定义注解
 * @author kai
 * @date 2020-02-11 15:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RequestLog {
    /**
     * 请求描述信息
     * @return
     */
    String description() default "";
}
