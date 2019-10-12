package com.wedontanything.usedmarket.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Fragment.ShowProductFragment;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendProductAdapter extends RecyclerView.Adapter<RecommendProductAdapter.Holder> {

    MainActivity main = new MainActivity();
    ShowProductFragment showFragment = new ShowProductFragment();
    private RecyclerViewClickListener clickListener;
    private ArrayList<Product> mData = new ArrayList<>();

    public RecommendProductAdapter(FragmentActivity activity, RecyclerViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void updateData(List<Product> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }


    public void setItem(ArrayList list) {
            this.mData = list;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recommend_product_item, viewGroup, false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.productImg.setImageResource(R.drawable.ic_image);
        holder.productPrice.setText(mData.get(i).getPrice());
        holder.productSeller.setText(mData.get(i).getSeller());
        holder.productName.setText(mData.get(i).getProductName());

    }

    @Override
    public int getItemCount() {
            return mData.size();
        }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView productImg;
        public TextView productName;
        public TextView productSeller;
        public TextView productPrice;

        private RecyclerViewClickListener listener;

        public Holder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.mainProductImageRecommendItem);
            productName = itemView.findViewById(R.id.mainTextProductNameRecommendItem);
            productSeller = itemView.findViewById(R.id.mainTextProductSellerRecommendItem);
            productPrice = itemView.findViewById(R.id.mainTextProductPriceRecommendItem);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
