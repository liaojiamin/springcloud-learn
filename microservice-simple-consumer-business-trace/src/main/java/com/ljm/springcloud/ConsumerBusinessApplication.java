package com.ljm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author liaojiamin
 * @Date:Created in 15:00 2020/7/6
 */
@SpringBootApplication
public class ConsumerBusinessApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerBusinessApplication.class, args);
    }
}
