package com.ljm.springcloud.learn.config;

import com.ljm.springcloud.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 用bean注入的方式指定 ribbon客户端名字是 microservice-provider-user
 * 的ribbon客户端的复制均衡规则按照RibbonConfiguration 指定规则进行
 * @author liaojiamin
 * @Date:Created in 10:49 2020/7/7
 */
@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)
public class TestConfiguration { }
