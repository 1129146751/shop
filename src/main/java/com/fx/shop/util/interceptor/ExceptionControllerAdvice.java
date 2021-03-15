package com.fx.shop.util.interceptor;

import com.fx.shop.util.common.RestCode;

import com.fx.shop.util.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: fxbin
 * @datetime: 2018/7/5 18:29
 * @description: 全局异常
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler
    public ApiResult<String> jsonErrorHandler(HttpServletRequest req, Exception e) {
        logger.error("异常产生:", e);
        return ApiResult.error(e.getMessage());
    }

    @ExceptionHandler
    public ApiResult<Map<String, String>> jsonErrorHandler(BindException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ApiResult.error(RestCode.FAIL.code, "error", errors);
    }

    @ExceptionHandler
    public ApiResult<Map<String, String>> jsonErrorHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ApiResult.error(RestCode.FAIL.code, "error", errors);
    }

}

