package com.xcyf.springcloud.service;

import com.xcyf.springcloud.request.LoginRequest;
import com.xcyf.springcloud.request.RegisterRequest;
import com.xcyf.springcloud.response.LoginResponse;

/**
 * @author liaojiamin
 * @Date:Created in 15:39 2021/5/11
 */
public interface ILoginService {
    LoginResponse login(LoginRequest request);
    Boolean register(RegisterRequest request);
}
