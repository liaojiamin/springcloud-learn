package com.ljm.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author liaojiamin
 * @Date:Created in 15:00 2020/7/6
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBusinessApplication.class, args);
    }
}
