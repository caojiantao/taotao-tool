package com.taotao.tool.util;

import com.taotao.tool.enums.EApiCode;
import com.taotao.tool.exception.ApiException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ApiAssertUtils {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ApiException(EApiCode.UNKNOWN, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw new ApiException(EApiCode.UNKNOWN, message);
        }
    }

    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new ApiException(EApiCode.UNKNOWN, message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ApiException(EApiCode.UNKNOWN, message);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ApiException(EApiCode.UNKNOWN, message);
        }
    }
}
