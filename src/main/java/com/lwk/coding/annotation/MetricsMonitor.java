package com.lwk.coding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义指标监控
 * @author kai
 * @date 2020-03-16 10:58
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetricsMonitor {
    /**
     * 请求接口标识名称
     */
    String desc() default "";
}
