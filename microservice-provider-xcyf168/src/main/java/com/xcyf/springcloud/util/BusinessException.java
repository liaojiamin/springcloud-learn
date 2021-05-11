package com.xcyf.springcloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaojiamin
 * @Date:Created in 15:57 2021/5/11
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 3190256515490831676L;
    private String message;
    private Object[] params;
    private ErrorEntity errorEntity;
    private static volatile boolean traceStack = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    public BusinessException() {
    }

    public static void traceStack() {
        traceStack = true;
    }

    public static void traceStack(boolean trace) {
        traceStack = trace;
    }

    public synchronized Throwable fillInStackTrace() {
        return (Throwable)(traceStack ? super.fillInStackTrace() : this);
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorEntity getErrorEntity() {
        return this.errorEntity;
    }

    public Object[] getParams() {
        return this.params;
    }

    public static BusinessException error(ErrorEntity errorEntity) {
        BusinessException businessException = new BusinessException();
        if (errorEntity != null) {
            businessException.message = errorEntity.getErrorMessage();
            businessException.errorEntity = errorEntity;
        }

        LOGGER.debug(businessException.getMessage(), businessException);
        return businessException;
    }

    public static BusinessException error(ErrorEntity errorEntity, String message, Object... params) {
        BusinessException businessException = error(errorEntity);
        businessException.message = businessException.message + "," + message;
        businessException.params = params;
        return businessException;
    }
}
