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
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProductFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        addButton = getActivity().findViewById(R.id.addButtonCommit);
        addProductName = getActivity().findViewById(R.id.addEditProductName);
        addPrice = getActivity().findViewById(R.id.addEditProductPrice);
        addDescription = getActivity().findViewById(R.id.addEditProductContents);
        addHashTag = getActivity().findViewById(R.id.addEditHashTag);
        addProductName = getActivity().findViewById(R.id.addEditProductName);

        Basic basic = new Basic();
        List<String> errMsg = new ArrayList<>();

        if (addButton.getText().toString().isEmpty()) {
            errMsg.add(addButton.getText().toString());
        } else if (addProductName.getText().toString().isEmpty()) {
            errMsg.add(addProductName.getText().toString());
        } else if (addPrice.getText().toString().isEmpty()) {
            errMsg.add(addPrice.getText().toString());
        } else if (addDescription.getText().toString().isEmpty()) {
            errMsg.add(addDescription.getText().toString());
        } else if (addHashTag.getText().toString().isEmpty()) {
            errMsg.add(addHashTag.getText().toString());
        } else if (categorySpinner.getSelectedItemPosition() == 0) {
            errMsg.add("카테고리");
        } else if (iamgeList.size() == 0) {
            errMsg.add("사진");
        }

        if (errMsg.size() > 0) {
            //basic.showDialog(getActivity(), "Message", errMsg.get);
        }
        //----------------------------------------------------------------------------------------------------------
        // data send to server
        manager = TokenManager.getInstance(getActivity().getApplicationContext());


        //Call<Response> updateProduct = service.putUpdateProduct()

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
