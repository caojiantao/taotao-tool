package com.taotao.tool.common.dto;

import com.taotao.tool.common.exception.ApiException;
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

    public static ApiResult<Void> fail(ApiException e) {
        return new ApiResult<>(e.getCode(), null, e.getMessage());
    }

    public static <T> ApiResult<T> fail(ApiException e, T data) {
        return new ApiResult<>(e.getCode(), data, e.getMessage());
    }
}
