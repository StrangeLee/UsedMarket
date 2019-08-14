package com.wedontanything.usedmarket.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.wedontanything.usedmarket.Fragment.AddProductFragment;
import com.wedontanything.usedmarket.Fragment.CategoryFragment;
import com.wedontanything.usedmarket.Fragment.ChatFragment;
import com.wedontanything.usedmarket.Fragment.MainFragment;
import com.wedontanything.usedmarket.Fragment.MyPageFragment;
import com.wedontanything.usedmarket.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private MainFragment mainFragment = new MainFragment();
    private CategoryFragment categoryFragment = new CategoryFragment();
    private AddProductFragment addProductFragment = new AddProductFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();
    private ChatFragment chatFragment = new ChatFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // fragment 객체 생성

        // bottomNavigationView 이벤트
        bottomNavigationView.setOnNavigationItemSelectedListener(i -> {
            transaction = fragmentManager.beginTransaction();

            switch (i.getItemId()){
                case R.id.nav_home:
                    transaction.replace(R.id.frameLayout, mainFragment);
                    break;
                case R.id.nav_category:
                    transaction.replace(R.id.frameLayout, categoryFragment);
                    transaction.commit();
                    break;
                case R.id.nav_write:
                    transaction.replace(R.id.frameLayout, addProductFragment);
                    transaction.commit();
                    break;
                case R.id.nav_user:
                    transaction.replace(R.id.frameLayout, myPageFragment);
                    transaction.commit();
                    break;
                case R.id.nav_chat:
                    transaction.replace(R.id.frameLayout, chatFragment);
                    transaction.commit();
                    break;
            }
            return true;
        });
    }

}
