package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import com.wedontanything.usedmarket.Activity.MyProductListActivity;
import com.wedontanything.usedmarket.Interface.UserService;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.User.UserInfo;
import com.wedontanything.usedmarket.Utils;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText schoolName, userName;
    ImageView userImage;
    Button modifyBtn, productListBtn;

    UserService service = Utils.RETROFIT.create(UserService.class);

    private OnFragmentInteractionListener mListener;

    public MyPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_page, container, false);

        userName = v.findViewById(R.id.myPageEditUserName);
        schoolName = v.findViewById(R.id.myPageEditSchoolName);
        productListBtn = v.findViewById(R.id.myPageButtonProductList);

        Call<Response<UserInfo>> userInfo = service.getUserInfo();

        System.out.println("a");
        userInfo.enqueue(new Callback<Response<UserInfo>>() {
            @Override
            public void onResponse(Call<Response<UserInfo>> call, retrofit2.Response<Response<UserInfo>> response) {

                Log.d("성공", "" + response.body());
//                userName.setText(response.body().getData().getName());
//                schoolName.setText(response.body().getData().getSchoolName());

                if (response.code() == 200) {
                    Log.d("성공", "");
                }

            }

            @Override
            public void onFailure(Call<Response<UserInfo>> call, Throwable t) {
                Log.d("실패", "");
            }
        });


        userName = v.findViewById(R.id.myPageEditUserName);
        schoolName = v.findViewById(R.id.myPageEditSchoolName);

        productListBtn.setOnClickListener(e -> {

        });

        userName.setEnabled(false);
        schoolName.setEnabled(false);

        return inflater.inflate(R.layout.fragment_my_page, container, false);
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
