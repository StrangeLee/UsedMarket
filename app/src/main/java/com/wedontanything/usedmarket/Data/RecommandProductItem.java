package com.wedontanything.usedmarket.Data;

import android.net.Uri;

import java.util.ArrayList;

public class RecommandProductItem {
    public Uri productUri;
    public String productName;
    public String productPrice;
    public String productSeller;

    public RecommandProductItem (Uri productUri, String productName, String productSeller, String productPrice) {
        this.productUri = productUri;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSeller = productSeller;
    }

    public static ArrayList<RecommandProductItem> createContactsList (int numContacts) {
        ArrayList<RecommandProductItem> contacts = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new RecommandProductItem(null, "셔츠", "이상하", "10,000원"));
        }

        return contacts;
    }
}
