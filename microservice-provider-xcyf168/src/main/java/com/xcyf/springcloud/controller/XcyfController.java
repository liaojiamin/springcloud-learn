package com.xcyf.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaojiamin
 * @Date:Created in 17:07 2021/5/10
 */
@RestController
@RequestMapping("xcyf")
public class XcyfController {

    @RequestMapping("/user")
    public String getUser(){
        return "is my user";
    }
}
