package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.util.Log;

import com.google.gson.Gson;
import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Adapter.RecentlyAddAdapter;
import com.wedontanything.usedmarket.Data.RecentlyAddItem;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Adapter.RecommendProductAdapter;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.Product.GetAllProduct;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recentlyAddRecyclerView, recommendRecyclerView;
    private RecentlyAddAdapter lastAddAdapter;
    private RecommendProductAdapter recommendAdapter;
    private ArrayList<RecentlyAddItem> recentlyList = new ArrayList<>();
    private ArrayList<RecommandProductItem> recommendList = new ArrayList<>();
    private ArrayList<RecommandProductItem> recommanditemlist = new ArrayList<>();

    RecyclerViewClickListener listener = (view, position) -> {
        Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_LONG).show();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout, new ShowProductFragment());
        transaction.commit();
    };

    ProductService service = Utils.RETROFIT.create(ProductService.class);

    TokenManager manager;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recentlyAddRecyclerView = v.findViewById(R.id.mainRecyclerViewRecentlyAddList);
        recommendRecyclerView = v.findViewById(R.id.mainRecyclerViewRecommendList);

        recentlyList = RecentlyAddItem.createContactsList(5);
//        recommendList = RecommandProductItem.createContactsList(5);

        //------------------------------------------------------------------------------

        // 서버 API 받아오기-----------------------------------------------------------------------------
        manager = TokenManager.getInstance(getActivity().getApplicationContext());

        Call<TestResponse> getAllProduct = service.getAllProduct(manager.getToken().getToken());

        //------------------------------------------------------------------
        // 최근 본 상품
        recentlyAddRecyclerView.setHasFixedSize(true);
        lastAddAdapter = new RecentlyAddAdapter(getActivity());
        lastAddAdapter.setItem(recentlyList);
        recentlyAddRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));
        recentlyAddRecyclerView.setAdapter(lastAddAdapter);
        lastAddAdapter.notifyDataSetChanged();

        // 추천 상품
        recommendAdapter = new RecommendProductAdapter(listener);
        Log.d("TAG", "" + recommendAdapter.getItemCount());

        getAllProduct.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, retrofit2.Response<TestResponse> response) {

                List<TestResponse.TestProduct> productList = response.body().getProductList();

                for (int i = 0; i < productList.size(); i++) {
                    recommanditemlist.add(new RecommandProductItem(null, productList.get(i).getProductName(), productList.get(i).getUserId(),
                            new DecimalFormat("#,##0원").format(productList.get(i).getPrice())));
                }

                recommendAdapter.setItem(recommanditemlist);
                recommendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {

            }
        });

        recommendRecyclerView.setHasFixedSize(true);
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommendRecyclerView.setAdapter(recommendAdapter);

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
