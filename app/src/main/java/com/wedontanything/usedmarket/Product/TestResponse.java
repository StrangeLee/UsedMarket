package com.wedontanything.usedmarket.Product;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestResponse {
    private Integer status;

    private List<Product> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
