package com.taotao.tool.common.util;

import com.taotao.tool.common.constants.ApiCode;
import com.taotao.tool.common.exception.ApiException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ApiAssertUtils {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ApiException(ApiCode.UNKNOWN, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw new ApiException(ApiCode.UNKNOWN, message);
        }
    }

    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new ApiException(ApiCode.UNKNOWN, message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ApiException(ApiCode.UNKNOWN, message);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ApiException(ApiCode.UNKNOWN, message);
        }
    }
}
