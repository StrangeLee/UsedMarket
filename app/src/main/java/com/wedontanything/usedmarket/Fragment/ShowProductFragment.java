package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wedontanything.usedmarket.Activity.Basic;
import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.HeartService;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.Product.UpdateProduct;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;


public class ShowProductFragment extends Fragment implements MainActivity.OnKeyBackPressedListener {

    // TODO : 사진 크기 고정, 해쉬태그 짤리는거, 삭제 버튼 삭제, 판매중 버튼 작동 똑바로(구매가능 ,거래중, 거래완료), Heart Button Background 색 White 로 바꾸기
    private Product showProduct;
    ProductData data = new ProductData();

    TextView productCategoryText;
    TextView productSellerText;
    TextView productPriceText;
    TextView productNameText;
    TextView productContentsText;
    TextView productHashTagText;
    ImageView productImage;

    Button tradeCommit;
    Button heart;
    Button deleteBtn;

    private static final String UNHEART = "♡";
    private static final String HEART = "♥";

    Boolean heartCheck = false;
    // server connection
    ProductService productservice = Utils.RETROFIT.create(ProductService.class);
    HeartService heartService = Utils.RETROFIT.create(HeartService.class);
    TokenManager manager;

    private OnFragmentInteractionListener mListener;

    public ShowProductFragment() {

    }

    public static ShowProductFragment newInstance(Product productList) {
        ShowProductFragment fragment = new ShowProductFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("LIST", productList);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            showProduct = getArguments().getParcelable("LIST");
        } else {
            showProduct = new Product(1, "a", "A", "a", 1, 1, "a", "a", "A", "a", "A");
            Toast.makeText(getActivity(), "네트워크 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_product, container, false);

        manager = TokenManager.getInstance(getActivity().getApplicationContext());

        productSellerText = v.findViewById(R.id.showTextSellerName);
        productPriceText = v.findViewById(R.id.showTextProductPrice);
        productNameText = v.findViewById(R.id.showTextProductName);
        productContentsText = v.findViewById(R.id.showTextContents);
        productHashTagText = v.findViewById(R.id.showTextHashTag);
        productImage = v.findViewById(R.id.showImageProduct);
        tradeCommit = v.findViewById(R.id.showButtonRequest);
        productCategoryText = v.findViewById(R.id.showTextCategory);
        deleteBtn = v.findViewById(R.id.showButtonDelete);
        heart = v.findViewById(R.id.showButtonHeart);

        productSellerText.setText("판매자 : " + showProduct.getMember_id());
        productNameText.setText(showProduct.getProduct_name());
        productPriceText.setText("가격 : " + new DecimalFormat("#,##0원").format(showProduct.getMoney()));
        productContentsText.setText(showProduct.getDescription());
        productHashTagText.setText(showProduct.getHashtag());
        productCategoryText.setText("카테고리 : " + showProduct.getCategory());
        Log.d("LOG", productNameText.getText().toString());
        Picasso.get().load(Utils.HOST_URL + showProduct.getImage()).into(productImage);

        tradeCommit.setText("판매중");

        heart.setOnClickListener(e -> {
            if (heartCheck == false) {
                Call<Response> heartcheck = heartService.postClickHeart(manager.getToken().getToken(), showProduct.getId());
                heartCheck = true;
                heart.setText(HEART);
                heartcheck.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        Log.d("찜하기 성공", "" + response.body());
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.d("찜하기 실패", t.toString());
                    }
                });
            }
            else if(heartCheck == true) {
                Call<Response> unHeart = heartService.postUnClickHeart(manager.getToken().getToken(), showProduct.getId());
                heartCheck = false;
                heart.setText(UNHEART);
                unHeart.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Log.d("찜하기 취소", "" + response.body());

                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.d("찜하기 취소 실패", t.toString());
                    }
                });
            }
        });

        tradeCommit.setOnClickListener(e -> {
            Call<Response> updateProduct = productservice.putUpdateProduct(showProduct.getId().toString(), manager.getToken().getToken(),
                    productNameText.getText().toString(), productContentsText.getText().toString(),
                    showProduct.getMoney(),
                    productHashTagText.getText().toString(), productCategoryText.getText().toString(),
                    "거래중", ""
                    );
            Log.d("LOG", updateProduct.toString());
            updateProduct.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Toast.makeText(getActivity(), "거래중", Toast.LENGTH_LONG).show();
                    //Log.d("LOG", response.body().toString() + " A");

                    // TODO: 결과값 서버에 요청하기
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(getActivity(), "실패", Toast.LENGTH_LONG).show();
                }
            });

        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnKeyBackPressedListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onBackKey() {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
