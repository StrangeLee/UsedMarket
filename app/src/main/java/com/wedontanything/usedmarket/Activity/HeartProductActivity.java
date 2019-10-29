package com.wedontanything.usedmarket.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.wedontanything.usedmarket.Adapter.HeartProductAdapter;
import com.wedontanything.usedmarket.Adapter.MyProductAdapter;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Product.HeartProduct;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeartProductActivity extends AppCompatActivity {
    ProductService service = Utils.RETROFIT.create(ProductService.class);

    private TokenManager manager;

    private HeartProductAdapter heartProductAdapter;

    private List<RecommandProductItem> heartproductList = new ArrayList<>();
    RecyclerView heartProductRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartproduct);
        manager = TokenManager.getInstance(this);

        heartProductRecyclerView = findViewById(R.id.heartproductRecyclerview);

        heartProductAdapter = new HeartProductAdapter();
        heartProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        heartProductRecyclerView.setAdapter(heartProductAdapter);
        heartProductRecyclerView.setHasFixedSize(true);

        Call<List<HeartProduct>> getHeartProduct = service.getheartProduct(manager.getToken().getToken());

        getHeartProduct.enqueue(new Callback<List<HeartProduct>>() {
            @Override
            public void onResponse(Call<List<HeartProduct>> call, Response<List<HeartProduct>> response) {
                if(response.body() != null) {
                    Log.d("success : ", "" + response.body());
                    for (int i = 0; i < response.body().size(); i++) {
                        heartproductList.add(new RecommandProductItem(response.body().get(i).getImages().get(0).getSrc(), response.body().get(i).getProductName(),
                                response.body().get(i).getUserId(), new DecimalFormat("#,##0원").format(response.body().get(i).getPrice())));
                    }
                    heartproductList.add(new RecommandProductItem("ff", "mue6328", "abcd123",
                            "asdf"));

                    heartProductAdapter.setItem(heartproductList);
                }
            }

            @Override
            public void onFailure(Call<List<HeartProduct>> call, Throwable t) {
                Log.d("error : ", t.toString());
            }
        });

    }
}
