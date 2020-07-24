package com.ljm.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaojiamin
 * @Date:Created in 10:27 2020/7/14
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String hello() {
        return this.profile;
    }
}

