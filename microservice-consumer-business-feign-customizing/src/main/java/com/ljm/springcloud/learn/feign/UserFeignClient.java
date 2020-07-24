package com.ljm.springcloud.learn.feign;

import com.ljm.springcloud.config.FeignConfiguration;
import com.ljm.springcloud.learn.entury.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author liaojiamin
 * @Date:Created in 14:17 2020/7/7
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
    @RequestLine(value = "GET /{id}")
    User findById(@Param("id") Long id);
}
//@FeignClient(name = "microservice-provider-user")
//public interface UserFeignClient {
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public User findById(@PathVariable("id") Long id);
//}

