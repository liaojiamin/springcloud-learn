package com.xcyf.springcloud.controller;

import com.xcyf.springcloud.request.LoginRequest;
import com.xcyf.springcloud.request.RegisterRequest;
import com.xcyf.springcloud.response.LoginResponse;
import com.xcyf.springcloud.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaojiamin
 * @Date:Created in 15:37 2021/5/11
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login")
    public LoginResponse login(LoginRequest request){
        return loginService.login(request);
    }

    @RequestMapping("/register")
    public Boolean register(RegisterRequest request){
        return loginService.register(request);
    }
}
