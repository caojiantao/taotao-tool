package com.taotao.tool.common.util;

import com.taotao.tool.common.constants.EApiCode;
import com.taotao.tool.common.exception.TTException;

import java.util.Objects;

public class TTAssertUtils {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new TTException(EApiCode.UNKNOWN.getCode(), message);
        }
    }

    public static void notTrue(boolean expression, String message) {
        if (expression) {
            throw new TTException(EApiCode.UNKNOWN.getCode(), message);
        }
    }

    public static void isNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw new TTException(EApiCode.UNKNOWN.getCode(), message);
        }
    }

    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new TTException(EApiCode.UNKNOWN.getCode(), message);
        }
    }
}
