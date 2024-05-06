package com.taotao.tool.common.dto;

import com.taotao.tool.common.constants.EApiCode;
import com.taotao.tool.common.constants.IApiCode;
import com.taotao.tool.common.exception.TTException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {

    private Integer code;
    private T data;
    private String msg;

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(0, null, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(0, data, null);
    }

    public static ApiResult<Void> fail() {
        return new ApiResult<>(EApiCode.UNKNOWN.getCode(), null, EApiCode.UNKNOWN.getMessage());
    }

    public static ApiResult<Void> fail(TTException e) {
        return new ApiResult<>(e.getCode(), null, e.getMessage());
    }

    public static ApiResult<Void> fail(String message) {
        return new ApiResult<>(EApiCode.UNKNOWN.getCode(), null, message);
    }

    public static ApiResult<Void> build(IApiCode e) {
        return build(e, null);
    }

    public static <T> ApiResult<T> build(IApiCode e, T data) {
        return new ApiResult<>(e.getCode(), data, e.getMessage());
    }

    public static <T> ApiResult<T> fail(TTException e, T data) {
        return new ApiResult<>(e.getCode(), data, e.getMessage());
    }
}
