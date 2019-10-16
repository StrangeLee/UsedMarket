package com.wedontanything.usedmarket.Product;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestResponse {
    private int status;

    private List<TestProduct> productList;

    public List<TestProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<TestProduct> productList) {
        this.productList = productList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

        public int getId() {
            return id;
        }

        public String getProductName() {
            return productName;
        }

        public String getDescription() {
            return description;
        }

        public int getPrice() {
            return price;
        }

        public Integer getHeart() {
            return heart;
        }

        public String getHashtag() {
            return hashtag;
        }

        public String getCategory() {
            return category;
        }

        public String getUpdateDay() {
            return updateDay;
        }

        public int getState() {
            return state;
        }

        public String getUserId() {
            return UserId;
        }

        public List<TestImage> getImages() {
            return Images;
        }
    }

    public class TestImage{
        int id;
        String productName;
        String src;
        int ProductId;
    }

}
