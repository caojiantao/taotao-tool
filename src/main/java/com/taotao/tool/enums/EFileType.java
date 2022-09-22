package com.taotao.tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EFileType {

    PICTURE(1),
    VIDEO(1),
    ;

    private Integer value;
}
