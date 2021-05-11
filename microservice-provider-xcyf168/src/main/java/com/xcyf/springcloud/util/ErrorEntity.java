package com.xcyf.springcloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaojiamin
 * @Date:Created in 15:57 2021/5/11
 */
public abstract class ErrorEntity implements Cloneable {
    private String errorCode;
    private String errorMessage;
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorEntity.class);

    protected abstract String getHeadCode();

    public ErrorEntity(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getUniqErrorCode() {
        return this.getHeadCode() + this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public ErrorEntity newMessage(String errorMessage) {
        try {
            ErrorEntity errorEntity = (ErrorEntity)super.clone();
            errorEntity.errorMessage = errorMessage;
            return errorEntity;
        } catch (Exception var3) {
            LOGGER.error(var3.getMessage(), var3);
            return this;
        }
    }
}

