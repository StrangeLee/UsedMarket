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
    String callNum;
    int price;
    String productName;
    String discription;
    String hashTag;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
    }
}
