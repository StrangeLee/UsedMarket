package com.wedontanything.usedmarket.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowProductActivity extends AppCompatActivity {

    String schoolName;
    String sellerName;
    int price;
    String productName;
    String discription;
    String hashTag;
    int mode;

    Button dealButton;
    TextView schoolNameText;
    TextView sellerNameText;
    TextView productNameText;
    TextView discriptionText;
    TextView hashTagText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        dealButton = findViewById(R.id.showButtonRequest);
        schoolNameText = findViewById(R.id.showTextSchoolName);
        sellerNameText = findViewById(R.id.showTextSellerName);
        productNameText = findViewById(R.id.showTextProductName);
        discriptionText = findViewById(R.id.showTextContents);
        hashTagText = findViewById(R.id.showTextHashTag);

        schoolNameText.setText(schoolName);
        sellerNameText.setText(sellerName);
        productNameText.setText(productName);
        discriptionText.setText(discription);
        hashTagText.setText(hashTag);
    }

    public ShowProductActivity(String schoolName, String sellerName, int price, String productName, String discription, String hashTag, int mode) {
        this.schoolName = schoolName;
        this.sellerName = sellerName;
        this.price = price;
        this.productName = productName;
        this.discription = discription;
        this.hashTag = hashTag;
        this.mode = mode;
    }


}
