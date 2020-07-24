package com.ljm.springcloud.learn.feign;

import com.ljm.springcloud.learn.entury.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liaojiamin
 * @Date:Created in 14:17 2020/7/7
 */
@FeignClient(name = "microservice-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 * The fallback factory must produce instances of fallback classes that
 * implement the interface annotated by {@link FeignClient}.
 *
 * @author: liaojiamin
 * @description:a
 * @dae: 11:25 2020/7/8
 */
@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger logger = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                logger.info("fallback; reason was:{}", throwable);
                User user = new User();
                user.setId(-1L);
                user.setName("default user");
                return user;
            }
        };
    }
}

