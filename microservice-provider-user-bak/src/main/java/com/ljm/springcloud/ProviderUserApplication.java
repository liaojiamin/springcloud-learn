package com.ljm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liaojiamin
 * @Date:Created in 14:55 2020/7/6
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApplication.class, args);
    }
}
