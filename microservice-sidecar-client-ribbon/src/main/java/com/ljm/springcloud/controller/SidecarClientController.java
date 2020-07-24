package com.ljm.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liaojiamin
 * @Date:Created in 9:55 2020/7/10
 */
@RestController
public class SidecarClientController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String findById(){
        // 将会请求到：http://localhost:8060/，返回结果：{"index":"欢迎来到首页"}
        return this.restTemplate.getForObject("http://microservice-sidecar-node-service/", String.class);
    }
}
