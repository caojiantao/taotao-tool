package com.taotao.tool.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EApiCode implements IApiCode {

    UNKNOWN(-1, "服务异常"),
    NOT_LOGIN(-2, "未登录"),
    ;

    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
