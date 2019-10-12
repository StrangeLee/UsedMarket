package com.wedontanything.usedmarket.Data;

import android.net.Uri;

import java.util.ArrayList;

public class RecentlyAddItem {
    public Uri productUri;
    public String productName;
    public String productPrice;

    public RecentlyAddItem (Uri uri, String productName, String productPrice) {
        this.productUri = uri;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public static ArrayList<RecentlyAddItem> createContactsList (int numContacts) {
        ArrayList<RecentlyAddItem> contacts = new ArrayList<>();

        for (int i = 1; i < numContacts; i++) {
            contacts.add(new RecentlyAddItem(null, "A", "d"));
        }

        return contacts;
    }

}
