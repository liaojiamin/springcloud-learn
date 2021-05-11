package com.xcyf.springcloud.response;

import com.xcyf.springcloud.entity.XcyfUser;

/**
 * @author liaojiamin
 * @Date:Created in 15:43 2021/5/11
 */
public class LoginResponse extends BaseResponse{
    private XcyfUser userInfo;

    public XcyfUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(XcyfUser userInfo) {
        this.userInfo = userInfo;
    }
}
