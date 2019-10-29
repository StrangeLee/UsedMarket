package com.wedontanything.usedmarket.Product;

import java.util.List;

public class HeartProduct {
    private int id;
    private String productName;
    private String description;
    private int price;
    private int heart;
    private String hashtag;
    private String category;
    private String updateDay;
    private String state;
    private String UserId;
    private List<Image> Images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdateDay() {
        return updateDay;
    }

    public void setUpdateDay(String updateDay) {
        this.updateDay = updateDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public List<Image> getImages() {
        return Images;
    }

    public void setImages(List<Image> images) {
        Images = images;
    }

    public class Image{
        int id;
        String src;
        int ProductId;

        public int getId() {
            return id;
        }

        public String getSrc() {
            return src;
        }

        public int getProductId() {
            return ProductId;
        }
    }

}

