package com.example.ncrsystem.ncrsystem.common.response;

public class ApiResponse<T> {
    private Integer code;
    private Object message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(
            Integer code,
            Object message,
            T data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
