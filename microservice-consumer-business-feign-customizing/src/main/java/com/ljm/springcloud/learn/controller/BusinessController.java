package com.ljm.springcloud.learn.controller;

import com.ljm.springcloud.learn.entury.User;
import com.ljm.springcloud.learn.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaojiamin
 * @Date:Created in 15:02 2020/7/6
 */
@RestController
public class BusinessController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

}
