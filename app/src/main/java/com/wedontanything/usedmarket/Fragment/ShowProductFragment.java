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

    // server connection
    ProductService service = Utils.RETROFIT.create(ProductService.class);
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
            // TODO: 다이얼로그 표시
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_product, container, false);

        productSellerText = v.findViewById(R.id.showTextSellerName);
        productPriceText = v.findViewById(R.id.showTextProductPrice);
        productNameText = v.findViewById(R.id.showTextProductName);
        productContentsText = v.findViewById(R.id.showTextContents);
        productHashTagText = v.findViewById(R.id.showTextHashTag);
        productImage = v.findViewById(R.id.showImageProduct);
        tradeCommit = v.findViewById(R.id.showButtonRequest);
        productCategoryText = v.findViewById(R.id.showTextCategory);

        productSellerText.setText(showProduct.getMember_id());
        productNameText.setText(showProduct.getProduct_name());
        productPriceText.setText(new DecimalFormat("#,##0원").format(showProduct.getMoney()));
        productContentsText.setText(showProduct.getDescription());
        productHashTagText.setText(showProduct.getHashtag());
        productCategoryText.setText(showProduct.getCategory());
        Log.d("LOG", productNameText.getText().toString());
        Picasso.get().load(Utils.HOST_URL + showProduct.getImage()).into(productImage);

        tradeCommit.setText(showProduct.getState());

        tradeCommit.setOnClickListener(e -> {
            manager = TokenManager.getInstance(getActivity().getApplicationContext());
            Call<Response> updateProdcut = service.putUpdateProduct(manager.getToken().getToken(),
                    productNameText.getText().toString(), productContentsText.getText().toString(),
                    showProduct.getMoney(),
                    productHashTagText.getText().toString(), productCategoryText.getText().toString(),
                    "거래중", "A"
                    );

            updateProdcut.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Toast.makeText(getActivity(), "거래중", Toast.LENGTH_LONG).show();
                    //Log.d("LOG", response.body().toString());
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
