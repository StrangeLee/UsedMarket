package com.wedontanything.usedmarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wedontanything.usedmarket.Adapter.CategoryShowAdapter;
import com.wedontanything.usedmarket.Adapter.HeartProductAdapter;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Fragment.ShowProductFragment;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.Product.HeartProduct;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryShowActivity extends AppCompatActivity {

    ProductService service = Utils.RETROFIT.create(ProductService.class);

    private String productName;

    private List<RecommandProductItem> itemList = new ArrayList<>();
    private TokenManager manager;
    private CategoryShowAdapter categoryShowAdapter;
    private RecyclerView categoryShowRecyclerView;

    RecyclerViewClickListener listener = (view, position) -> {
        ShowProductFragment sf = new ShowProductFragment();
        Log.d("Tag", "f");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout, sf);
        transaction.commit();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_show);

        Intent intent = getIntent();
        String categoryName = (String) intent.getExtras().get("CATEGORY");

        manager = TokenManager.getInstance(this);

        categoryShowRecyclerView = findViewById(R.id.categoryShowRecyclerView);

        categoryShowAdapter = new CategoryShowAdapter(listener);
        categoryShowRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        categoryShowRecyclerView.setAdapter(categoryShowAdapter);
        categoryShowRecyclerView.setHasFixedSize(true);

        Call<List<HeartProduct>> getHeartProduct = service.getheartProduct(manager.getToken().getToken());

        getHeartProduct.enqueue(new Callback<List<HeartProduct>>() {
            @Override
            public void onResponse(Call<List<HeartProduct>> call, Response<List<HeartProduct>> response) {
                Log.d("success : ", "" + response.body());
                if(response.body() != null) {

                    for (int i = 0; i < response.body().size(); i++) {

                        if (response.body().get(i).getCategory().equals(categoryName)) {
                            itemList.add(new RecommandProductItem(response.body().get(i).getImages().get(0).getSrc(), response.body().get(i).getProductName(),
                                    response.body().get(i).getUserId(), new DecimalFormat("#,##0원").format(response.body().get(i).getPrice()), response.body().get(i).getUpdateDay()));
                        }

                    }

                    categoryShowAdapter.setItem(itemList);
                    Log.d("HEART", categoryShowAdapter.getItemCount() + " 아이템 갯수");
                }
            }

            @Override
            public void onFailure(Call<List<HeartProduct>> call, Throwable t) {
                Log.d("error : ", t.toString());
            }
        });



       // categoryShowRecyclerView.setOnClickListener(new );


    }
}
