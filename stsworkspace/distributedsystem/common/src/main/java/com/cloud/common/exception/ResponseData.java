package com.cloud.common.exception;

public class ResponseData {
    private boolean status = true;
    private int code = 200;
    private String message;
    private Object data;

    public static ResponseData ok(Object data){     //此方法在调用正常时使用   返回的data可以自定义  而其他值采用默认值
        return new ResponseData(data);
    }
    public  ResponseData(){
        super();
    }

    public ResponseData(Object data){
        super();
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
