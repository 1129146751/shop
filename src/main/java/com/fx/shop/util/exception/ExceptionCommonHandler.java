package com.fx.shop.util.exception;

import com.fx.shop.util.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一业务异常处理
 * 
 * @author hjzeng-2015
 *
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionCommonHandler {

    @ExceptionHandler(value = {MyException.class,Exception.class})
    public ApiResult<Object> myExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        if(ex instanceof MyException){
            MyException e = (MyException) ex;
            String path = request.getRequestURI();
            if (e.getCode() == 403) {
                log.debug("JWT失效,请重新登录");
                log.debug("业务请求path={}访问异常", path);
                return ApiResult.error(403,e.getMsg(),null);
            }
            log.debug("业务请求path={}访问异常", path);
            return ApiResult.error(400,e.getMsg(),null);
        }else {
            log.error("", ex);
            return ApiResult.error(403,ex.getMessage(),null);
        }

    }




   /* *//*
     * 权限异常
     *//*
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    @ResponseBody
    public RestResult<Object> authorizationException(HttpServletRequest request, HttpServletResponse response) {
        AdminServiceException exception = new AdminServiceException(AdminServiceException.ERROR_10005);
        request.setAttribute(AdminConstant.RESULT_FLAG_KEY, true);
        request.setAttribute(AdminConstant.RESULT_FLAG_MSG_KEY, exception.getErroDesc());
        ResultResponse<Object> rm = ResultResponse.exception(exception);
        String path = request.getRequestURI();
        log.debug("业务请求path={}权限异常", path);

        return rm;
    }*/

}
