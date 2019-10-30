package com.wedontanything.usedmarket.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.wedontanything.usedmarket.Adapter.MyProductAdapter;
import com.wedontanything.usedmarket.Adapter.RecommendProductAdapter;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProductActivity extends AppCompatActivity {

    ProductService service = Utils.RETROFIT.create(ProductService.class);

    private TokenManager manager;

    private MyProductAdapter myProductAdapter;

    private List<RecommandProductItem> myproductList = new ArrayList<>();
    RecyclerView myProductRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproduct);
        manager = TokenManager.getInstance(this);

        myProductRecyclerView = findViewById(R.id.myproductRecyclerview);

        Call<TestResponse> myproduct = service.getMyProduct(manager.getToken().getToken());

        myProductAdapter = new MyProductAdapter();
        myProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        myProductRecyclerView.setAdapter(myProductAdapter);
        myProductRecyclerView.setHasFixedSize(true);

        myproduct.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {

                List<TestResponse.TestProduct> productList = response.body().getProductList();

                for (int i = 0; i < productList.size(); i++) {
                    myproductList.add(new RecommandProductItem(productList.get(i).getImages().get(0).getSrc(), productList.get(i).getProductName(), productList.get(i).getUserId(),
                            new DecimalFormat("#,##0원").format(productList.get(i).getPrice()), productList.get(i).getUpdateDay()));
                }

                myProductAdapter.setItem(myproductList);
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {

            }
        });
    }
}
