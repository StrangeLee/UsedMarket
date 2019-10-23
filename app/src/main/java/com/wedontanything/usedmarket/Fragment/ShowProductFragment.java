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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowProductFragment extends Fragment implements MainActivity.OnKeyBackPressedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ShowProductFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
