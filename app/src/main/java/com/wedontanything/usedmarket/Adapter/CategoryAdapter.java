package com.wedontanything.usedmarket.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wedontanything.usedmarket.Data.CategoryListItem;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    Context context;
    ArrayList<CategoryListItem> categoryList = new ArrayList<>();

    TextView categoryName;
    ImageView categoryImage;

    public CategoryAdapter(Context context, ArrayList<CategoryListItem> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Context context = parent.getContext();
            if (inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            convertView = inflater.inflate(R.layout.category_item, parent, false);
        }
        categoryName = convertView.findViewById(R.id.categoryItemName);
        categoryImage = convertView.findViewById(R.id.categoryItemImage);

        categoryImage.setImageResource(categoryList.get(position).getCategoryImage());
        categoryName.setText(categoryList.get(position).getCategoryName());

        return convertView;
    }
}
