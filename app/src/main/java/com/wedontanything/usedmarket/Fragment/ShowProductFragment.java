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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ShowProductFragment extends Fragment implements MainActivity.OnKeyBackPressedListener {

    private Product showProduct;

    ProductData data = new ProductData();

    TextView schoolNameText;
    TextView productSellerText;
    TextView productPriceText;
    TextView productNameText;
    TextView productContentsText;
    TextView productHashTagText;
    ImageView productImage;

    Button tradeCommit;
    Button heart;

    String schoolName, seller, price, productName, productContents, hashTag;

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
            showProduct = new Product(1, "a", "A", "a", 1, 1, "a", "a", "A", "a");
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

        productSellerText.setText(showProduct.getMember_id());
        productNameText.setText(showProduct.getProduct_name());
        productPriceText.setText(new DecimalFormat("#,##0원").format(showProduct.getMoney()));
        productContentsText.setText(showProduct.getDescription());
        //var hashtag = showProduct.getHashtag();
        productHashTagText.setText(showProduct.getHashtag());
        Picasso.get().load(Utils.HOST_URL + showProduct.getImage()).into(productImage);

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
