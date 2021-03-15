package com.fx.shop.util.result;

/**
 * 返回枚举类
 *
 * @Author jx
 * @Date 2020/8/7 17:31
 */
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 用户异常
     */
    NOT_LOGIN(401, "登录信息已失效，请重新登录"),
    INVALID_PARAMETER(402, "请求参数有误"),
    NOT_AUTH(403, "您无权限进行该操作，请联系管理员"),
    NOT_FOUND(404, "路径不存在，请检查路径是否正确"),
    ACCOUNT_LOCKED(405, "账号已被锁定，请%s后重试"),
    TIP_REGISTER(406, "该用户不存在，请前往注册"),

    /**
     * 业务自定义异常
     */
    ERROR_10001(10001, "%s已存在，请勿重复添加"),
    ERROR_10002(10002, "%s格式有误"),
    ERROR_10003(10003, "%s不能为空"),

    /**
     * 系统错误
     */
    ERROR(10000, "系统出现错误，请联系管理员");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    /**
     * 重写toString方法在控制台显示自定义异常信息
     *
     * @return
     */
    @Override
    public String toString() {
        return "[异常码:" + this.code + " 异常信息:" + this.message + "]";
    }
}
