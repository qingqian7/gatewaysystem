package com.cloud.common.exception;

public class GlobalException extends Exception {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public GlobalException(String msg){
        super(msg);
    }

    public GlobalException(String message, int code) {
        super(message);
        this.code = code;
    }
}
