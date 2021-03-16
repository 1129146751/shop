package com.fx.shop.util.exception;

public abstract class BaseException  extends RuntimeException {
    private static final long serialVersionUID = 8178302434812284832L;
    public static final int SUCCESS = 200;
    private Integer code;
    private String msg;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(int code) {
        this.setCode(code);
        switch(code) {
            case 200:
                this.setMsg("操作成功");
            default:
                this.setErrorMsg(code);
        }
    }

    public BaseException(int code, Object... params) {
        this.setCode(code);
        if (params == null) {
            throw new IllegalArgumentException("Params can't be null");
        } else {
            this.setErrorMsg(code, params);
        }
    }

    protected abstract void setErrorMsg(int var1, Object... var2);

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    protected void setMsg(String msg, Object... params) {
        this.setMsg(String.format(msg, params));
    }
}
