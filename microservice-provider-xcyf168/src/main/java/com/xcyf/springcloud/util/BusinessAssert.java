package com.xcyf.springcloud.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

/**
 * @author liaojiamin
 * @Date:Created in 15:53 2021/5/11
 */
public class BusinessAssert {
    public BusinessAssert() {
    }

    public static void error(ErrorEntity errorEntity) {
        throw BusinessException.error(errorEntity);
    }

    public static void isNotNull(Object obj, ErrorEntity errorEntity) {
        if (obj == null) {
            throw BusinessException.error(errorEntity);
        }
    }

    public static void isNotBlank(String value, ErrorEntity errorEntity) {
        if (StringUtils.isBlank(value)) {
            throw BusinessException.error(errorEntity);
        }
    }

    public static void isTrue(Boolean isTrue, ErrorEntity errorEntity) {
        if (isTrue == null || !isTrue) {
            throw BusinessException.error(errorEntity);
        }
    }

    public static void isNotEmpty(Collection<?> collection, ErrorEntity errorEntity) {
        if (CollectionUtils.isEmpty(collection)) {
            throw BusinessException.error(errorEntity);
        }
    }

    /** @deprecated */
    @Deprecated
    public static void isNotPresent(Optional<? extends ErrorEntity> optional) {
        if (optional.isPresent()) {
            throw BusinessException.error((ErrorEntity)optional.get());
        }
    }

    public static void error(ErrorEntity errorEntity, String msg, Object... params) {
        throw BusinessException.error(errorEntity, msg, params);
    }

    public static void isNotNull(Object obj, ErrorEntity errorEntity, String msg, Object... params) {
        if (obj == null) {
            throw BusinessException.error(errorEntity, msg, params);
        }
    }

    public static void isNotBlank(String value, ErrorEntity errorEntity, String msg, Object... params) {
        if (StringUtils.isBlank(value)) {
            throw BusinessException.error(errorEntity, msg, params);
        }
    }

    public static void isTrue(Boolean isTrue, ErrorEntity errorEntity, String msg, Object... params) {
        if (isTrue == null || !isTrue) {
            throw BusinessException.error(errorEntity, msg, params);
        }
    }

    public static void isNotEmpty(Collection<?> collection, ErrorEntity errorEntity, String msg, Object... params) {
        if (CollectionUtils.isEmpty(collection)) {
            throw BusinessException.error(errorEntity, msg, params);
        }
    }

    /** @deprecated */
    @Deprecated
    public static void isNotPresent(Optional<? extends ErrorEntity> optional, String msg, Object... params) {
        if (optional.isPresent()) {
            throw BusinessException.error((ErrorEntity)optional.get(), msg, params);
        }
    }
}
