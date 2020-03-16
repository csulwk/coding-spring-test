package com.lwk.coding.metrics;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 在线程池中传递Slf4J MDC内容
 * @author kai
 * @date 2020-03-16 16:53
 * @refer https://blog.csdn.net/BossHX/article/details/84987483
 */
public class MDCTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        super.execute(wrapExecute(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(wrapSubmit(task, MDC.getCopyOfContextMap()));
    }

    private Runnable wrapExecute(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceId();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

    private <T> Callable<T> wrapSubmit(Callable<T> task, final Map<String, String> context) {
        return () -> {
            Map<String, String> previous = MDC.getCopyOfContextMap();
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceId();
            try {
                return task.call();
            } finally {
                MDC.clear();
            }
        };
    }

    /**
     * 若traceId为空，则设置新值
     */
    private void setTraceId() {
        if (MDC.get("traceId") == null) {
            MDC.put("traceId", UUID.randomUUID().toString().replace("-", ""));
        }
    }
}
