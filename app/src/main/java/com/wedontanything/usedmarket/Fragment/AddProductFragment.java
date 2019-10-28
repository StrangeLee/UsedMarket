package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wedontanything.usedmarket.Activity.Basic;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Product.AddProduct;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProductFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Spinner categorySpinner;

    EditText addProductName, addPrice, addDescription, addHashTag;
    List<Image> iamgeList = new ArrayList<Image>();
    Button addButton;
    Button addPicture;

    ProductService service = Utils.RETROFIT.create(ProductService.class);
    TokenManager manager;

    private OnFragmentInteractionListener mListener;

    public AddProductFragment() {

    }

    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_product, container, false);

        //----------------------------------------------------------------------------------------------------------
        // spinner
        categorySpinner = v.findViewById(R.id.addSpinnerCategory);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.spinnerArray));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(spinnerAdapter);
        //----------------------------------------------------------------------------------------------------------
        // 항목 null 체크
        addButton = v.findViewById(R.id.addButtonCommit);
        addProductName = v.findViewById(R.id.addEditProductName);
        addPrice = v.findViewById(R.id.addEditProductPrice);
        addDescription = v.findViewById(R.id.addEditProductContents);
        addHashTag = v.findViewById(R.id.addEditHashTag);

        Basic basic = new Basic();
        List<String> addString = new ArrayList<>();

        addButton.setOnClickListener(e -> {
            for (int i = 0 ; i < 5; i ++) {
                if (addString.get(i).isEmpty()) {
                    basic.showDialog(getActivity(), "Message", addString.get(i));
                    return;
                }
            }

            //----------------------------------------------------------------------------------------------------------
            // data send to server
            manager = TokenManager.getInstance(getActivity().getApplicationContext());

            Call<AddProduct> addProduct = service.postProductApply(manager.getToken().getToken(),
                    addString.get(0), addString.get(2), Integer.parseInt(addString.get(1)), addString.get(3), addString.get(4), "A"
            );

            addProduct.enqueue(new Callback<AddProduct>() {
                @Override
                public void onResponse(Call<AddProduct> call, Response<AddProduct> response) {
                    Log.d("TAG", "상품 추가 성공");
                }

                @Override
                public void onFailure(Call<AddProduct> call, Throwable t) {

                }
            });
        });




        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
