package com.lwk.coding.aop;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.annotation.RequestMonitor;
import com.lwk.coding.metrics.MetricsStatsAsync;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 请求信息打印切面类
 * 执行顺序：@Around->@Before->接口的逻辑代码->@After->@AfterReturning
 * @author kai
 * @date 2020-02-11 15:55
 * @refer https://www.cnblogs.com/quanxiaoha/p/10789843.html
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    private MetricsStatsAsync myMetricsAsync;

    /**
     * 以自定义注解作为切点
     */
    @Pointcut("@annotation(com.lwk.coding.annotation.RequestMonitor)")
    public void requestMonitorMethod() {}

    /**
     * 以RequestMapping注解作为切点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMappingMethod() {}

    /**
     * 环绕，可以在切点前后织入代码，并且可以自由的控制何时执行切点
     * @param proceedingJoinPoint
     * @return
     */
    @Around("requestMonitorMethod() || requestMappingMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 执行切点
        Object respObj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        // 打印响应信息
        log.info("ResponseMsg : {}", JSONObject.toJSONString(respObj));
        // 执行请求耗时
        log.info("UsedTime    : {} ms", endTime - startTime);

        // 异步统计调用次数
        myMetricsAsync.counting(proceedingJoinPoint, respObj);

        return respObj;
    }

    /**
     * 在切点之前，织入相关代码
     * @param joinPoint
     */
    @Before("requestMonitorMethod()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求信息
        String methodDescription = getRequestLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("=============");
        log.info("请求开始...");
        // 打印请求的描述信息
        log.info("RequestDesc : {}", methodDescription);
        // 打印请求地址
        log.info("RequestURL  : {}", request.getRequestURL().toString());
        // 打印请求入参
        log.info("RequestMsg  : {}", JSONObject.toJSONString(joinPoint.getArgs()));
        // 打印请求类型
        log.info("RequestType : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("RequestClass: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的IP
        log.info("RequestIP   : {}", request.getRemoteAddr());
    }

    /**
     * 获取 @RequestLog 自定义注解的描述信息
     * @param joinPoint
     * @return
     */
    private String getRequestLogDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description.append(method.getAnnotation(RequestMonitor.class).desc());
                    break;
                }
            }
        }
        return description.toString();
    }

    /**
     * 在切点之后，织入相关代码
     */
    @After("requestMonitorMethod()")
    public void doAfter() {
        log.info("请求结束...");
    }

}
