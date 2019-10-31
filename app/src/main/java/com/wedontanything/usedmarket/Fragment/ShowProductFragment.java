package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wedontanything.usedmarket.Activity.Basic;
import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Adapter.CommentAdapter;
import com.wedontanything.usedmarket.Adapter.HeartProductAdapter;
import com.wedontanything.usedmarket.Comment.CommentList;
import com.wedontanything.usedmarket.Data.CommentItem;
import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.CommentService;
import com.wedontanything.usedmarket.Interface.HeartService;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.Product.UpdateProduct;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.Utils;


import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;


public class ShowProductFragment extends Fragment {

    // TODO : 해쉬태그 짤리는거, 삭제 버튼 삭제, 판매중 버튼 작동 똑바로(구매가능 ,거래중, 거래완료), Heart Button Background 색 White 로 바꾸기
    private Product showProduct;
    ProductData data = new ProductData();

    RecyclerView commentRecyclerView;
    CommentAdapter commentAdapter;

    List<CommentItem> item = new ArrayList<>();

    TextView productCategoryText;
    TextView productSellerText;
    TextView productPriceText;
    TextView productNameText;
    TextView productContentsText;
    TextView productHashTagText;
    TextView productDateText;
    ImageView productImage;

    Button tradeCommit;
    Button heart;
    Button deleteBtn;

    File tempFile;
    File file;

    Bitmap bitmapImage;

    private static final String UNHEART = "♡";
    private static final String HEART = "♥";

    Boolean heartCheck = false;
    // server connection
    ProductService productservice = Utils.RETROFIT.create(ProductService.class);
    HeartService heartService = Utils.RETROFIT.create(HeartService.class);
    CommentService commentService = Utils.RETROFIT.create(CommentService.class);
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
        productDateText = v.findViewById(R.id.showTextDate);
        heart = v.findViewById(R.id.showButtonHeart);

        String [] date = showProduct.getUpdate_day().split("T");

        productSellerText.setText("판매자 : " + showProduct.getMember_id());
        productNameText.setText(showProduct.getProduct_name());
        productPriceText.setText("가격 : " + new DecimalFormat("#,##0원").format(showProduct.getMoney()));
        productContentsText.setText(showProduct.getDescription());
        productHashTagText.setText(showProduct.getHashtag());
        productCategoryText.setText("카테고리 : " + showProduct.getCategory());
        productDateText.setText("등록일 : " + date[0]);
        Log.d("LOG", productNameText.getText().toString());
        Picasso.get().load(Utils.HOST_URL + showProduct.getImage()).into(productImage);

        tradeCommit.setText(showProduct.getState());

        commentRecyclerView = v.findViewById(R.id.commentRecyclerView);

        commentAdapter = new CommentAdapter();
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayout.VERTICAL, false));
        commentRecyclerView.setAdapter(commentAdapter);
        commentRecyclerView.setHasFixedSize(true);

        Call<List<CommentList>> commentList = commentService.getCommentList(manager.getToken().getToken(), showProduct.getId());

        commentList.enqueue(new Callback<List<CommentList>>() {
            @Override
            public void onResponse(Call<List<CommentList>> call, retrofit2.Response<List<CommentList>> response) {
                Log.d("body", "" + response.body() + response.code() + response.message());
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        item.add(new CommentItem(response.body().get(i).getUserId(), response.body().get(i).getContent()));
                    }
                    commentAdapter.setItem(item);
                }
            }

            @Override
            public void onFailure(Call<List<CommentList>> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });

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
                    //Toast.makeText(getActivity(), "거래중", Toast.LENGTH_LONG).show();

                    // TODO: 결과값 서버에 요청하기
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    //Toast.makeText(getActivity(), "실패", Toast.LENGTH_LONG).show();
                }
            });

        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //((MainActivity)context).setOnKeyBackPressedListener(this);
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
