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
import com.wedontanything.usedmarket.Adapter.RecentlyAddAdapter;
import com.wedontanything.usedmarket.Data.RecentlyAddItem;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Adapter.RecommendProductAdapter;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.Product.GetAllProduct;
import com.wedontanything.usedmarket.Product.Product;
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


    ProductService service = Utils.RETROFIT.create(ProductService.class);

    TokenManager manager;


    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
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



        manager = TokenManager.getInstance(getActivity().getApplicationContext());

        Call<Response<List<Product>>> getAllProduct = service.getAllProduct(manager.getToken().getToken());

        getAllProduct.enqueue(new Callback<Response<List<Product>>>() {
            @Override
            public void onResponse(Call<Response<List<Product>>> call, retrofit2.Response<Response<List<Product>>> response) {
                Log.d("success", "" + response.isSuccessful());
                Log.d("string body", "" + response.body().toString());
                Log.d("token", "" + manager.getToken().getToken());
                Log.d("body", "" + response.body().getData());

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.d("success resonse", successResponse);

                List<Product> productList = response.body().getData();
                ArrayList<RecommandProductItem> recommanditemlist = new ArrayList<>();

//                for (int i = 0; i < response.body().getData().size(); i++) {
//                    recommanditemlist.add(new RecommandProductItem(null, productList.get(i).getProduct_name(), productList.get(i).getMember_id(),
//                            new DecimalFormat("#,##0원").format(productList.get(i).getMoney())));
//                }
                recommendAdapter.setItem(productList);
               // recommendAdapter.notifyDataSetChanged();

//                for (int i = 0; i < response.body().getData().getProducts().size(); i++) {
//                    System.out.println(response.body().getData().getProducts().get(i).toString());
//                    productList.add(response.body().getData().getProducts().get(i));
//
//                    recommendAdapter.setItem(productList);
//                }

//                for (Product product : productList) {
//
//                }
            }

            @Override
            public void onFailure(Call<Response<List<Product>>> call, Throwable t) {

            }
        });
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
        recommendList = RecommandProductItem.createContactsList(5);

//        Call<Response<GetAllProduct>> getAllProduct = service.getAllProduct(manager.getToken().getToken());

//        getAllProduct.enqueue(new Callback<Response<GetAllProduct>>() {
//            @Override
//            public void onResponse(Call<Response<GetAllProduct>> call, retrofit2.Response<Response<GetAllProduct>> response) {
////                for (int i = 0; i < response.body().getData().getProducts().size(); i++) {
//                    // recommendList.add(response.body().getData().getProducts().get(i));
//  //              }
//
//            }
//
//            @Override
//            public void onFailure(Call<Response<GetAllProduct>> call, Throwable t) {
//
//            }
//        });

        recentlyAddRecyclerView.setHasFixedSize(true);
        lastAddAdapter = new RecentlyAddAdapter(getActivity());
        lastAddAdapter.setItem(recentlyList);
        recentlyAddRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));
        recentlyAddRecyclerView.setAdapter(lastAddAdapter);
        lastAddAdapter.notifyDataSetChanged();

        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_LONG).show();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainFrameLayout, new ShowProductFragment());
            transaction.commit();
        };



        recommendRecyclerView.setHasFixedSize(true);
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recommendAdapter = new RecommendProductAdapter(getActivity(), listener);
       // recommendAdapter.setItem(recommendList);
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
