package com.wedontanything.usedmarket.Product;

public class Product {
    private Integer id;

    private String member_id;

    private String product_name;

    private String description;

    private Integer money;

    private Integer heart_cnt;

    private String hashtag;

    private String update_day;

    private String state;

    public Product(Integer id, String member_id, String product_name, String description, Integer money, Integer heart_cnt, String hashtag, String update_day, String state) {
        this.id = id;
        this.member_id = member_id;
        this.product_name = product_name;
        this.description = description;
        this.money = money;
        this.heart_cnt = heart_cnt;
        this.hashtag = hashtag;
        this.update_day = update_day;
        this.state = state;
    }

}
