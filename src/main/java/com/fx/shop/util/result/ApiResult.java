package com.fx.shop.util.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author jx
 * @date 2020/8/7 17:31
 */
@Data
@ApiModel(value = "ApiResult全局统一返回结果", description = "全局统一返回结果")
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "是否成功", position = 1)
    private Boolean success = true;


    @ApiModelProperty(value = "返回码", position = 2)
    private Integer code = ResultCode.SUCCESS.getCode();


    @ApiModelProperty(value = "返回消息", position = 3)
    private String message = ResultCode.SUCCESS.getMessage();


    @ApiModelProperty(value = "返回数据", position = 4)
    private T data;


    protected ApiResult() {
        super();
    }


    /**
     * 操作成功，自定义返回消息和数据
     */
    public static <T> ApiResult<T> success(String message, T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 操作成功，自定义返回数据
     */
    public static <T> ApiResult<T> success(T data) {

        return success(ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 操作成功
     */
    public static <T> ApiResult<T> success() {

        return success(ResultCode.SUCCESS.getMessage(), null);
    }


    /**
     * 操作失败，自定义返回码，消息和数据
     */
    public static <T> ApiResult<T> error(Integer code, String message, T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 操作失败，自定义返回码，消息
     */
    public static <T> ApiResult<T> error(ResultCode resultCode) {

        return error(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 操作失败，自定义返回消息
     */
    public static <T> ApiResult<T> error(String message) {

        return error(ResultCode.ERROR.getCode(), message, null);
    }

    /**
     * 操作失败
     */
    public static <T> ApiResult<T> error() {

        return error(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage(), null);
    }

    /**
     * 返回响应异常
     *
     * @return
     */
    public static <T> ApiResult<T> exception(Exception exception) {
        return error(500, exception.getMessage(), null);
    }

}