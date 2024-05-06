package com.taotao.tool.system.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.exception.TTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(TTException.class)
    public ApiResult<Void> handleApiException(TTException e) {
        log.error("act=handleApiException", e);
        return ApiResult.fail(e);
    }

    @ExceptionHandler({BindException.class})
    public ApiResult<Void> handleBindException(BindException e) {
        log.error("act=handleBindException", e);
        assert e.getFieldError() != null;
        String msg = e.getFieldError().getDefaultMessage();
        return ApiResult.fail(msg);
    }

    @ExceptionHandler({ValidationException.class})
    public ApiResult<Void> handleValidException(ValidationException e) {
        log.error("act=handleValidException", e);
        return ApiResult.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<Void> handleException(Exception e) {
        log.error("act=handleException", e);
        return ApiResult.fail();
    }
}
