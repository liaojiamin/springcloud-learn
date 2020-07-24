package com.ljm.springcloud.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaojiamin
 * @Date:Created in 14:45 2020/7/7
 */
@Configuration
public class FeignConfiguration {
    /**
     * 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了。
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
}
