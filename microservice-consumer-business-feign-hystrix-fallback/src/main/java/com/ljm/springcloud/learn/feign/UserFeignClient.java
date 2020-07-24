package com.ljm.springcloud.learn.feign;

import com.ljm.springcloud.learn.entury.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liaojiamin
 * @Date:Created in 14:17 2020/7/7
 */
@FeignClient(name = "microservice-provider-user", fallback = FeignClientFallback.class)
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}

@Component
class FeignClientFallback implements UserFeignClient{
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setName("default user");
        return user;
    }
}
