package com.wedontanything.usedmarket.Response;

import com.google.gson.annotations.SerializedName;

public class Response<T> {
    @SerializedName(value = "status")
    private Integer status;

    @SerializedName(value = "data")
    private T data;

    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
