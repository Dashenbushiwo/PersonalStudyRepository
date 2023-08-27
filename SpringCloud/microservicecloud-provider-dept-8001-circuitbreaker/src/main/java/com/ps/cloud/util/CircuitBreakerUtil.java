package com.ps.cloud.util;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CircuitBreakerUtil {
    /**
     * 获取熔断器的状态
     */
    public static void getCircuitBreakerStatus(String time, CircuitBreaker circuitBreaker){
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        // Returns the failure rate in percentage.
        float failureRate = metrics.getFailureRate();
        // Returns the slow rate in percentage.
        float slowCallRate = metrics.getSlowCallRate();
        // Returns the current number of slow calls.
        int slowCalls = metrics.getNumberOfSlowCalls();
        // Returns the current number of slow successful calls.
        int slowSuccessfulCalls = metrics.getNumberOfSlowSuccessfulCalls();
        // Returns the current number of slow failed calls.
        int slowFailedCalls = metrics.getNumberOfSlowFailedCalls();
        // Returns the current number of buffered calls.
        int bufferedCalls = metrics.getNumberOfBufferedCalls();
        // Returns the current number of failed calls.
        int failedCalls = metrics.getNumberOfFailedCalls();
        // Returns the current number of successed calls.
        int successCalls = metrics.getNumberOfSuccessfulCalls();
        // Returns the current number of not permitted calls.
        long notPermittedCalls = metrics.getNumberOfNotPermittedCalls();

        log.info(time + "state=" +circuitBreaker.getState() +
                " , metrics[ failureRate=" + failureRate +
                ", bufferedCalls=" + bufferedCalls +
                ", slowCallRate=" + slowCallRate +
                ", slowCalls=" + slowCalls +
                ", slowSuccessfulCalls=" + slowSuccessfulCalls +
                ", slowFailedCalls=" + slowFailedCalls +
                ", bufferedCalls=" + bufferedCalls +
                ", failedCalls=" + failedCalls +
                ", successCalls=" + successCalls +
                ", notPermittedCalls=" + notPermittedCalls +
                " ]"
        );
    }

    /**
     * 监听熔断器事件
     */
    public static void addCircuitBreakerListener(CircuitBreaker circuitBreaker){
        circuitBreaker.getEventPublisher()
                .onSuccess(event -> log.info("服务调用成功：" + event))
                .onError(event -> log.info("服务调用失败：" + event))
                .onIgnoredError(event -> log.info("服务调用失败，但异常被忽略：" + event))
                .onReset(event -> log.info("熔断器重置：" + event))
                .onStateTransition(event -> log.info("熔断器状态改变：" + event))
                .onCallNotPermitted(event -> log.info(" 熔断器已经打开：" + event))
        ;
    }
}
