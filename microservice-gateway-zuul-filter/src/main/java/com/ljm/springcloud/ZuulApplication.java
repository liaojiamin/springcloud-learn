package com.ljm.springcloud;

import com.ljm.springcloud.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author liaojiamin
 * @Date:Created in 14:38 2020/7/9
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public PreRequestLogFilter preRequestLogFilter(){
       return new PreRequestLogFilter();
    }

}

