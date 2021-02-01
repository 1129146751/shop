package com.fx.shop.config.handler;

import cn.hutool.core.collection.CollUtil;
import com.sineyun.commons.core.exception.CustomException;
import com.sineyun.commons.core.result.ApiResult;
import com.sineyun.commons.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    public ApiResult handleCustomExceptionHandler(CustomException e) {
        log.error("业务异常==>错误码：{}，错误信息：{}", e.getErrorCode(), e.getErrorDesc());
        log.error("业务异常==>异常信息：", e);
        return ApiResult.exception(e);
    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ApiResult handleNullPointerException(NullPointerException e) {
        log.error("空指针异常==>错误码：{}，错误信息：{}", "", "空指针异常");
        log.error("空指针异常==>异常信息：", e);
        return ApiResult.error("出现空指针异常，请联系管理员");
    }


    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorDescList = new ArrayList<>();
        for (ObjectError s : e.getBindingResult().getAllErrors()) {
            errorDescList.add(s.getObjectName() + s.getDefaultMessage());
        }
        String errordesc = ResultCode.INVALID_PARAMETER.getMessage();
        if (errorDescList.size() > 0) {
            errordesc = CollUtil.join(errorDescList, "，");
        }
        log.error("参数校验异常==>错误码：{}，错误信息：{}", ResultCode.INVALID_PARAMETER.getCode(), errordesc);
        log.error("参数校验异常==>异常信息：", e);
        return ApiResult.error(ResultCode.INVALID_PARAMETER.getCode(), errordesc, null);
    }


    /**
     * 处理参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ApiResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("参数校验异常==>错误码：{}，错误信息：{}", ResultCode.INVALID_PARAMETER.getCode(), e.getMessage());
        log.error("参数校验异常==>异常信息：", e);
        return ApiResult.error(ResultCode.INVALID_PARAMETER.getCode(), e.getMessage(), null);
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ApiResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数格式错误==>错误码：{}，错误信息：{}", ResultCode.INVALID_PARAMETER.getCode(), e.getMessage());
        log.error("参数格式错误==>异常信息：", e);
        return ApiResult.error(ResultCode.INVALID_PARAMETER.getCode(), e.getMessage(), null);
    }

    /**
     * 404异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ApiResult handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        String path = request.getRequestURI();
        log.error("404异常信息==>错误码：{}，错误信息：{}", ResultCode.NOT_FOUND.getCode(), path);
        log.error("404异常==>异常信息：", e);
        return ApiResult.error(ResultCode.NOT_FOUND);
    }


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ApiResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式不支持==>错误码：{}，错误信息：{}", ResultCode.ERROR.getCode(), e.getMessage());
        log.error("参数格式错误==>异常信息：", e);
        return ApiResult.error(e.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResult handleException(Exception e) {
        log.error("系统异常==>错误码：{}，错误信息：{}", ResultCode.ERROR.getCode(), e.getMessage());
        log.error("系统异常==>异常信息：", e);
        return ApiResult.error();
    }

    // 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiResult handlerThrowable(Throwable e, HttpServletRequest request) {
        log.error("发生未知异常==>错误码：{}，错误信息：{}", ResultCode.ERROR.getCode(), e.getMessage());
        log.error("系统异常==>异常信息：", e);
        return ApiResult.error();
    }


}
