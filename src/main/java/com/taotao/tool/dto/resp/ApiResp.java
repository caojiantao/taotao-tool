package com.taotao.tool.dto.resp;

import com.taotao.tool.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResp<T> {

    private Integer code;
    private T data;
    private String msg;

    public static <T> ApiResp<T> success(T data) {
        return new ApiResp<>(0, data, null);
    }

    public static ApiResp<Void> fail(ApiException e) {
        return new ApiResp<>(e.getCode().getNo(), null, e.getMessage());
    }
}
