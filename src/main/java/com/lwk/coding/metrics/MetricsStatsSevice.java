package com.lwk.coding.metrics;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.entity.exception.WebException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 接口调用统计
 * @author kai
 * @date 2020-03-16 12:06
 */
@Slf4j
@Component
public class MetricsStatsSevice implements MeterBinder {

    private MeterRegistry meterRegistry;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        log.info("计量器注册表绑定成功...");
        this.meterRegistry = meterRegistry;
    }

    /**
     * 异步统计调用次数
     * @param reqId
     * @param resp
     */
    public void doCounter(String reqId, JSONObject resp) {
        if (resp == null) {
            throw new WebException("接口计数时异常");
        }
        String retCode = resp.getString("retCode");

        log.info("接口计数器: REQID -> {}, RETCODE -> {}", reqId, retCode);
        meterRegistry.counter("coding-spring-test",
                "REQID", reqId,
                "RETCODE", retCode
        ).increment();
    }
}
