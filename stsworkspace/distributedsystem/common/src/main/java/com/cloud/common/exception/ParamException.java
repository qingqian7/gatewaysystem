package com.cloud.common.exception;

public class ParamException extends GlobalException {
    public ParamException(String msg) {
        super(msg,ResponseCode.PARAM_ERROR_CODE.getCode());
    }
}
