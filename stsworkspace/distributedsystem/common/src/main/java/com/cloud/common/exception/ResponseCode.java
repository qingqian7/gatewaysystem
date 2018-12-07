package com.cloud.common.exception;

public enum ResponseCode {
    PARAM_ERROR_CODE(400),
    SERVER_ERROE_CODE(500);
    private int code;

    public int getCode() {
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }

    private ResponseCode(int code){
        this.code = code;
    }
}
