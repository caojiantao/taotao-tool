package com.taotao.tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EApiCode {

    UNKNOWN(-1, "未知错误"),
    NOT_LOGIN(-2, "未登录"),
    ;

    private Integer no;
    private String desc;
}
