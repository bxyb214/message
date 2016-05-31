package com.cmbc.most.config;

import com.cmbc.most.async.ExceptionHandlingAsyncTaskExecutor;
import lombok.Data;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by Yan on 16/5/31.
 */
@Configuration
@EnableConfigurationProperties(AsyncConfig.AsyncProperties.class)
@EnableAsync
@EnableScheduling
@lombok.extern.slf4j.Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Inject
    AsyncConfig.AsyncProperties properties;

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        log.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix("message-Executor-");
        return new ExceptionHandlingAsyncTaskExecutor(executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @ConfigurationProperties(prefix = "cmbc.most.async")
    @Data
    public static class AsyncProperties {
        int corePoolSize;
        int MaxPoolSize;
        int QueueCapacity;
    }
}