package com.wedontanything.usedmarket.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wedontanything.usedmarket.Data.RecentlyAddItem;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecentlyAddAdapter extends RecyclerView.Adapter<RecentlyAddAdapter.Holder> {

    private List<RecentlyAddItem> list = new ArrayList<>();
    private RecyclerViewClickListener clickListener;

    public RecentlyAddAdapter(RecyclerViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setItem(List<RecentlyAddItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recently_add_item, viewGroup, false);
        Holder holder = new Holder(view, clickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Picasso.get().load(Utils.HOST_URL + list.get(i).productUri).into(holder.productImg);
        holder.productImg.setImageResource(R.drawable.ic_image);
        holder.productPrice.setText(list.get(i).productPrice);
        holder.productText.setText(list.get(i).productName);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView productImg;
        public TextView productText;
        public TextView productPrice;

        private RecyclerViewClickListener listener;

        public Holder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);

            productImg = itemView.findViewById(R.id.mainProductImageRecentlyAddItem);
            productText = itemView.findViewById(R.id.mainTextProductNameRecentlyAddItem);
            productPrice = itemView.findViewById(R.id.mainTextProductPriceRecentlyAddItem);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
