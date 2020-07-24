package com.ljm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liaojiamin
 * @Date:Created in 15:00 2020/7/6
 */
@EnableEurekaClient
@SpringBootApplication
public class ConsumerBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBusinessApplication.class, args);
    }
}
