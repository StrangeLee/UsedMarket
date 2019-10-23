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

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.Holder> {

    private RecyclerViewClickListener clickListener;
    private List<RecommandProductItem> myProduct = new ArrayList<>();

    public MyProductAdapter() {

    }

    public void setItem(List<RecommandProductItem> list) {
        this.myProduct = list;
        notifyDataSetChanged();
    }

    @Override
    public MyProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recommend_product_item, viewGroup, false);
        MyProductAdapter.Holder holder = new MyProductAdapter.Holder(view, clickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductAdapter.Holder holder, int i) {
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

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView productImg;
        public TextView productName;
        public TextView productSeller;
        public TextView productPrice;

        private RecyclerViewClickListener listener;

        public Holder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);

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
