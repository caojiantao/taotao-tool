package com.taotao.tool.common.util;

import com.taotao.tool.common.constants.EApiCode;
import com.taotao.tool.common.exception.ApiException;

import java.util.Objects;

public class ApiAssertUtils {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ApiException(EApiCode.UNKNOWN.getCode(), message);
        }
    }

    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new ApiException(EApiCode.UNKNOWN.getCode(), message);
        }
    }
}
