package com.taotao.tool.controller;

import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.enums.EApiCode;
import com.taotao.tool.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ApiException.class)
    public ApiResp<Void> handleApiException(ApiException e) {
        log.error("act=handleApiException", e);
        return ApiResp.fail(e);
    }

    @ExceptionHandler(BindException.class)
    public ApiResp<Void> handleBindException(BindException e) {
        log.error("act=handleBindException", e);
        String msg = e.getFieldError().getDefaultMessage();
        return new ApiResp<>(EApiCode.UNKNOWN.getNo(), null, msg);
    }

    @ExceptionHandler(Exception.class)
    public ApiResp<Void> handleException(Exception e) {
        log.error("act=handleException", e);
        return new ApiResp<>(EApiCode.UNKNOWN.getNo(), null, "服务异常");
    }
}
