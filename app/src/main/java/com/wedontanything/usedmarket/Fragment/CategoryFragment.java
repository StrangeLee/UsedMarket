package com.wedontanything.usedmarket.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wedontanything.usedmarket.Adapter.CategoryAdapter;
import com.wedontanything.usedmarket.Data.CategoryListItem;
import com.wedontanything.usedmarket.R;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryFragment extends Fragment {

    // TODO : Product 에서 카테고리만 분류해서 띄우기
    ListView categoryList;
    CategoryAdapter categoryAdapter;

    private OnFragmentInteractionListener mListener;

    public CategoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        categoryList = v.findViewById(R.id.categoryListView);

        // category item 생성
        ArrayList<String> names = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.spinnerArray)));
        List<CategoryListItem> categoryListItems = new ArrayList<>();
        List<Integer> images = Arrays.asList(
                R.drawable.ic_action_book,
                R.drawable.ic_action_clothes,
                R.drawable.ic_action_digital,
                R.drawable.ic_action_food,
                R.drawable.ic_action_hobby,
                R.drawable.ic_action_makeup,
                R.drawable.ic_action_schoolsupplies,
                R.drawable.ic_action_sports,
                R.drawable.ic_action_ticket
        );

        for (int i = 0; i < names.size() - 1; i++) {
            categoryListItems.add(new CategoryListItem(names.get(i + 1), images.get(i)));
            Log.d("List", categoryListItems.get(i).getCategoryName());
        }
        //--------------------------------------------------------------

        categoryAdapter = new CategoryAdapter(categoryListItems);
        categoryList.setAdapter(categoryAdapter);


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
