package com.fx.shop.util.exception;


public class MyException extends BaseException {

    private static final long serialVersionUID = 7561312572066000828L;

    public static final int ERROR_403 = 403;
    public static final int ERROR_400 = 400;

    public MyException(int code, Object... params) {
        super(code,params);
        this.setErrorMsg(code,params);
    }

    @Override
    protected void setErrorMsg(int code, Object... params) {
        switch (code) {
            case ERROR_403:
                this.setMsg("登录会话已过期，请重新登录",params);//匹配方法
                break;
            case ERROR_400:
                this.setMsg("%s!",params);
                break;
            default:
                this.setMsg("未知异常！");
                break;
        }
    }
}
