package com.xcyf.springcloud.response;

import com.xcyf.springcloud.entity.XcyfUser;

/**
 * @author liaojiamin
 * @Date:Created in 16:18 2021/5/11
 */
public class RegisterResponse extends BaseResponse{
    private XcyfUser user;

    public XcyfUser getUser() {
        return user;
    }

    public void setUser(XcyfUser user) {
        this.user = user;
    }
}
