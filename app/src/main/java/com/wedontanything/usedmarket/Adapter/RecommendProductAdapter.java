package com.wedontanything.usedmarket.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Fragment.ShowProductFragment;
import com.wedontanything.usedmarket.Interface.RecyclerViewClickListener;
import com.wedontanything.usedmarket.Product.GetProduct;
import com.wedontanything.usedmarket.Product.Product;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecommendProductAdapter extends RecyclerView.Adapter<RecommendProductAdapter.Holder> {

    private RecyclerViewClickListener clickListener;
    private List<RecommandProductItem> mData = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일'T'hh:mm");
    private Date date;

    public RecommendProductAdapter(RecyclerViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setItem(List<RecommandProductItem> list) {
            this.mData = list;
            notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_recommend_product_item, viewGroup, false);
        Holder holder = new Holder(view, clickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String [] date1 = mData.get(i).productDate.split("T");
//        String [] time = date1[1].split("");
//        Log.d("LOG", time[0]);
        Picasso.get().load(Utils.HOST_URL + mData.get(i).productUri).into(holder.productImg);
        holder.productPrice.setText(mData.get(i).productPrice);
        holder.productSeller.setText("판매자 : " + mData.get(i).productSeller);
        holder.productName.setText(mData.get(i).productName);
        holder.productDate.setText("등록 날짜 " + date1[0] + " ");


        //Log.d("LOG", "어댑터 등록 날짜 " + sdf.format(date));
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
        public TextView productDate;

        private RecyclerViewClickListener listener;

        public Holder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);

            productImg = itemView.findViewById(R.id.mainProductImageRecommendItem);
            productName = itemView.findViewById(R.id.mainTextProductNameRecommendItem);
            productSeller = itemView.findViewById(R.id.mainTextProductSellerRecommendItem);
            productPrice = itemView.findViewById(R.id.mainTextProductPriceRecommendItem);
            productDate = itemView.findViewById(R.id.mainTextDateRecommendItem);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
