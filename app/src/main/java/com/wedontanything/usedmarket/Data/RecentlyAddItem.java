package com.wedontanything.usedmarket.Data;

import android.net.Uri;

import java.util.ArrayList;

public class RecentlyAddItem {
    public Uri productUri;
    public String productName;
    public String productPrice;

    public RecentlyAddItem (Uri productUri, String productName, String productPrice) {
        this.productUri = productUri;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public static ArrayList<RecentlyAddItem> createContactsList (int numContacts) {
        ArrayList<RecentlyAddItem> contacts = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new RecentlyAddItem(null, "셔츠", "10,000원"));
        }

        return contacts;
    }

}
