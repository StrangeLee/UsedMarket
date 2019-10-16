package com.wedontanything.usedmarket.Product;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestResponse {
    private Integer status;

    private List<TestProduct> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TestProduct> getData() {
        return data;
    }

    public void setData(List<TestProduct> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public class TestProduct {
        int id;
        String productName;
        String description;
        int price;
        Integer heart;
        String hashtag;
        String category;
        String updateDay;
        int state;
        String UserId;
        List<TestImage> Images;
    }

    public class TestImage{
        int id;
        String productName;
        String src;
        int ProductId;
    }

}
