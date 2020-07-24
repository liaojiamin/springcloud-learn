package com.ljm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author liaojiamin
 * @Date:Created in 14:54 2020/7/8
 */
@SpringBootApplication
@EnableTurbineStream
public class TurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}

