package com.wedontanything.usedmarket.Product;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private Integer id;

    private String member_id;

    private String product_name;

    private String description;

    private Integer money;

    private Integer heart_cnt;

    private String hashtag;

    private String update_day;

    private String state;

    private String image;

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getHeart_cnt() {
        return heart_cnt;
    }

    public String getHashtag() {
        return hashtag;
    }

    public String getUpdate_day() {
        return update_day;
    }

    public String getState() {
        return state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(Integer id, String member_id, String product_name, String description, Integer money, Integer heart_cnt, String hashtag, String update_day, String state, String image) {
        this.id = id;
        this.member_id = member_id;
        this.product_name = product_name;
        this.description = description;
        this.money = money;
        this.heart_cnt = heart_cnt;
        this.hashtag = hashtag;
        this.update_day = update_day;
        this.state = state;
        this.image = image;
    }

    public Product (Parcel dest) {
        id = dest.readInt();
        member_id = dest.readString();
        product_name = dest.readString();
        description = dest.readString();
        money = dest.readInt();
        heart_cnt = dest.readInt();
        hashtag = dest.readString();
        update_day = dest.readString();
        state = dest.readString();
        image = dest.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(member_id);
        dest.writeString(product_name);
        dest.writeString(description);
        dest.writeInt(money);
        dest.writeInt(heart_cnt);
        dest.writeString(hashtag);
        dest.writeString(update_day);
        dest.writeString(state);
        dest.writeString(image);
    }
}
