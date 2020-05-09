package com.jincou.sms.login.util;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private T result;

    public static <T> ServiceResponse<T> success() {
        return new ServiceResponse("0000", (String)null, (Object)null);
    }

    public static <T> ServiceResponse<T> success(T result) {
        return new ServiceResponse("0000", (String)null, result);
    }

    public static <T> ServiceResponse<T> success(T result, String message) {
        return new ServiceResponse("0000", message, result);
    }

    public static <T> ServiceResponse<T> failure(String code, String message) {
        return new ServiceResponse(code, message, (Object)null);
    }

    public static <T> ServiceResponse<T> failure(String code, String message, T result) {
        return new ServiceResponse(code, message, result);
    }

    public ServiceResponse() {
    }

    private ServiceResponse(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return "0000".equals(this.code);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}