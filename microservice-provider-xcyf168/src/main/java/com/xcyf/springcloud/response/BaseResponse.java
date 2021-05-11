package com.xcyf.springcloud.response;

import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 15:43 2021/5/11
 */
public class BaseResponse implements Serializable {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
