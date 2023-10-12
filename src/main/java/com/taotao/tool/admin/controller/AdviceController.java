package com.taotao.tool.admin.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.constants.ApiCode;
import com.taotao.tool.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ApiException.class)
    public ApiResult<Void> handleApiException(ApiException e) {
        log.error("act=handleApiException", e);
        return ApiResult.fail(e);
    }

    @ExceptionHandler(BindException.class)
    public ApiResult<Void> handleBindException(BindException e) {
        log.error("act=handleBindException", e);
        assert e.getFieldError() != null;
        String msg = e.getFieldError().getDefaultMessage();
        return new ApiResult<>(ApiCode.UNKNOWN, null, msg);
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<Void> handleException(Exception e) {
        log.error("act=handleException", e);
        return new ApiResult<>(ApiCode.UNKNOWN, null, "服务异常");
    }
}
