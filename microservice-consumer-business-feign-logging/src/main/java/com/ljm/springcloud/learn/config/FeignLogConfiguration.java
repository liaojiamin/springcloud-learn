package com.ljm.springcloud.learn.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaojiamin
 * @Date:Created in 17:17 2020/7/7
 */
@Configuration
public class FeignLogConfiguration {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
