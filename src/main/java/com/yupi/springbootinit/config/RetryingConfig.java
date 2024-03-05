package com.yupi.springbootinit.config;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetryingConfig {

    @Bean
    public Retryer<Boolean> retryer(){
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(result -> !result)
                .retryIfException(e -> true)    // 任何异常都会触发重试
                .withWaitStrategy(WaitStrategies.fixedWait(1000, TimeUnit.MILLISECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))   // 允许最多重试3次
                .build();

        return retryer;
    }
}
