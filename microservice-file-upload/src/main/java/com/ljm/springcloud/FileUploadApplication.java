package com.ljm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liaojiamin
 * @Date:Created in 15:29 2020/7/9
 */
@SpringBootApplication
@EnableEurekaClient
public class FileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class,args);
    }
}
