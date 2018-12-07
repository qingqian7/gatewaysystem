package com.cloud.common.basic;

public enum ResponseCode {
    SUCCESS_CODE(200),
    PARAM_ERROE_CODE(400),
    LIMIT_ERROR_CODE(401),
    TOKEN_TIMEOUT_ERROR(402),
    NO_AUTH_ERROR(403),
    NOT_FOUND_ERROR(404),
    SERVER_ERROR(500),
    DOWNGRADE(406);
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    private ResponseCode(int code){
        this.code = code;
    }
}
