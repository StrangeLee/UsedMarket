package com.wedontanything.usedmarket.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedontanything.usedmarket.Activity.MainActivity;
import com.wedontanything.usedmarket.Data.ProductData;
import com.wedontanything.usedmarket.Data.RecommandProductItem;
import com.wedontanything.usedmarket.Fragment.ShowProductFragment;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;

public class RecommendProductAdapter extends RecyclerView.Adapter<RecommendProductAdapter.Holder> {

    MainActivity main = new MainActivity();
    ShowProductFragment showFragment = new ShowProductFragment();

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, boolean isUser);
    }
    private OnItemClickListener clickListener;

    private ArrayList<RecommandProductItem> list = new ArrayList<>();
    ProductData data = new ProductData();

    public RecommendProductAdapter(FragmentActivity activity, OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setItem(ArrayList list) {
            this.list = list;
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
                holder.productPrice.setText(list.get(i).productPrice);
                holder.productSeller.setText(list.get(i).productSeller);
                holder.productName.setText(list.get(i).productName);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            public ImageView productImg;
            public TextView productName;
            public TextView productSeller;
            public TextView productPrice;

            OnItemClickListener listener;

            public Holder(@NonNull View itemView) {
                super(itemView);

                productImg = itemView.findViewById(R.id.mainProductImageRecommendItem);
                productName = itemView.findViewById(R.id.mainTextProductNameRecommendItem);
                productSeller = itemView.findViewById(R.id.mainTextProductSellerRecommendItem);
                productPrice = itemView.findViewById(R.id.mainTextProductPriceRecommendItem);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.setProductPrice(productPrice.getText().toString());
                        data.setSeller(productSeller.getText().toString());
                        data.setProductName(productName.getText().toString());
                    }
                });
        }

    }

}
