package com.taotao.tool.common.exception;

import lombok.Getter;

@Getter
public class TTException extends RuntimeException {

    private final int code;

    public TTException(int code, String message) {
        super(message);
        this.code = code;
    }
}
