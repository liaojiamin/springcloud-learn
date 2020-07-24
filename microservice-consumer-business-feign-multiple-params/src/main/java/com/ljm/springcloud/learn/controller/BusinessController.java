package com.ljm.springcloud.learn.controller;

import com.google.common.collect.Maps;
import com.ljm.springcloud.learn.entury.User;
import com.ljm.springcloud.learn.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liaojiamin
 * @Date:Created in 15:02 2020/7/6
 */
@RestController
public class BusinessController {
    private Logger logger = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    /**
     * 测试URL：http://localhost:8010/user/get0?id=1&username=张三
     * 该请求不会成功。
     * @param user user
     * @return 用户信息
     */
    @GetMapping("/user/get0")
    public User get0(User user) {
        return this.userFeignClient.get0(user);
    }

    /**
     * 测试URL：http://localhost:8010/user/get1?id=1&username=张三
     * @param user user
     * @return 用户信息
     */
    @GetMapping("/user/get1")
    public User get1(User user) {
        return this.userFeignClient.get1(user.getId(), user.getUsername());
    }

    /**
     * 测试URL：http://localhost:8010/user/get2?id=1&username=张三
     * @param user user
     * @return 用户信息
     */
    @GetMapping("/user/get2")
    public User get2(User user) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        return this.userFeignClient.get2(map);
    }

    /**
     * 测试URL:http://localhost:8010/user/post?id=1&username=张三
     * @param user user
     * @return 用户信息
     */
    @GetMapping("/user/post")
    public User post(User user) {
        return this.userFeignClient.post(user);
    }

}
