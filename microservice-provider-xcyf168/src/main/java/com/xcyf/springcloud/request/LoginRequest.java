package com.xcyf.springcloud.request;

import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 15:38 2021/5/11
 */
public class LoginRequest implements Serializable {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
