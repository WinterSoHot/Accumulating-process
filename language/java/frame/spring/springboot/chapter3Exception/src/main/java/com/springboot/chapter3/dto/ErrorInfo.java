package com.springboot.chapter3.dto;

public class ErrorInfo<T> {

    private Integer code;
    private String message;
    private String url;
    private T data;

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;


    public ErrorInfo() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }
}
