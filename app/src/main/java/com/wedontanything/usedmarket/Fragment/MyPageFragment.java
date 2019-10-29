package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import com.wedontanything.usedmarket.Activity.LoginActivity;
import com.wedontanything.usedmarket.Activity.MyProductActivity;
import com.wedontanything.usedmarket.Activity.MyProductListActivity;
import com.wedontanything.usedmarket.Activity.PasswordFindActivity;
import com.wedontanything.usedmarket.DataBase.TokenManager;
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
    EditText schoolName, userName;
    ImageView userImage;
    Button modifyBtn, productListBtn, LogoutBtn;

    UserService service = Utils.RETROFIT.create(UserService.class);

    TokenManager manager;

    private OnFragmentInteractionListener mListener;

    public MyPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_page, container, false);

        userName = v.findViewById(R.id.myPageEditUserName);
        schoolName = v.findViewById(R.id.myPageEditSchoolName);
        productListBtn = v.findViewById(R.id.myPageButtonProductList);
        LogoutBtn = v.findViewById(R.id.myPageButtonLogout);
        userImage = v.findViewById(R.id.myPageImageUserImage);

        userImage.setImageResource(R.drawable.main_logo);

        userName.setText("A");

        manager = TokenManager.getInstance(getActivity().getApplicationContext());

        Call<Response<UserInfo>> userInfo = service.getUserInfo(manager.getToken().getToken());

        System.out.println("a");
        userInfo.enqueue(new Callback<Response<UserInfo>>() {
            @Override
            public void onResponse(Call<Response<UserInfo>> call, retrofit2.Response<Response<UserInfo>> response) {
                Log.d("토큰", "" + manager.getToken().getToken());
                System.out.println("b");


                Log.d("성공", "" + response.code()
                        + " " + response.body().getData().getName());
                userName.setText(response.body().getData().getName());
                schoolName.setText(response.body().getData().getSchoolName());

                if (response.code() == 200) {
                    Log.d("성공", "");
                    userName.setText(response.body().getData().getName());
                    schoolName.setText(response.body().getData().getSchoolName());
                }

            }

            @Override
            public void onFailure(Call<Response<UserInfo>> call, Throwable t) {
                Log.d("실패", "");
            }
        });

        productListBtn.setOnClickListener(e -> {
            Intent intent = new Intent(v.getContext(), MyProductActivity.class);
            startActivity(intent);
        });

        LogoutBtn.setOnClickListener(e -> {
//            manager.setToken("");
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
        });

        userName.setEnabled(false);
        schoolName.setEnabled(false);

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
        void onFragmentInteraction(Uri uri);
    }
}
