package com.wedontanything.usedmarket.Activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wedontanything.usedmarket.Fragment.AddProductFragment;
import com.wedontanything.usedmarket.Fragment.CategoryFragment;
import com.wedontanything.usedmarket.Fragment.ChatFragment;
import com.wedontanything.usedmarket.Fragment.MainFragment;
import com.wedontanything.usedmarket.Fragment.MyPageFragment;
import com.wedontanything.usedmarket.R;

public class AddProductActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_product);

        // BottomNavigation 생성
        BottomNavigationView bottomNavigationView = findViewById(R.id.addNavigation);

        // fragment 객체 생성
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.addFrameLayout, addProductFragment);
        transaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(i -> {
            transaction = fragmentManager.beginTransaction();

            switch (i.getItemId()){
                case R.id.nav_home:
                    transaction.replace(R.id.addFrameLayout, mainFragment);
                    transaction.commit();
                    break;
                case R.id.nav_category:
                    transaction.replace(R.id.addFrameLayout, categoryFragment);
                    transaction.commit();
                    break;
                case R.id.nav_write:
                    transaction.replace(R.id.addFrameLayout, addProductFragment);
                    transaction.commit();
                    break;
                case R.id.nav_user:
                    transaction.replace(R.id.addFrameLayout, myPageFragment);
                    transaction.commit();
                    break;
                case R.id.nav_chat:
                    transaction.replace(R.id.addFrameLayout, chatFragment);
                    transaction.commit();
                    break;
            }
            return true;
        });
    }
}
