package com.taotao.tool.exception;

import com.taotao.tool.enums.EApiCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final EApiCode code;

    public ApiException(EApiCode code, String message) {
        super(message);
        this.code = code;
    }
}
