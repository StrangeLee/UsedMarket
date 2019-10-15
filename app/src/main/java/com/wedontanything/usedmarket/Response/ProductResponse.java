package com.wedontanything.usedmarket.Response;

import com.google.gson.annotations.SerializedName;

public class ProductResponse<T> {
    @SerializedName(value = "status")
    private Integer status;

    @SerializedName(value = "data")
    private T data;

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
}
