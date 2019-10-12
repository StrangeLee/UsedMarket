package com.wedontanything.usedmarket.Request;

public class ProductRequest {
    private String product_name;

    private String description;

    private Integer money;

    private String hashTag;

    private Integer state;

    public ProductRequest(String product_name, String description, Integer money, String hashTag, Integer state) {
        this.product_name = product_name;
        this.description = description;
        this.money = money;
        this.hashTag = hashTag;
        this.state = state;
    }


}
