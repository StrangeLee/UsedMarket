package com.wedontanything.usedmarket.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.util.ArrayList;
import java.util.List;

public class HeartProductAdapter extends RecyclerView.Adapter<HeartProductAdapter.Holder> {

    private RecyclerViewClickListener clickListener;
    private List<RecommandProductItem> myProduct = new ArrayList<>();

    public HeartProductAdapter() {

    }

    public void setItem(List<RecommandProductItem> list) {
        this.myProduct = list;
        notifyDataSetChanged();
    }

    @Override
    public HeartProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recommend_product_item, viewGroup, false);
        HeartProductAdapter.Holder holder = new HeartProductAdapter.Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeartProductAdapter.Holder holder, int i) {
        Picasso.get().load(Utils.HOST_URL + myProduct.get(i).productUri).into(holder.productImg);
        //holder.productImg.setImageResource(R.drawable.ic_image);
        holder.productPrice.setText(myProduct.get(i).productPrice);
        holder.productSeller.setText(myProduct.get(i).productSeller);
        holder.productName.setText(myProduct.get(i).productName);
    }

    @Override
    public int getItemCount() {
        return myProduct.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView productImg;
        public TextView productName;
        public TextView productSeller;
        public TextView productPrice;

        public Holder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.mainProductImageRecommendItem);
            productName = itemView.findViewById(R.id.mainTextProductNameRecommendItem);
            productSeller = itemView.findViewById(R.id.mainTextProductSellerRecommendItem);
            productPrice = itemView.findViewById(R.id.mainTextProductPriceRecommendItem);
        }


    }
}
