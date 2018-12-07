package com.cloud.common.exception;

public class ServerException extends GlobalException {

    public ServerException(String message) {
        super(message, ResponseCode.SERVER_ERROE_CODE.getCode());
    }
}
