package com.lwk.coding.metrics;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.MetricsMonitor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 异步调用处理
 * @author kai
 * @date 2020-03-16 15:29
 */
@Component
@Slf4j
public class MetricsStatsAsync {

    @Autowired
    private MetricsStatsSevice metricsStatsSevice;

    /**
     * 异步调用处理。
     * 当带有异步注解的方法被调用的时候，实际上是由代理类来调用的，代理类在调用时增加异步作用；
     * 然而，如果该注解方法是被同一个类中的其他方法调用的，那么该方法的调用并没有通过代理类，导致异步作用失效。
     * @param point
     * @param resp
     */
    @Async(value = "metricsExecutor")
    public void counting(ProceedingJoinPoint point, Object resp) {

        // 获取目标对象
        Object target = point.getTarget();
        if (target == null) {
            log.warn("MetricsAsync.addCallTimes() -> target is null...");
            return;
        }

        // 获取方法的签名信息：方法名和形参列表共同组成方法签名
        String methodName = point.getSignature().getName();
        Class<?> clazz = target.getClass();
        Method theMethod = null;

        // 获取目标方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                theMethod = method;
                break;
            }
        }

        // 若未匹配到同名方法，则跳过计数
        if (theMethod == null) {
            log.error("addCallTimes() -> theMethod is not found...");
            return;
        }

        // 若未匹配到自定义metrics注解，则跳过计数
        MetricsMonitor metricsMonitor = theMethod.getAnnotation(MetricsMonitor.class);
        if (metricsMonitor == null) {
            log.warn("The declared method does not match the expected annotation, so skip this counting...");
            return;
        }

        log.info("Do the counting: target -> {}, methodName -> {}", target, methodName);
        JSONObject result = JSONObject.parseObject(resp == null ? "" : resp.toString());
        metricsStatsSevice.doCounter(metricsMonitor.desc(), result);
    }

}
