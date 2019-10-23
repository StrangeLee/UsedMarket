package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;
import java.util.List;


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

    private ArrayList<Integer> showProductList = new ArrayList<>();

    ProductData data = new ProductData();

    TextView schoolNameText;
    TextView productSellerText;
    TextView productPriceText;
    TextView productNameText;
    TextView productContentsText;
    TextView productHashTagText;

    Button tradeCommit;
    Button heart;

    String schoolName, seller, price, productName, productContents, hashTag;

    private OnFragmentInteractionListener mListener;

    public ShowProductFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowProductFragment newInstance(String param1, String param2) {
        ShowProductFragment fragment = new ShowProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_product, container, false);
        Bundle args = getArguments();

        if (args != null) {
            showProductList = args.getIntegerArrayList("LIST");
        }

        schoolNameText = v.findViewById(R.id.showTextSchoolName);
        productSellerText = v.findViewById(R.id.showTextSellerName);
        productPriceText = v.findViewById(R.id.showTextProductPrice);
        schoolNameText = v.findViewById(R.id.showTextSchoolName);
        productNameText = v.findViewById(R.id.showTextProductName);
        productContentsText = v.findViewById(R.id.showTextContents);
        productHashTagText = v.findViewById(R.id.showTextHashTag);

        productSellerText.setText(showProductList.get);
        productNameText.setText("그냥 물건");
        productPriceText.setText("10000원");

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
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.showFrameLayout, new MainFragment());
        transaction.commit();
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
