package com.wedontanything.usedmarket.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.wedontanything.usedmarket.Data.RecentlyAddItem;
import com.wedontanything.usedmarket.Fragment.AddProductFragment;
import com.wedontanything.usedmarket.Fragment.CategoryFragment;
import com.wedontanything.usedmarket.Fragment.ChatFragment;
import com.wedontanything.usedmarket.Fragment.MainFragment;
import com.wedontanything.usedmarket.Fragment.MyPageFragment;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fragmentManager = getSupportFragmentManager();
    public FragmentTransaction transaction;
    private MainFragment mainFragment = new MainFragment();
    private CategoryFragment categoryFragment = new CategoryFragment();
    private AddProductFragment addProductFragment = new AddProductFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();
    private ChatFragment chatFragment = new ChatFragment();

    Context context;

    private ArrayList<RecentlyAddItem> list = new ArrayList<>();

    public interface OnKeyBackPressedListener {
        public void onBackKey();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
           // case R.id.:

                default:
                return super.onOptionsItemSelected(item);
        }

    }

    private  OnKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(OnKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  setSupportActionBar(toolbar);

        // BottomNavigation 생성
        BottomNavigationView bottomNavigationView = findViewById(R.id.mainNavigation);


        // fragment 객체 생성
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout, mainFragment);
        transaction.commit();

        // bottomNavigationView 이벤트
        bottomNavigationView.setOnNavigationItemSelectedListener(i -> {
            transaction = fragmentManager.beginTransaction();

            switch (i.getItemId()){
                case R.id.nav_home:
                    transaction.replace(R.id.mainFrameLayout, mainFragment);
                    transaction.commit();
                    break;
                case R.id.nav_category:
                    transaction.replace(R.id.mainFrameLayout, categoryFragment);
                    transaction.commit();
                    break;
                case R.id.nav_write:
                    transaction.replace(R.id.mainFrameLayout, addProductFragment);
                    transaction.commit();
                    break;
                case R.id.nav_user:
                    transaction.replace(R.id.mainFrameLayout, myPageFragment);
                    transaction.commit();
                    break;
                case R.id.nav_chat:
                    transaction.replace(R.id.mainFrameLayout, chatFragment);
                    transaction.commit();
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (mOnKeyBackPressedListener != null) {
            mOnKeyBackPressedListener.onBackKey();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                Toast.makeText(getApplicationContext(), "종료하려면 한번 더 누르세요.", Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
        }
    }
}
