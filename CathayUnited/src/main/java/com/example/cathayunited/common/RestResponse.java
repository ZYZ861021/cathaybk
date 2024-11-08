package com.example.cathayunited.common;

public class RestResponse<T> {

    private int statusCode;
    private String message;
    private T data;

    public RestResponse() {
    }

    public RestResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // 靜態方法來創建成功或失敗的回應
    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(200, "Success", data);
    }

    public static <T> RestResponse<T> failure(String message) {
        return new RestResponse<>(500, message, null);
    }

    public static <T> RestResponse<T> failure(int statusCode, String message) {
        return new RestResponse<>(statusCode, message, null);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
