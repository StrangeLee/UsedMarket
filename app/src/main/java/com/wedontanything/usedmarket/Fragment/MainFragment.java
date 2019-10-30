package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wedontanything.usedmarket.Adapter.RecentlyAddAdapter;
import com.wedontanything.usedmarket.Data.RecentlyAddItem;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Adapter.RecommendProductAdapter;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;

public class MainFragment extends Fragment {

    // TODO : XML고치기, 배너 생성, font바꾸기, 카테고리 바꾸고, Border 추가하고
    private RecyclerView recentlyAddRecyclerView, recommendRecyclerView;
    private RecentlyAddAdapter lastAddAdapter;
    private RecommendProductAdapter recommendAdapter;
    private List<RecommandProductItem> recommendItemList = new ArrayList<>();
    private List<RecentlyAddItem> recentlyItemList = new ArrayList<>();
    private ArrayList<Product> productAllList = new ArrayList<>();

    // RecyclerView Listener 구현
    RecyclerViewClickListener listener = (view, position) -> {

        ShowProductFragment sf = ShowProductFragment.newInstance(productAllList.get(position));
        Log.d("TAG", productAllList.get(position).getProduct_name());

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout, sf);
        transaction.commit();
    };

    ProductService service = Utils.RETROFIT.create(ProductService.class);
    TokenManager manager;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recentlyAddRecyclerView = v.findViewById(R.id.mainRecyclerViewRecentlyAddList);
        recommendRecyclerView = v.findViewById(R.id.mainRecyclerViewRecommendList);
        //------------------------------------------------------------------------------

        // 서버 API 받아오기-----------------------------------------------------------------------------
        manager = TokenManager.getInstance(getActivity().getApplicationContext());

        Call<TestResponse> getAllProduct = service.getAllProduct(manager.getToken().getToken());

        //------------------------------------------------------------------
        // 최근 본 상품
        lastAddAdapter = new RecentlyAddAdapter(listener);
        recentlyAddRecyclerView.setHasFixedSize(true);
        recentlyAddRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));
        recentlyAddRecyclerView.setAdapter(lastAddAdapter);

        // 추천 상품
        recommendAdapter = new RecommendProductAdapter(listener);
        recommendRecyclerView.setHasFixedSize(true);
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        recommendRecyclerView.setAdapter(recommendAdapter);

        getAllProduct.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, retrofit2.Response<TestResponse> response) {

                List<TestResponse.TestProduct> productList = response.body().getProductList();
                Log.d("LOG", productList.get(0).getUpdateDay());
                productAllList.clear();
                recommendItemList.clear();
                recentlyItemList.clear();
                for (int i = 0; i < productList.size(); i++) {
                    productAllList.add(new Product(productList.get(i).getId(), productList.get(i).getUserId(), productList.get(i).getProductName(),
                            productList.get(i).getDescription(), productList.get(i).getPrice(), productList.get(i).getHeart(), productList.get(i).getHashtag(),
                            productList.get(i).getUpdateDay(), productList.get(i).getState(), productList.get(i).getImages().get(0).getSrc(), productList.get(i).getCategory()));

                    recommendItemList.add(new RecommandProductItem(productList.get(i).getImages().get(0).getSrc(), productList.get(i).getProductName(), productList.get(i).getUserId(),
                            new DecimalFormat("#,##0원").format(productList.get(i).getPrice()), productList.get(i).getUpdateDay()));
                    Log.d("LOG", productList.get(i).getImages().get(0).getSrc());

                    recentlyItemList.add(new RecentlyAddItem(productList.get(i).getImages().get(0).getSrc(), productList.get(i).getProductName(), new DecimalFormat("#,##0원").format(productList.get(i).getPrice())));

                }
                recommendAdapter.setItem(recommendItemList);
                lastAddAdapter.setItem(recentlyItemList);
            }
            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "실패", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
